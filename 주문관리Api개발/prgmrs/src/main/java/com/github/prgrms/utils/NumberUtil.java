package com.github.prgrms.utils;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtil extends NumberUtils{
    
    public static boolean isBig(int value, int max){
        boolean r = false;

        if(value > max){
            r = true;
        }

        return r;
    }
}