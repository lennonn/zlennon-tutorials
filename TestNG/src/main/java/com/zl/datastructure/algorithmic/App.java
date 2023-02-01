package com.zl.datastructure.algorithmic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        System.out.println(Factorial.calNFactorial(Integer.valueOf(args[0])));

    }
}
