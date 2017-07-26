package com.android.ilmanaque;

public class Task implements java.io.Serializable{
	
	long _id;
    String title;
    String desc;
    String date;
	
	public Task(long _id, String title, String desc,String date){
		this._id=_id;
		this.title=title;
		this.desc=desc;
		this.date=date;
	}
	
	public long getId(){
		return _id;
	}
	public void setId(long _id){
		this._id=_id;
	}
	
	public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
	
	public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }
	
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc=desc;
    }
}