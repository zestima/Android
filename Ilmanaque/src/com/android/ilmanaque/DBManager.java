package com.android.ilmanaque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;

public class DBManager{
	
	private DBHelper dbHelper;
	private SQLiteDatabase database;
	private String[] allColPlant = {DBHelper.P_ID,DBHelper.P_PLANT,DBHelper.P_LOC,DBHelper.P_DATE,DBHelper.P_QUANT,DBHelper.P_OBS,DBHelper.P_PHOTO};
	private String[] allColTask = {DBHelper.T_ID,DBHelper.T_TITLE,DBHelper.T_DESC,DBHelper.T_DATE};
	private String[] allColInfo= {DBHelper.I_ID,DBHelper.I_SEMENTE,DBHelper.I_OBS,DBHelper.I_MES_P,DBHelper.I_DIAS_C,DBHelper.I_PH};
	
	public DBManager(Context context){          
		dbHelper = new DBHelper(context);
	}
	
	public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
	
	public void close() {
        dbHelper.close();
    }
	
	private Plant cursorToPlant(Cursor cursor) { 
        
		byte[] blob = cursor.getBlob(cursor.getColumnIndex(DBHelper.P_PHOTO));
	    Bitmap photo = BitmapFactory.decodeByteArray(blob, 0, blob.length);
		
		Plant p = new Plant(cursor.getLong(0),cursor.getString(1),cursor.getString(2),
		cursor.getString(3),cursor.getString(4),cursor.getString(5),photo);
		
		cursor.close();
        return p;
	}
	
	private Task cursorToTask(Cursor cursor) { 
		
		Task t = new Task(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
		
		cursor.close();
        return t;
	}

	private Sementeira cursorToSementeira(Cursor cursor) { 
		
		Sementeira t = new Sementeira(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5));
		
		cursor.close();
        return t;
	}

	public Plant AddSementeira(long _id, String semente,String obs, String mes_plant,int dias_crescimento, float ph){
    ContentValues values = new ContentValues(); 
	
    values.put(DBHelper.I_SEMENTE,semente); 
    values.put(DBHelper.I_OBS, obs); 
    values.put(DBHelper.I_MES_P, mes_plant);
    values.put(DBHelper.I_DIAS_C, dias_crescimento);
	values.put(DBHelper.I_PH, ph);

	
	
    long _id1 = database.insert(DBHelper.TAB_INF, null, values); 
	
	Cursor cursor = database.query(DBHelper.TAB_INF,allColInfo, DBHelper.I_ID + " = " + _id1 , null, null, null, null);
	cursor.moveToFirst();
	return cursorToPlant(cursor);
}
	
	public Plant AddPlant(String plant, String loc, String date, String quant, String obs, Bitmap photo){
        ContentValues values = new ContentValues(); 
		
        values.put(DBHelper.P_PLANT, plant); 
        values.put(DBHelper.P_LOC, loc); 
        values.put(DBHelper.P_DATE, date);
        values.put(DBHelper.P_QUANT, quant);
		values.put(DBHelper.P_OBS, obs);
		
		ByteArrayOutputStream bp = new ByteArrayOutputStream();  
		photo.compress(Bitmap.CompressFormat.PNG, 100, bp);  
		byte[] p = bp.toByteArray();
		
		values.put(DBHelper.P_PHOTO, p);
		
        long _id = database.insert(DBHelper.TAB_PLANT, null, values); 
		
		Cursor cursor = database.query(DBHelper.TAB_PLANT,allColPlant, DBHelper.P_ID + " = " + _id, null, null, null, null);
		cursor.moveToFirst();
		return cursorToPlant(cursor);
	}
	
