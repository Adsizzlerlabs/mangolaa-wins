package com.adsizzler.mangolaa.wins.dataloader.dto

import com.adsizzler.mangolaa.wins.domain.enums.Status
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by ankushsharma on 29/01/18.
 */
@ToString(includePackage = false)
@Entity
@Table(name = 'creatives')
@EqualsAndHashCode
class CreativeDTO extends BaseEntity {

    @Column(name = 'markup_template')
    String markupTemplate

    @Column(name = 'status')
    Status status

    @Column(name = 'campaign_id')
    Integer campaignId

    @Column(name = 'adv_id')
    Integer advId

    @Column(name = 'client_id')
    Integer clientId
}
