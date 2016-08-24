package nz.ac.aut.rnd.team.thinkfeelactproject.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aida on 20/05/2016.
 */
public class StressCalculator {

    private List<Double> squareResultList;
    private double temp;
    private double mean;
    private double sum;
    private double squareMean;
    private double result;

    private double getMean( List<Double> rateList){
        mean=0;
        for(double rate: rateList){
            mean += rate;
        }
        mean = mean/rateList.size();

    return mean;
    }

    private List<Double> squareResult(List<Double> rateList){
        squareResultList = new ArrayList<Double>();
        for(double rate: rateList){
            temp = (rate - mean) *(rate - mean);
            squareResultList.add(temp);
        }
        return squareResultList;
    }
    private double sumSquareResult (){
        for(double rate: squareResultList){
            sum += rate;
        }
        return sum;
    }
    private double getSquaredMean(){
        return squareMean = (sum/squareResultList.size());
    }

    public double standardDeviationResult(List<Double> rateList){
        //getMean(rateList);
        //squareResult(rateList);
        //sumSquareResult();
        result = getMean(rateList);
        return result;
    }
}
