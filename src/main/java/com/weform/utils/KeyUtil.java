package com.weform.utils;

import java.util.Random;

/**
 * @Author: Kason
 * @Date: 2018/12/11 16:35
 */
public class KeyUtil {

    public static synchronized String createPrimaryKey() {
        Random random = new Random();
        int nextInt = random.nextInt(999) * random.nextInt(999);
        String key = System.currentTimeMillis() + String.valueOf(nextInt);
        return key;
    }

    public static String createNumber(){
        Random random = new Random();
        int nextInt = random.nextInt(99) * random.nextInt(99);
        return String.valueOf(nextInt);
    }

}
