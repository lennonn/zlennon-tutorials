package com.zl.ifelse;

public class StrategySecond implements Strategy {

    @Override
    public void apply(Integer type) {
        System.out.println(type);
    }
}
