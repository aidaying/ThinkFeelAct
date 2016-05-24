package nz.ac.aut.rnd.team.thinkfeelactproject;

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

    private static final String QUESTION_TABLE_NAME = "QUESTION";
    private static final String USER_TABLE_NAME = "USER";
    private static final String LT_TABLE_NAME = "INITIAL";
    private static final String EVENT_RECENT_TABLE_NAME = "RECENT_EVENT";
    private static final String EVENT_CURRENT_TABLE_NAME = "CURRENT_EVENT";
    private static final String EMOTIONAL_TABLE_NAME = "EMOTIONAL";
    private static final String PHYSICAL_TABLE_NAME = "PHYSICAL";
    private static final String THOUGHTS_TABLE_NAME = "THOUGHTS";

    //DATABASE RELATED
    private static final String ID = "ID";
    private static final String LTSURVEY_ID = "ID";
    private static final String QUESTION_TYPE = "TYPE";
    private static final String QUESTION_ID = "QUESTION_ID";
    private static final String QUESTION = "QUESTION";
    private static final String USER = "USER";
    private static final String EVENT_NAME = "EVENT_NAME";
    private static final String EMOTIONAL_NAME = "EMOTIONAL_NAME";
    private static final String THOUGHTS_NAME = "THOUGHTS_NAME";
    private static final String PHYSICAL_NAME = "PHYSICAL_NAME";
    private static final String RATING = "RATING";
    private static final String ADDED_DATE = "ADDED_DATE";
    private static final String LAST_ACCESSED_DATE = "LAST_ACCESSED_DATE";
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
        addQuestions();
        isCreating = false;
        dbase= null;
    }
    @Override
    public SQLiteDatabase getWritableDatabase() {
        // TODO Auto-generated method stub
        if(isCreating && dbase != null){
            return dbase;
        }
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        // TODO Auto-generated method stub
        if(isCreating && dbase != null){
            return dbase;
        }
        return super.getReadableDatabase();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE_NAME);
        onCreate(db);
    }
    public List<Survey> getAllQuestion(){
        List<Survey> arrayList = new ArrayList<Survey>();
         dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery("SELECT * FROM " + QUESTION_TABLE_NAME, null);
       if(cursor.moveToFirst()) {
           do {
               Survey survey = new Survey();
               survey.setId(cursor.getInt(0));
               survey.setQuestion(cursor.getString(1));
               survey.setType(cursor.getString(2));
               arrayList.add(survey);
           }while(cursor.moveToNext());
       }
        return arrayList;
    }

    public List<Double> getAlltheRateFromLTSurvey (){
        List<Double> arrayList = new ArrayList<Double>();
          dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery("SELECT * FROM " + LT_TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do {
                LongTermSurvey longTermSurvey = new LongTermSurvey();
                longTermSurvey.setRating(cursor.getInt(3));
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

    public void addQuestion(Survey survey) {
         dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUESTION, survey.getQuestion());
        values.put(QUESTION_TYPE, survey.getType());
        try {
        dbase.insert(QUESTION_TABLE_NAME, null, values);}catch (Exception e){}
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

    private void createTable(SQLiteDatabase db){
        String CREATE_QUESTION_TABLE = "CREATE TABLE "+ QUESTION_TABLE_NAME + "("
                + QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION + " TEXT, " + QUESTION_TYPE + " TEXT)";
        db.execSQL(CREATE_QUESTION_TABLE);

        String CREATE_USER_TABLE = "CREATE TABLE "+ USER_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER + " TEXT, " + ADDED_DATE + " TEXT, " + LAST_ACCESSED_DATE + " TEXT )";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_LTSURVEY_TABLE = "CREATE TABLE "+ LT_TABLE_NAME + "("
                + LTSURVEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ANSWERED_TRUE + " TEXT, " + RATING + " DOUBLE, "
                + QUESTION_ID + " INTEGER, FOREIGN KEY("+QUESTION_ID+") REFERENCES "+QUESTION_TABLE_NAME + "("+QUESTION_ID +"))";
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

    private void addQuestions(){
        Survey s1 = new Survey("Death of a significant person? I.e., a spouse, friend, relative, or family member.","Self");
        addQuestion(s1);
        Survey s2 = new Survey("Separation or Divorce","Self");
        addQuestion(s2);
        Survey s3 = new Survey("Serious sickness or injury (yourself)","Self");
        addQuestion(s3);
        Survey s4 = new Survey("Serious sickness or injury (companion)","Self");
        addQuestion(s4);
        Survey s5 = new Survey("Not enough money/ heavy debt?","Self");
        addQuestion(s5);
        Survey s6 = new Survey("Conflict with family, friends or in-laws?","Self");
        addQuestion(s6);
        Survey s7 = new Survey("Problems with Child Care?","Self");
        addQuestion(s7);
        Survey s8 = new Survey("Sexual Conflict or frustration?","Self");
        addQuestion(s8);
        Survey s9 = new Survey("Conflict with spiritual, moral or ethical values?","Self");
        addQuestion(s9);
        Survey s10 = new Survey("Detention in jail or other institution?","Self");
        addQuestion(s10);
        Survey s11 = new Survey("Son or daughter leaving home?","Self");
        addQuestion(s11);
    }

}
