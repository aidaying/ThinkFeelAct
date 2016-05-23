package nz.ac.aut.rnd.team.thinkfeelactproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aida on 20/05/2016.
 */
public class StressCalculator {

    List<Integer> squareResultList;
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
        squareResultList = new ArrayList<Integer>(){};
        for(int rate: rateList){
            temp = (rate - mean) *(rate - mean);
            squareResultList.add(temp);
        }
        return squareResultList;
    }
    private int sumSquareResult (List<Integer> squareResultList){
        for(int rate: squareResultList){
            sum += rate;
        }
        return sum;
    }
    private int getSquaredMean(List<Integer> squareResultList){
        return squareMean = (1/squareResultList.size())*sum;
    }

    public int standardDeviationResult(List<Integer> rateList){
        getMean(rateList);
        squareResult(rateList);
        sumSquareResult(squareResultList);
        result = getSquaredMean(squareResultList);
        return result;
    }
}
