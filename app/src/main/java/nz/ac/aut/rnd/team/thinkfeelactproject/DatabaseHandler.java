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
    private SQLiteDatabase dbase;

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
    private static final String FIRSTTIME = "FIRST_USED";
    private static final String ANSWERED_TRUE = "FIRST_ANSWER";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        createTable(db);
        addQuestions();
        db.close();
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

    public void addQuestion(Survey survey) {
        ContentValues values = new ContentValues();
        values.put(QUESTION, survey.getQuestion());
        values.put(QUESTION_TYPE, survey.getType());
        dbase.insert(QUESTION_TABLE_NAME, null, values);
    }

    public void addEventCurrent(Event event) {
        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event.getName());
        values.put(ADDED_DATE, event.getDate());
        values.put(RATING, event.getRating());
        try {
            dbase.insert(EVENT_CURRENT_TABLE_NAME, null, values);
        }catch (Exception e){}
    }
    public void addEventPast(Event event) {
        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event.getName());
        values.put(ADDED_DATE, event.getDate());
        values.put(RATING, event.getRating());
        dbase.insert(EVENT_CURRENT_TABLE_NAME, null, values);
    }

    public void addLongTermSurvey(LongTermSurvey longTermSurvey) {
        ContentValues values = new ContentValues();
        values.put(ANSWERED_TRUE, longTermSurvey.getAnswerTF());
        values.put(RATING, longTermSurvey.getRating());
        values.put(QUESTION_ID, longTermSurvey.getQuestionId());
        dbase.insert(LT_TABLE_NAME, null, values);
    }

    private void createTable(SQLiteDatabase db){
        String CREATE_QUESTION_TABLE = "CREATE TABLE "+ QUESTION_TABLE_NAME + "("
                + QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION + " TEXT, " + QUESTION_TYPE + " TEXT)";
        db.execSQL(CREATE_QUESTION_TABLE);

        String CREATE_USER_TABLE = "CREATE TABLE "+ USER_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER + " TEXT, " + ADDED_DATE + " TEXT, " + LAST_ACCESSED_DATE + " TEXT )";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_LTSURVEY_TABLE = "CREATE TABLE "+ LT_TABLE_NAME + "("
                + LTSURVEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ANSWERED_TRUE + " TEXT, " + RATING + " INTEGER, "
                + QUESTION_ID + " INTEGER, FOREIGN KEY("+QUESTION_ID+") REFERENCES "+QUESTION_TABLE_NAME + "("+QUESTION_ID +"))";
        db.execSQL(CREATE_LTSURVEY_TABLE);

        String CREATE_EVENT_RECENT_TABLE = "CREATE TABLE "+ EVENT_RECENT_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EVENT_NAME + " TEXT, " + ADDED_DATE + " TEXT, " + RATING + " INTEGER)";
        db.execSQL(CREATE_EVENT_RECENT_TABLE);

        String CREATE_EVENT_CURRENT_TABLE = "CREATE TABLE "+ EVENT_CURRENT_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EVENT_NAME + " TEXT, " + ADDED_DATE + " TEXT, " + RATING + " INTEGER)";
        db.execSQL(CREATE_EVENT_CURRENT_TABLE);

        String CREATE_EMOTIONAL_TABLE = "CREATE TABLE "+ EMOTIONAL_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMOTIONAL_NAME + " TEXT, " + ADDED_DATE + " TEXT, " + RATING + " INTEGER)";
        db.execSQL(CREATE_EMOTIONAL_TABLE);

        String CREATE_PHYSICAL_TABLE = "CREATE TABLE "+ PHYSICAL_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PHYSICAL_NAME + " TEXT, " + ADDED_DATE + " TEXT, " + RATING + " INTEGER)";
        db.execSQL(CREATE_PHYSICAL_TABLE);

        String CREATE_THOUGHTS_TABLE = "CREATE TABLE "+ THOUGHTS_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + THOUGHTS_NAME + " TEXT, " + ADDED_DATE + " TEXT, " + RATING + " INTEGER)";
        db.execSQL(CREATE_THOUGHTS_TABLE);

    }

    private void addQuestions(){
        Survey s1 = new Survey("Death of a significant person? I.e., a spouse, friend, relative, or family member.","Self");
        this.addQuestion(s1);
        Survey s2 = new Survey("Separation or Divorce","Self");
        this.addQuestion(s2);
        Survey s3 = new Survey("Serious sickness or injury (yourself)","Self");
        this.addQuestion(s3);
        Survey s4 = new Survey("Serious sickness or injury (companion)","Self");
        this.addQuestion(s4);
        Survey s5 = new Survey("Not enough money/ heavy debt?","Self");
        this.addQuestion(s5);
        Survey s6 = new Survey("Conflict with family, friends or in-laws?","Self");
        this.addQuestion(s6);
        Survey s7 = new Survey("Problems with Child Care?","Self");
        this.addQuestion(s7);
        Survey s8 = new Survey("Sexual Conflict or frustration?","Self");
        this.addQuestion(s8);
        Survey s9 = new Survey("Conflict with spiritual, moral or ethical values?","Self");
        this.addQuestion(s9);
        Survey s10 = new Survey("Detention in jail or other institution?","Self");
        this.addQuestion(s10);
        Survey s11 = new Survey("Son or daughter leaving home?","Self");
        this.addQuestion(s11);

        /*String THIRDTEEN = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Major change in family when they get-together?' , 'self'  )";
        db.execSQL(THIRDTEEN);
        String FOURTEEN = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Major change in social activities?' , 'self'  )";
        db.execSQL(FOURTEEN);
        String FIFTHTEEN = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Minor violation of the law?' , 'self'  )";
        db.execSQL(FIFTHTEEN);
        String EIGHTEEN = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Major change in the number of arguments with spouse(i.e., child related, personal habits, etc.)' , 'self' )";
        db.execSQL(EIGHTEEN);
        String NINETEEN = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Gaining a new family member.' , 'self'  )";
        db.execSQL(NINETEEN);
        String TWENTY = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Taking on mortgage?' , 'self'  )";
        db.execSQL(TWENTY);
        String T_ONE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Being betrayed by someone (i.e., friend, girlfriend/boyfriend)' , 'self'  )";
        db.execSQL(T_ONE);
        String T_TWO = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Betrayed BY someone (i.e., friend, girlfriend/boyfriend)' , 'self'  )";
        db.execSQL(T_TWO);
        String T_THREE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'unintentionally hurt someone else badly' , 'self'  )";
        db.execSQL(T_THREE);
        String T_FOUR = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Credit card payment overdue?' , 'self'  )";
        db.execSQL(T_FOUR);
        String T_FIVE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Parenting challenges (Different age groups from baby to teens)' , 'self'  )";
        db.execSQL(T_FIVE);
        String T_SIX = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Have physical accident resulting in reparation?' , 'self'  )";
        db.execSQL(T_SIX);
        String T_SEVEN = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Too many responsibilities' , 'work'  )";
        db.execSQL(T_SEVEN);
        String T_EIGHT = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Conflicts with co-worker?' , 'work' )";
        db.execSQL(T_EIGHT);
        String T_NINE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Troubles with the boss?' , 'work' )";
        db.execSQL(T_NINE);
        String THIRTY = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Difficult customers?' , 'work' )";
        db.execSQL(THIRTY);
        String TH_ONE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Work overloaded?' , 'work' )";
        db.execSQL(TH_ONE);
        String TH_TWO = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Being fired at work?' , 'work' )";
        db.execSQL(TH_TWO);
        String TH_THREE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Changing to a different line of work?' , 'work' )";
        db.execSQL(TH_THREE);
        String TH_FOUR = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Major changes in working hours and conditions?' , 'work' )";
        db.execSQL(TH_FOUR);
        String TH_FIVE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Demanding or Unreasonable deadlines' , 'work' )";
        db.execSQL(TH_FIVE);
        String TH_SIX = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Poor management, unclear expectations of your work, or cannot have opinion in the decision-making process.' , 'work' )";
        db.execSQL(TH_SIX);
        String TH_SEVEN = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Working under dangerous conditions?' , 'work' )";
        db.execSQL(TH_SEVEN);
        String TH_EIGHT = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Being insecure about your chance for advancement or risk of termination of employment' , 'work' )";
        db.execSQL(TH_EIGHT);
        String TH_NINE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Facing discrimination or harassment at work, especially if your company is not supportive' , 'work' )";
        db.execSQL(TH_NINE);
        String FOURTY = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Have not had pay raise for a long time' , 'work' )";
        db.execSQL(FOURTY);
        String F_ONE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Getting stuck in one position for a long time without promotion' , 'work' )";
        db.execSQL(F_ONE);
        String F_TWO = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Facing too much homework with deadline' , 'study' )";
        db.execSQL(F_TWO);
        String F_THREE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Having stress of exams' , 'study' )";
        db.execSQL(F_THREE);
        String F_FOUR = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Feeling learning is a challenge' , 'study' )";
        db.execSQL(F_FOUR);
        String F_FIVE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Being bullied' , 'study' )";
        db.execSQL(F_FIVE);
        String F_SIX = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Bad time management' , 'study' )";
        db.execSQL(F_SIX);
        String F_SEVEN = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Communication problems  with family' , 'study' )";
        db.execSQL(F_SEVEN);
        String F_EIGHT = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Having irresponsible educator (E.g., teacher, lecture, tutor, professor)' , 'study' )";
        db.execSQL(F_EIGHT);
        String F_NINE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Having different opinion with educator (i.g., teacher, lecture, tutor, professor) in some projects' , 'study' )";
        db.execSQL(F_NINE);
        String FIFTY = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Have nobody willing to be partner ( Feeling Lonely, isolated or insecure)' , 'study' )";
        db.execSQL(FIFTY);
        String FI_ONE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Feeling fearful about your ability to get right grades' , 'study' )";
        db.execSQL(FI_ONE);
        String FI_TWO = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Missing home (e.g., international students)' , 'study' )";
        db.execSQL(FI_TWO);
        String FI_THREE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Facing culture shock' , 'study' )";
        db.execSQL(FI_THREE);
        String FI_FOUR = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Having roommate conflict' , 'study' )";
        db.execSQL(FI_FOUR);
        String FI_FIVE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Having difficulty to speak in public' , 'study' )";
        db.execSQL(FI_FIVE);
        String FI_SIX = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Changing major' , 'study' )";
        db.execSQL(FI_SIX);
        String FI_SEVEN = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Missed too many classes' , 'study' )";
        db.execSQL(FI_SEVEN);
        String FI_EIGHT = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Transferring school' , 'study' )";
        db.execSQL(FI_EIGHT);
        String FI_NINE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Facing unfamiliar situation.' , 'study' )";
        db.execSQL(FI_NINE);
        String SIXTY = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Facing divorce between parents' , 'study' )";
        db.execSQL(SIXTY);
        String S_ONE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Changing religious beliefs' , 'study' )";
        db.execSQL(S_ONE);
        String S_TWO = "INSERT INTO  "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Having a part-time job' , 'study' )";
        db.execSQL(S_TWO);
        String S_THREE = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Start consuming alcohol or drugs' , 'study' )";
        db.execSQL(S_THREE);
        String S_FOUR = "INSERT INTO "+ QUESTION_TABLE_NAME + " ( " + QUESTION + " , " + QUESTION_TYPE + " )" +
                " VALUES ( 'Unrealistic expectations from parents' , 'study' )";
        db.execSQL(S_FOUR);*/

    }

}
