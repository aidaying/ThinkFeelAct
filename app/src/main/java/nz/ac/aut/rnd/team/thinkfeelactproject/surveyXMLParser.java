package nz.ac.aut.rnd.team.thinkfeelactproject;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Aida on 8/08/16.
 */
public class SurveyXMLParser {



   /* private static final String ns = null;

    public ArrayList<Survey> parse(XmlResourceParser parser) throws XmlPullParserException, IOException {

        return readSurveys(parser);
    }
    private ArrayList<Survey> readSurveys(XmlPullParser parser) throws XmlPullParserException, IOException{
        ArrayList<Survey> surveys = new ArrayList<Survey>();
        parser.require(XmlPullParser.START_TAG, ns, "surveys");
        while (parser.getEventType() != XmlPullParser.END_TAG){
            if(parser.getEventType()!= XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("survey")){
                surveys.add(readSurvey(parser));
            }else{
                skip(parser);
            }
        }
        return surveys;
    }

    private Survey readSurvey(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, ns, "survey");
        int id = 0;
        String question = "";
        String desc = "";
        while(parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String element = parser.getName();
            if(element.equals("id")){
                id = readID(parser);
            }else if(element.equals("question")){
                question = readQuestion(parser);
            }else if(element.equals("description")){
                desc = readDesc(parser);
            }else{
                skip(parser);
            }
        }

        return new Survey(id, question, desc);
    }

    private int readID(XmlPullParser parser)throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG,ns,"id");
        int id = Integer.valueOf(readText(parser));
        parser.require(XmlPullParser.END_TAG,ns,"id");
        return id;
    }
    private String readQuestion(XmlPullParser parser)throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG,ns,"question");
        String question = readText(parser);
        parser.require(XmlPullParser.END_TAG,ns,"question");
        return question;
    }
    private String readDesc(XmlPullParser parser)throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG,ns,"description");
        String desc = readText(parser);
        parser.require(XmlPullParser.END_TAG,ns,"description");
        return desc;
    }
    private String readText(XmlPullParser parser)throws XmlPullParserException, IOException{
        String content = "";
        if(parser.next() == XmlPullParser.TEXT){
            content = parser.getText();
            parser.nextTag();
        }
        return content;
    }
    private void skip(XmlPullParser parser)throws XmlPullParserException, IOException{
        if(parser.getEventType()!= XmlPullParser.START_TAG){
            throw new IllegalStateException();
        }
        int i = 1;
        while (i != 0){
            switch (parser.next()){
                case XmlPullParser.END_TAG:
                    i--;
                    break;
                case XmlPullParser.START_TAG:
                    i++;
                    break;
            }
        }
    }*/
}
