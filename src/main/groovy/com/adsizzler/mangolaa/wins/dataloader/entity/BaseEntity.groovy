package com.adsizzler.mangolaa.wins.dataloader.entity

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass


/**
 * Base class that contains fields that are common to all JPA Entity classes
 * Created by ankushsharma on 29/01/18.
 */
@MappedSuperclass
class BaseEntity {

    @Id
    @GeneratedValue
    Integer id

}
