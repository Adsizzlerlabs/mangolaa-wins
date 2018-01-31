package com.adsizzler.mangolaa.wins

import com.adsizzler.mangolaa.wins.verticle.WinNotificationsVerticle
import groovy.util.logging.Slf4j
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import javax.annotation.PostConstruct

@SpringBootApplication
@Slf4j
class WinsApplication {

	@Autowired
	private Vertx vertx

	@Autowired
	private SpringVerticleFactory springVerticleFactory

	static void main(String[] args) {
		SpringApplication.run WinsApplication, args
	}

	//Deploy n number of verticles per core, following the Multi-reactor pattern
	@PostConstruct
	void deployVerticles(){
		vertx.registerVerticleFactory(springVerticleFactory)
		def cores = Runtime.getRuntime().availableProcessors()
		log.info "Number of cores available {} " , cores
		def numOfVerticlesToDeploy = cores * 2
		log.info "Deploying {} verticle instances ", numOfVerticlesToDeploy
		def options = new DeploymentOptions().setInstances(numOfVerticlesToDeploy)
		vertx.deployVerticle(springVerticleFactory.prefix() + ":" + WinNotificationsVerticle.class.getName(),options,{ deployment->
			if(deployment.succeeded()){
				log.info "Deployment successful. Deployment Info {} " , deployment.result()
			}
			else{
				log.error "Deployment Failed with exception ", deployment.cause()
			}
		})
	}
}
