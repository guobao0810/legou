package com.legou.common.utils;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class IDUtils {

    public static Long getId(){
        Long time = System.currentTimeMillis();
        Random random = new Random();
        int num = random.nextInt(99)+10;
        return Long.valueOf(time + String.valueOf(num));
    }

}
