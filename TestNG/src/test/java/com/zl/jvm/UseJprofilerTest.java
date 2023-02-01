package com.zl.jvm;

import org.testng.annotations.Test;

public class UseJprofilerTest {
    @Test
    public void test(){
        try {
            UseJProfiler.test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
