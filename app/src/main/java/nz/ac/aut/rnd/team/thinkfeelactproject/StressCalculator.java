package nz.ac.aut.rnd.team.thinkfeelactproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aida on 20/05/2016.
 */
public class StressCalculator {

    List<Integer> sqaureResultList;
    int temp;
    int mean;
    int sum;
    int squareMean;
    int result;

    private int getMean( List<Integer> rateList){
        mean=0;
        for(int rate: rateList){
            mean += rate;
        }
        mean = mean/rateList.size();
    return mean;
    }

    private List<Integer> squareResult(List<Integer> rateList){
        sqaureResultList = new ArrayList<Integer>(){};
        for(int rate: rateList){
            temp = (rate - mean) *(rate - mean);
            sqaureResultList.add(temp);
        }
        return sqaureResultList;
    }
    private int sumSquareResult (List<Integer> sqaureResultList){
        for(int rate: sqaureResultList){
            sum += rate;
        }
        return sum;
    }
    private int getSquaredMean(List<Integer> sqaureResultList){
        return squareMean = (1/sqaureResultList.size())*sum;
    }

    public int standardDeviationResult(List<Integer> rateList){
        getMean(rateList);
        squareResult(rateList);
        sumSquareResult(sqaureResultList);
        result = getSquaredMean(sqaureResultList);
        return result;
    }
}
