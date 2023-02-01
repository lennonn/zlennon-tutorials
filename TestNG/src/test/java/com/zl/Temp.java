package com.zl;

import org.testng.annotations.Test;

import java.util.Arrays;

public class Temp {
    @Test
    public void test(){
        String tmp ="[\"2222\",\"33333\"]";
        String sub =tmp.substring(1,tmp.length()-1);
        String arr[] = sub.split(",");
        System.out.println(Arrays.toString(arr));
        int SHARED_SHIFT   = 16;
        //读锁?单位
        int SHARED_UNIT    = (1 << SHARED_SHIFT);
        System.out.println(SHARED_UNIT);
        //读锁?的最大数量
        int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
        System.out.println(MAX_COUNT);
        //写锁?的最大数量
        int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
        System.out.println(EXCLUSIVE_MASK);
        System.out.println(1>>>16);

    }




}
