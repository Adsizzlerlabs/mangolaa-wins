package com.adsizzler.mangolaa.wins.util


/**
 * Helper class that contains static utilies methods to perform validations against Objects
 * Created by Ankush on 17/07/17.
 */
class Assert {

    /**
     * Validate if an Object is null.
     * @param t The parameter against which null check is performed
     * @param errorMsg The errorMsg to show in case @param t is null
     * @throws IllegalArgumentException in case @param t is null
     */
    static <T> void notNull(T t, String errorMsg){
        if(Objects.isNull(t)){
            throw new IllegalArgumentException(errorMsg);
        }
    }

    /**
     * Check if a String is null or empty
     * @param string The string variable against which null and empty check is performed
     * @param errorMsg The errorMsg to show in case @param t is null
     * @throws IllegalArgumentException in case @param string is null or empty
     */
    static void notEmptyString(String string, String errorMsg){
        if(!Strings.hasText(string)){
            throw new IllegalArgumentException(errorMsg);
        }
    }


}
