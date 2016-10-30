package nz.ac.aut.rnd.team.thinkfeelactproject.sosmodel;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.DatabaseHandler;
import nz.ac.aut.rnd.team.thinkfeelactproject.java.Desc;

/**
 * SOS model is the Same old Stuff keep happening model
 * This model has implemented a pie chart where user can see the Phase of the cycle
 * that will happen to the person after they have repeated the event that might be violence, or have suicide thoughts, etc.
 * ////////////////////////////////////Implemented//////////////////////////////////////////
 * XML parser which gets the data from the /raw/sospie.xml to generate the pie chart details.
 * Pie chart that is using MPChart open source plugin found in GitHub
 * Linked to Database to store all the event that is inputted by users
 * Implemented dialogs to show the view and the other one for adding the data.
 */
public class SOSModelActivity extends Activity {

    private DatabaseHandler mydb;
    private RelativeLayout relativeLayout;
    private PieChart sosSelection;
    private float[]  yData;
    private String[] xData;
    private sosDescListAdapter sosDescListAdapter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosmodel);
        mydb = new DatabaseHandler(getApplicationContext());

        XmlPullParserFactory pullParserFactory;
        try{
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream inputStream = getResources().openRawResource(R.raw.sospievalue);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(inputStream,null);
            parserXML(parser);
        }catch(XmlPullParserException | IOException e){
            e.printStackTrace();
        }
        //////////////////pie Chart Implementation start/////////////////////
        relativeLayout = (RelativeLayout) findViewById(R.id.sospie);
        sosSelection = new PieChart(this);

        relativeLayout.addView(sosSelection);
        sosSelection.setEntryLabelColor(Color.BLACK);
        sosSelection.setUsePercentValues(true);
        sosSelection.setDescription("");
        sosSelection.setDrawHoleEnabled(true);
        sosSelection.setHoleColor(Color.TRANSPARENT);
        sosSelection.setHoleRadius(30);
        sosSelection.setTransparentCircleRadius(30);

        sosSelection.setRotationAngle(0);
        sosSelection.setRotationEnabled(false);


        ViewGroup.LayoutParams params = sosSelection.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;


        sosSelection.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                //////////////////First Dialog///////////////
                final Dialog dialog = new Dialog(SOSModelActivity.this);
                dialog.setContentView(R.layout.fragment_sos_dialog);
                dialog.setTitle("Details");
                final Button addNewBtn = (Button) dialog.findViewById(R.id.addNewSosEventBtn);
                Button close = (Button) dialog.findViewById(R.id.closeBtn);
                updateList(dialog);
                dialog.show();
                addNewBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //////////////////Second Dialog/////////////////////////////////////
                        final Dialog addNewDialog = new Dialog(SOSModelActivity.this);
                        addNewDialog.setContentView(R.layout.addnewdesclayout);
                        addNewDialog.setTitle("Add New");
                        final Button addNewDescBtn = (Button) addNewDialog.findViewById(R.id.addNewDescBtn);
                        Button cancelAddNewDescBtn = (Button) addNewDialog.findViewById(R.id.cancelAddNewDescBtn);
                        final EditText addNewDesc = (EditText) addNewDialog.findViewById(R.id.descInput);
                        addNewDialog.show();
                        addNewDescBtn.setEnabled(false);
                        addNewDesc.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                addNewDescBtn.setEnabled(s.toString().trim().length() > 0);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                            }
                        });
                        addNewDescBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String type = "SOS";
                                String desc = addNewDesc.getText().toString();
                                int num = 1;
                                Desc desc1 = new Desc();
                                desc1.setDesc(desc);
                                desc1.setType(type);

                                desc1.setNum(num);
                                mydb.addNewDescInfo(desc1);
                                updateList(dialog);
                                addNewDialog.dismiss();
                            }
                        });

                        cancelAddNewDescBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                addNewDialog.cancel();
                            }
                        });
                    }

                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void onNothingSelected() {

            }
        });

        addData();
        //////////////////pie Chart Implementation end/////////////////////
    }


    /**
     * This method is linked to the sosDescListAdaptor to update the list
     * sosDescListAdaptor is a class to set up each row of the data in the list
     *
     * @param dialog
     */
    private void updateList(Dialog dialog){
        if(mydb.isDescEmpty() == true) {
            ArrayList<Desc> descList = mydb.getAllNewDescInfo();
            ArrayList<Desc> list = new ArrayList<Desc>();
            for(int i = 0; i < descList.size(); i++){

                list.add(descList.get(i));

            }
            sosDescListAdapter = new sosDescListAdapter(dialog.getContext(), list);
            ListView listView = (ListView) dialog.findViewById(R.id.sosListView);
            listView.setAdapter(sosDescListAdapter);
        }else{
            Toast.makeText(getApplicationContext(), "Add new Description first", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is used to set up the pie chart data and colour of each portion of the chart
     */
    private void addData(){

        ArrayList<PieEntry> yVals1 = new ArrayList<PieEntry>();
        for(int i = 0; i < yData.length; i++){
            yVals1.add(new PieEntry(yData[i],xData[i]));
        }

        PieDataSet pieDataSet = new PieDataSet(yVals1,"SOS Model");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(6);


        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c : ColorTemplate.JOYFUL_COLORS){
            colors.add(c);
        }
        for(int c : ColorTemplate.JOYFUL_COLORS){
            colors.add(c);
        }

        colors.add(ColorTemplate.getHoloBlue());
        pieDataSet.setColors(colors);

        PieData pieData = new PieData();
        pieData.setDataSet(pieDataSet);
        pieData.setValueTextColor(Color.TRANSPARENT);

        sosSelection.setData(pieData);
        sosSelection.highlightValue(null);
        sosSelection.invalidate();
    }


    private void parserXML(XmlPullParser parser) throws XmlPullParserException, IOException{
        int i = 0;
        int j = 0;
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    yData = new float[10];
                    xData = new String[10];
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("size")){
                        yData[i] = Float.valueOf(parser.nextText());
                        i++;
                    }
                    if(name.equals("detail")){
                        xData[j] = parser.nextText();
                        j++;
                    }
                    break;
                case XmlPullParser.END_TAG:

            }
            eventType = parser.next();
        }

    }
}
