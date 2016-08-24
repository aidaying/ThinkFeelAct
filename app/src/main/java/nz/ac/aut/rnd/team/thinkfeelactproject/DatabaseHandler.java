package nz.ac.aut.rnd.team.thinkfeelactproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aida on 12/05/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "THINK_FEEL_ACT";
    SQLiteDatabase dbase = null;


    private static final String LT_TABLE_NAME = "INITIAL";
    private static final String EVENT_RECENT_TABLE_NAME = "RECENT_EVENT";
    private static final String EVENT_CURRENT_TABLE_NAME = "CURRENT_EVENT";
    private static final String EMOTIONAL_TABLE_NAME = "EMOTIONAL";
    private static final String PHYSICAL_TABLE_NAME = "PHYSICAL";
    private static final String THOUGHTS_TABLE_NAME = "THOUGHTS";

    //DATABASE RELATED
    private static final String ID = "ID";
    private static final String LTSURVEY_ID = "ID";
    private static final String QUESTION_ID = "QUESTION_ID";
    private static final String EVENT_NAME = "EVENT_NAME";
    private static final String EMOTIONAL_NAME = "EMOTIONAL_NAME";
    private static final String THOUGHTS_NAME = "THOUGHTS_NAME";
    private static final String PHYSICAL_NAME = "PHYSICAL_NAME";
    private static final String RATING = "RATING";
    private static final String ADDED_DATE = "ADDED_DATE";
    private static final String ANSWERED_TRUE = "FIRST_ANSWER";
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
        Cursor cursor = dbase.rawQuery("SELECT * FROM " + EVENT_CURRENT_TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setRating(cursor.getInt(3));
                arrayList.add(event.getRating());
            }while(cursor.moveToNext());
        }
        return arrayList;
    }



    public void addEventCurrent(Event event) {
         dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event.getName());
        values.put(ADDED_DATE, event.getDate());
        values.put(RATING, event.getRating());
        try {
            dbase.insert(EVENT_CURRENT_TABLE_NAME, null, values);
        }catch (Exception e){}
    }
    public void addEventPast(Event event) {
           dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event.getName());
        values.put(ADDED_DATE, event.getDate());
        values.put(RATING, event.getRating());
        try{
        dbase.insert(EVENT_CURRENT_TABLE_NAME, null, values);}catch(Exception e){}
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

    public boolean isEventCurrentEmpty(){

        dbase = this.getReadableDatabase();

        String count = "SELECT COUNT(*) FROM "+ EVENT_CURRENT_TABLE_NAME;

        Cursor cursor = dbase.rawQuery(count,null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        if(i>3){
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

        String CREATE_EVENT_RECENT_TABLE = "CREATE TABLE "+ EVENT_RECENT_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EVENT_NAME + " TEXT, " + ADDED_DATE + " TEXT, " + RATING + " DOUBLE)";
        db.execSQL(CREATE_EVENT_RECENT_TABLE);

        String CREATE_EVENT_CURRENT_TABLE = "CREATE TABLE "+ EVENT_CURRENT_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EVENT_NAME + " TEXT, " + ADDED_DATE + " TEXT, " + RATING + " DOUBLE)";
        db.execSQL(CREATE_EVENT_CURRENT_TABLE);

        String CREATE_EMOTIONAL_TABLE = "CREATE TABLE "+ EMOTIONAL_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMOTIONAL_NAME + " TEXT, " + ADDED_DATE + " TEXT, " + RATING + " DOUBLE)";
        db.execSQL(CREATE_EMOTIONAL_TABLE);

        String CREATE_PHYSICAL_TABLE = "CREATE TABLE "+ PHYSICAL_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PHYSICAL_NAME + " TEXT, " + ADDED_DATE + " TEXT, " + RATING + " DOUBLE)";
        db.execSQL(CREATE_PHYSICAL_TABLE);

        String CREATE_THOUGHTS_TABLE = "CREATE TABLE "+ THOUGHTS_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + THOUGHTS_NAME + " TEXT, " + ADDED_DATE + " TEXT, " + RATING + " DOUBLE)";
        db.execSQL(CREATE_THOUGHTS_TABLE);

    }


}
