package nz.ac.aut.rnd.team.thinkfeelactproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aida on 20/05/2016.
 */
public class StressCalculator {

    List<Double> squareResultList;
    double temp;
    double mean;
    double sum;
    double squareMean;
    double result;

    private Double getMean( List<Double> rateList){
        mean=0;
        for(double rate: rateList){
            mean += rate;
        }
        mean = mean/rateList.size();

    return mean;
    }

    private List<Double> squareResult(List<Double> rateList){
        squareResultList = new ArrayList<Double>(){};
        for(double rate: rateList){
            temp = (rate - mean) *(rate - mean);
            squareResultList.add(temp);
        }
        return squareResultList;
    }
    private Double sumSquareResult (){
        for(double rate: squareResultList){
            sum += rate;
        }
        return sum;
    }
    private Double getSquaredMean(){
        return squareMean = (1/squareResultList.size())*sum;
    }

    public Double standardDeviationResult(List<Double> rateList){
        getMean(rateList);
        squareResult(rateList);
        sumSquareResult();
        result = Math.sqrt(squareMean);
        return result;
    }
}
