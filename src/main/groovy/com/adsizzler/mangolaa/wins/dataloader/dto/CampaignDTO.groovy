package com.adsizzler.mangolaa.wins.dataloader.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by ankushsharma on 30/01/18.
 */
@ToString(includePackage = false)
@Entity
@Table(name = 'campaigns')
@EqualsAndHashCode
class CampaignDTO extends BaseEntity {

    @Column(name = 'landing_page')
    String landingPage

}
