## Overview ##

From a birds eye view, this module is responsible for two important things:

* Returning the markup for BidRequests served. Any event received by this module is called 'WinNotification'
* Sending the WinNotification received to Apache Kafka

**URL structure**

* Base domain : **wins.mangolaa.com**
* Query params : 
   * As per Open RTB 2.5 : 
      * bid_req_id : ${AUCTION_ID} 
      * bid_resp_id : ${AUCTION_BID_ID}  
      * imp_id : ${AUCTION_IMP_ID} 
      * seat_id : ${AUCTION_SEAT_ID} 
      * ad_id : ${AUCTION_AD_ID} 
      * cur : {AUCTION_CURRENCY} 
      * price : ${AUCTION_PRICE}
      * mbr : ${AUCTION_MBR}
      * loss : ${AUCTION_LOSS} 

   * Macros that are **required** by Mangolaa
      * cr_id : Creative id 

      


Example : 
https://wins.mangolaa.com/?cr_id=3&bid_req_id=123&bid_resp_id=345&imp_id=abc&seat_id=def&ad_id=123&seat_id=tgv&ad_id=edc&cur=USD&price=2.4&mbr=1.2&loss=1


## Tech ##
* **Programming Language** : Groovy 2.4.13
* **Framework** : Spring Boot on top of Vert.x
* **Cache** : CQEngine as a Local cache
* **Messaging** : Apache Kafka as both producer and Consumer
* **Repository** : Fetching CMS data from MySQL on startup, and Cassandra to deal with BidRequests and BidResponse, and H2 for testing
* **Tests** : Spring-Spock, Vertx-unit
* **DevOps** : Docker


## References ##

[Open RTB 2.5](https://www.iab.com/wp-content/uploads/2016/03/OpenRTB-API-Specification-Version-2-5-FINAL.pdf)