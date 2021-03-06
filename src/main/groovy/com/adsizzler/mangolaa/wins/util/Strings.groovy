package com.adsizzler.mangolaa.wins.util
/**
 * Created by Ankush on 17/07/17.
 */
class Strings {

     static final String EMPTY = ""

    /**
     * Validate if a String has text in it. Whitespaces are not considered valid text
     * @param text The String that has to be validated
     * @return false if the String does not have text
     */
     static boolean hasText(String text){
        if(!text){
            return false
        }
        for(char ch in text.toCharArray()){
            if(!Character.isWhitespace(ch)){
                return true
            }
        }
        return false
    }

    /**
     * Uses a StringBuilder to build an array of objects. The value returned in the Object's toString method is considered
     * If a null is passed in the array, it is simply ignored
     * @param array the array of objects that will be concatenated
     * @return concatenated String
     */
    static String build(Object... array){
        Assert.notNull(array, "array cannot be null")
        def sb = new StringBuilder()
        for(object in array){
            //Please don't change this to if(!object). The reasoning is this : Let's say you have an Integer object = 0. In this case, if(!object) would evaluate to true
            if(Objects.nonNull(object)){
                sb.append(object.toString())
            }
        }
        return sb.toString()
    }

}
