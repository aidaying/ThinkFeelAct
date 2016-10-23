package nz.ac.aut.rnd.team.thinkfeelactproject.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aida on 20/05/2016.
 */
public class StressCalculator {

    private double mean;


    private double getMean( List<Double> rateList){
        mean=0;
        for(double rate: rateList){
            mean += rate;
        }
        mean = mean/rateList.size();

    return mean;
    }

    public double getWeightedMean(double longTerm, double day){
        return ((longTerm * 0.3) + (day * 0.7));
    }

    public double calculationResult(List<Double> rateList){
       return getMean(rateList);
    }
}
