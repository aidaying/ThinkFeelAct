package nz.ac.aut.rnd.team.thinkfeelactproject.timeoutmodel.exercise;


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
import nz.ac.aut.rnd.team.thinkfeelactproject.java.ExerciseItem;


public class Exercise extends Fragment {

    private ExerciseListAdapter exerciseListAdapter;
    private ArrayList<ExerciseItem> expandListItems;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View exerciseView = inflater.inflate(R.layout.fragment_exercise,container,false);

        XMLPullParser();

        exerciseListAdapter = new ExerciseListAdapter(getActivity(), expandListItems);
        ListView listView = (ListView) exerciseView.findViewById(R.id.exerciseList);
        listView.setAdapter(exerciseListAdapter);

        return exerciseView;
    }



    private void XMLPullParser(){
        XmlPullParserFactory pullParserFactory;
        try{
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream inputStream = getResources().openRawResource(R.raw.exercisenamevalue);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(inputStream,null);
            parserXML(parser);
        }catch(XmlPullParserException | IOException e){
            e.printStackTrace();
        }

    }

    private void parserXML(XmlPullParser parser) throws XmlPullParserException, IOException{

        int eventType = parser.getEventType();
        ExerciseItem exerciseItem = null;
        expandListItems = new ArrayList<ExerciseItem>();

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("exercise")){
                        exerciseItem = new ExerciseItem();
                    }else if(exerciseItem != null) {
                        switch(name){
                            case "name":
                                exerciseItem.setImage(parser.nextText());
                                break;
                            case "explanation":
                                exerciseItem.setName(parser.nextText());
                                break;

                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if(name.equalsIgnoreCase("exercise")&& exerciseItem != null) {
                        expandListItems.add(exerciseItem);
                    }
            }
            eventType = parser.next();
        }
    }
}
