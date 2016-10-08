package nz.ac.aut.rnd.team.thinkfeelactproject.java;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.audiofx.AudioEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aida on 12/05/2016.
 *
 * needs to fix the database.......after the SE model is updated....
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "THINK_FEEL_ACT";
    SQLiteDatabase dbase = null;

    //DATABASE RELATED
    private static final String LT_TABLE_NAME = "INITIAL";
    private static final String LTSURVEY_ID = "ID";
    private static final String QUESTION_ID = "QUESTION_ID";
    private static final String RATING = "RATING";
    private static final String ADDED_DATE = "ADDED_DATE";
    private static final String ANSWERED_TRUE = "FIRST_ANSWER";

    private static final String EVENT_TABLE_NAME = "EVENT";
    private static final String EVENT_ID = "EVENT_ID";
    private static final String EVENT_NAME = "EVENT_NAME";
    private static final String EVENT_EMOTIONAL = "EVENT_MOOD";
    private static final String EVENT_PHYSICAL = "EVENT_PHYSICAL";
    private static final String EVENT_THOUGHTS_WHAT = "EVENT_THOUGHTS_WHAT";
    private static final String EVENT_THOUGHTS_WHY_HOW = "EVENT_THOUGHTS_WHY_HOW";
    private static final String EVENT_THOUGHTS_FEEL = "EVENT_THOUGHTS_FEEL";
    private static final String EVENT_DATE = "EVENT_DATE";
    private static final String EVENT_RATING = "EVENT_RATING";

    private static final String SOS_DESCRIPTION_TABLE_NAME = "SOS DESC";
    private static final String SOS_DESC_ID = "DESC_ID";
    private static final String SOS_DESC = "DESCRIPTION";
    private static final String SOS_DESC_TYPE = "TYPE";
    private static final String SOS_DESC_SELECTED_VALUE = "SELECTED_VALUE";
    private static final String SOS_DESC_NUM = "NUMBER";

    boolean isCreating = false;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        isCreating = true;
        dbase = db;
        createTable(dbase);
        isCreating = false;
        dbase= null;
    }
    @Override
    public SQLiteDatabase getWritableDatabase() {
        if(isCreating && dbase != null){
            return dbase;
        }
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        if(isCreating && dbase != null){
            return dbase;
        }
        return super.getReadableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


    public List<Double> getAlltheRateFromLTSurvey (){
        List<Double> arrayList = new ArrayList<>();
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery("SELECT * FROM " + LT_TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do {
                LongTermSurvey longTermSurvey = new LongTermSurvey();
                longTermSurvey.setRating(cursor.getInt(2));
                arrayList.add(longTermSurvey.getRating());
            }while(cursor.moveToNext());
        }
        return arrayList;
    }

    public List<Double> getAllTheRateFromEvent (){
        List<Double> arrayList = new ArrayList<Double>();
         dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery("SELECT * FROM " + EVENT_TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setRating(cursor.getInt(3));
                arrayList.add(event.getRating());
            }while(cursor.moveToNext());
        }
        return arrayList;
    }



    public void addEvent(Event event) {
         dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event.getName());
        values.put(EVENT_EMOTIONAL, event.getEmotion());
        values.put(EVENT_PHYSICAL, event.getPain());
        values.put(EVENT_THOUGHTS_WHAT, event.getThoughtwhat());
        values.put(EVENT_THOUGHTS_WHY_HOW, event.getThoughtwhyhow());
        values.put(EVENT_THOUGHTS_FEEL, event.getThoughtfeel());
        values.put(EVENT_DATE, event.getDate());
        values.put(EVENT_RATING, event.getRating());
        try {
            dbase.insert(EVENT_TABLE_NAME, null, values);
        }catch (Exception e){}
    }
    public void addFirstTimeEvent(Event event) {
        dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event.getName());
        values.put(EVENT_RATING, event.getRating());
        try {
            dbase.insert(EVENT_TABLE_NAME, null, values);
        }catch (Exception e){}
    }

    public void addLongTermSurvey(LongTermSurvey longTermSurvey) {
          dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ANSWERED_TRUE, longTermSurvey.getAnswerTF());
        values.put(RATING, longTermSurvey.getRating());
        values.put(QUESTION_ID, longTermSurvey.getQuestionId());
        try{
        dbase.insert(LT_TABLE_NAME, null, values);}catch(Exception e){}
    }

    public void addNewDescInfo(Desc desc) {
        dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SOS_DESC, desc.getDesc());
        values.put(SOS_DESC_TYPE, desc.getType());
        values.put(SOS_DESC_SELECTED_VALUE,desc.getSelectedValue());
        values.put(SOS_DESC_NUM, desc.getNum());
        try{
            dbase.insert(SOS_DESCRIPTION_TABLE_NAME, null, values);}catch(Exception e){}
    }

    public void alterDescInfo(Desc desc) {

        dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SOS_DESC_NUM, desc.getNum());
        String where =  SOS_DESC_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(desc.getId())};

        dbase.update(SOS_DESCRIPTION_TABLE_NAME, values, where, whereArgs);

    }

    public ArrayList<Desc> getAllNewDescInfo (){
        ArrayList<Desc> arrayList = new ArrayList<Desc>();
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery("SELECT * FROM " + SOS_DESCRIPTION_TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do {
                Desc desc = new Desc();
                desc.setId(cursor.getInt(0));
                desc.setDesc(cursor.getString(1));
                desc.setType(cursor.getString(2));
                desc.setSelectedValue(cursor.getFloat(3));
                desc.setNum(cursor.getInt(4));
                arrayList.add(desc);
            }while(cursor.moveToNext());
        }
        return arrayList;
    }
    public boolean isDescEmpty(){

        dbase = this.getReadableDatabase();

        String count = "SELECT COUNT(*) FROM "+ SOS_DESCRIPTION_TABLE_NAME;

        Cursor cursor = dbase.rawQuery(count,null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        if(i > 0){
            return true;
        }
        // result is more than 0 if item exists
        return false;
    }

    private void createTable(SQLiteDatabase db){

        String CREATE_LTSURVEY_TABLE = "CREATE TABLE "+ LT_TABLE_NAME + "("
                + LTSURVEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ANSWERED_TRUE + " TEXT, " + RATING + " DOUBLE, "
                + QUESTION_ID + " INTEGER)";
        db.execSQL(CREATE_LTSURVEY_TABLE);

        String CREATE_EVENT_CURRENT_TABLE = "CREATE TABLE "+ EVENT_TABLE_NAME + "("
                + EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EVENT_NAME + " TEXT, " + EVENT_EMOTIONAL + " TEXT, " + EVENT_PHYSICAL + " TEXT, "
                + EVENT_THOUGHTS_WHAT + " TEXT, " + EVENT_THOUGHTS_WHY_HOW + " TEXT, " + EVENT_THOUGHTS_FEEL + " TEXT, "+ EVENT_DATE + " TEXT, " + EVENT_RATING + " TEXT)";
        db.execSQL(CREATE_EVENT_CURRENT_TABLE);

        String CREATE_DESC_TABLE = "CREATE TABLE "+ SOS_DESCRIPTION_TABLE_NAME + "("
                + SOS_DESC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SOS_DESC + " TEXT, " + SOS_DESC_TYPE + " TEXT, " + SOS_DESC_SELECTED_VALUE + " FLOAT, " + SOS_DESC_NUM + " INTEGER)";
        db.execSQL(CREATE_DESC_TABLE);
    }


}
