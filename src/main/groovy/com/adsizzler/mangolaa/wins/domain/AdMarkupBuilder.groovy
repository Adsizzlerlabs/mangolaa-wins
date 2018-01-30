package com.adsizzler.mangolaa.wins.domain

import com.adsizzler.mangolaa.wins.util.Assert

/**
 * Created by ankushsharma on 30/01/18.
 */
class AdMarkupBuilder {

    private final String markupTemplate

    AdMarkupBuilder(String markupTemplate){
        Assert.notEmptyString(markupTemplate, 'markupTemplate cannot be empty')
        this.markupTemplate = markupTemplate
    }

    String build(){
        ""
    }



}
