package com.zl.math34;

import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.rng.UniformRandomProvider;

public class Random {
    public static double getGaussianRandomGenerator(RandomGenerator generator){
        GaussianRandomGenerator grg =new GaussianRandomGenerator(generator);
        return grg.nextNormalizedDouble();
    }
}
