package com.infomagnetic;

import com.infomagnetic.inverseproblem.CEA.Individual;

import java.io.Serializable;

/**
 * Created by yutongpang on 8/24/15.
 */
public class PopulationInfo implements Serializable {
    public int loop = 0;
    public int numberofThread;
    public int populationLengthPerThread;
    public Individual[][] individuals;
    public PopulationInfo(Individual[][] individuals, int loop, int numberofThread, int populationPerThread){
        this.individuals = individuals;
        this.loop = loop;
        this.numberofThread = numberofThread;
        this.populationLengthPerThread = populationPerThread;
    }
}
