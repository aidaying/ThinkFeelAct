package nz.ac.aut.rnd.team.thinkfeelactproject.timelinemodel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.LongTermSurvey;

public class LongTermResultFragment extends Fragment {
    private LongTermResultListAdaptor longTermResultListAdaptor;
    private ArrayList<LongTermSurvey> longTermSurveyArrayList;
    private DatabaseHandler mydb;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View longTermResultView = inflater.inflate(R.layout.fragment_long_term_result,container,false);
        mydb = new DatabaseHandler(getActivity());
        XMLPullParser();

        setupLTResultList(longTermResultView);

        return longTermResultView;
    }


    private void setupLTResultList(View longTermResultView){
        ArrayList<LongTermSurvey> showResult = new ArrayList<LongTermSurvey>();
        ArrayList<LongTermSurvey> dbSavedList = mydb.getAllLTSurvey();

        for(LongTermSurvey l : dbSavedList){

                LongTermSurvey displaySurveyItem = new LongTermSurvey();
                displaySurveyItem.setID(l.getID());
                displaySurveyItem.setQuestion(longTermSurveyArrayList.get(l.getID()-1).getQuestion());
                displaySurveyItem.setRating(l.getRating());
                showResult.add(displaySurveyItem);

        }
        longTermResultListAdaptor = new LongTermResultListAdaptor(getActivity(), showResult);
        ListView listView = (ListView) longTermResultView.findViewById(R.id.longTermResultList);
        listView.setAdapter(longTermResultListAdaptor);
    }


    private void XMLPullParser(){
        XmlPullParserFactory pullParserFactory;
        try{
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream inputStream = getResources().openRawResource(R.raw.surveys);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(inputStream,null);
            parserXML(parser);
        }catch(XmlPullParserException | IOException e){
            e.printStackTrace();
        }

    }

    private void parserXML(XmlPullParser parser) throws XmlPullParserException, IOException{

        int eventType = parser.getEventType();
        LongTermSurvey longTermSurvey = null;
        longTermSurveyArrayList = new ArrayList<LongTermSurvey>();

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("survey")){
                        longTermSurvey = new LongTermSurvey();
                    }else if(longTermSurvey != null) {
                        switch(name){
                            case "id":
                                longTermSurvey.setID(Integer.valueOf(parser.nextText()));
                                break;
                            case "question":
                                longTermSurvey.setQuestion(parser.nextText());
                                break;
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if(name.equalsIgnoreCase("survey")&& longTermSurvey != null) {
                        longTermSurveyArrayList.add(longTermSurvey);
                    }
            }
            eventType = parser.next();
        }
    }
}
