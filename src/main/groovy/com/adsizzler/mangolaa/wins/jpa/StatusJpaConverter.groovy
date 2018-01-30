package com.adsizzler.mangolaa.wins.jpa

import com.adsizzler.mangolaa.wins.domain.enums.Status
import org.springframework.util.Assert

import javax.persistence.AttributeConverter
import javax.persistence.Converter

/**
 * Created by ankushsharma on 29/01/18.
 */
@Converter(autoApply = true)
class StatusJpaConverter implements AttributeConverter<Status, Integer> {

    @Override
    Integer convertToDatabaseColumn(Status attribute) {
        Assert.notNull(attribute, 'status cannot be null')
        attribute.code
    }

    @Override
    Status convertToEntityAttribute(Integer dbData) {
        if(Objects.nonNull(dbData)){
            return Status.from(dbData)
        }
        return Status.UNKNOWN
    }

}
