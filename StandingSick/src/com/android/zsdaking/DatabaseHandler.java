package com.android.zsdaking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "StandingSick";


    private static final String TABLE_QUESTIONS = "questions";
    private static final String TABLE_ANSWERS="answers";


    private static final String KEY_ID = "id";
    private static final String KEY_Q = "question";
    private static final String KEY_AID = "id";
    private static final String KEY_A = "answer";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Q + " TEXT)";
        String CREATE_ANSWERS_TABLE = "CREATE TABLE " + TABLE_ANSWERS + "("
        + KEY_AID + " INTEGER PRIMARY KEY," + KEY_A + " TEXT)";
        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_ANSWERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERS);
        onCreate(db);
    }
    //Adicionar questão
    void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Q, question.getQuestion()); 
        db.insert(TABLE_QUESTIONS, null, values);
        db.close(); 
    }
    //Adicionar resposta
    void addAnswer(Answer answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_A, answer.getAnswer()); 
        db.insert(TABLE_ANSWERS, null, values);
        db.close(); 
    }
    //Obter questão através do id
    Question getQuestion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_QUESTIONS, new String[]{KEY_ID,
            KEY_Q}, KEY_ID + "=?",
            new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Question question = new Question(Integer.parseInt(cursor.getString(0)),
            cursor.getString(1));
        db.close();
        return question;
    }
    //Obter resposta através do Id
    Answer getAnswer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ANSWERS, new String[]{KEY_AID,
            KEY_A}, KEY_AID + "=?",
            new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Answer answer = new Answer(Integer.parseInt(cursor.getString(0)),
            cursor.getString(1));
        db.close();
        return answer;
    }

    //Alterar uma questão
    public void updateQuestion(Question question, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Q, question.getQuestion());
        db.update(TABLE_QUESTIONS, values, KEY_ID + " = ?",
            new String[]{String.valueOf(id)});
        db.close();
    }

    //Alterar uma resposta
    public void updateAnswer(Answer answer, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_A, answer.getAnswer());
        db.update(TABLE_ANSWERS, values, KEY_AID + " = ?",
            new String[]{String.valueOf(id)});
        db.close();
    }

    //Total de questões
    public int getQuestionsCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_QUESTIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        db.close();
        return count;
    }
    //Total de respostas
    public int getAnswersCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_ANSWERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        db.close();
        return count;
    }
}