	public Task AddTask(String title, String desc, String date){
        ContentValues values = new ContentValues(); 
		
        values.put(DBHelper.T_TITLE, title); 
        values.put(DBHelper.T_DESC, desc); 
        values.put(DBHelper.T_DATE, date);
		
        long _id = database.insert(DBHelper.TAB_TASK, null, values); 
		
		Cursor cursor = database.query(DBHelper.TAB_TASK,allColTask, DBHelper.T_ID + " = " + _id, null, null, null, null);
		cursor.moveToFirst();
		return cursorToTask(cursor);
	}
	
	public Plant UpdatePlant(int id, String plant, String loc, String date, String quant, String obs, Bitmap photo){
        ContentValues values = new ContentValues(); 
		
        values.put(DBHelper.P_PLANT, plant); 
        values.put(DBHelper.P_LOC, loc); 
        values.put(DBHelper.P_DATE, date); 
        values.put(DBHelper.P_QUANT, quant);
		values.put(DBHelper.P_OBS, obs);
		
		ByteArrayOutputStream bp = new ByteArrayOutputStream();  
		photo.compress(Bitmap.CompressFormat.PNG, 100, bp);  
		byte[] p = bp.toByteArray();
		
		values.put(DBHelper.P_PHOTO, p);
		
		database.update(DBHelper.TAB_PLANT, values, "_id="+id, null);
		
		Cursor cursor = database.query(DBHelper.TAB_PLANT,allColPlant, DBHelper.P_ID + " = " + id, null, null, null, null);
		cursor.moveToFirst();
		return cursorToPlant(cursor);
	}
	
	public Task UpdateTask(int id, String title, String desc, String date){
        ContentValues values = new ContentValues(); 
		
        values.put(DBHelper.T_TITLE, title); 
        values.put(DBHelper.T_DESC, desc); 
        values.put(DBHelper.T_DATE, date);
		
        database.update(DBHelper.TAB_TASK, values, "_id="+id, null);
		
		Cursor cursor = database.query(DBHelper.TAB_TASK,allColTask, DBHelper.T_ID + " = " + id, null, null, null, null);
		cursor.moveToFirst();
		return cursorToTask(cursor);
	}
	
	public void DeletePlant (int _id){ 
        database.delete(DBHelper.TAB_PLANT, DBHelper.P_ID + " = " + _id, null); 
	}
	
	public void DeleteTask (int _id){ 
        database.delete(DBHelper.TAB_TASK, DBHelper.T_ID + " = " + _id, null); 
	}
	
	public Cursor getAllPlants(){ 
        Cursor cursor = database.rawQuery("Select _id, Plant, Location, Date, Observations, Quantity, Photo From PlantationTable", null); 
        return cursor; 
	}
	
	public Cursor getAllTasks(){
		Cursor cursor = database.rawQuery("Select _id, Title, Description, Date From TaskTable", null);
		return cursor;
	}

		public Cursor getAllSementeira(){ 
        Cursor cursor = database.rawQuery("Select _id, Semente, Observation, Mes, Dias, PH From InfoTable", null); 
        return cursor; 
	}

	public Cursor getAllTasksDate(){
		Cursor cursor = database.rawQuery("Select _id, Title, Description, Date From TaskTable order by Date ASC", null);
		return cursor;
	}
	
	public Plant getPlant(int _id){ 
        Cursor cursor = database.query(DBHelper.TAB_PLANT, allColPlant, DBHelper.P_ID + " = " + _id, null, null, null, null); 
		cursor.moveToFirst(); 
		return cursorToPlant(cursor); 
	}
	
	public Task getTask(int _id){ 
        Cursor cursor = database.query(DBHelper.TAB_TASK, allColTask, DBHelper.T_ID + " = " + _id, null, null, null, null); 
		cursor.moveToFirst(); 
		return cursorToTask(cursor); 
	}

	public Sementeira getSementeira(int _id){ 
        Cursor cursor = database.query(DBHelper.TAB_INF, allColInfo, DBHelper.I_ID + " = " + _id, null, null, null, null); 
		cursor.moveToFirst(); 
		return cursorToSementeira(cursor); 
	}
}