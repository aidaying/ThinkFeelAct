package nz.ac.aut.rnd.team.thinkfeelactproject.timelinemodel;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Aida on 21/10/2016.
 */

public class TabsPagerAdaptor extends FragmentPagerAdapter {

    private int numTabs;

    public TabsPagerAdaptor(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LongTermResultFragment tab1 = new LongTermResultFragment();
                return tab1;
            case 1:
                SelfEvaluationResultFragment tab2 = new SelfEvaluationResultFragment();
                return tab2;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }

}