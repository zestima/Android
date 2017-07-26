package com.android.ilmanaque;

import android.graphics.Bitmap;

public class Plant implements java.io.Serializable{
	
	long _id;
    String plant;
    String loc;
    String date;
	String obs;
	String quant;
	Bitmap photo;
	
	public Plant(long _id, String plant, String loc,String date,String quant,String obs){
		this._id=_id;
		this.plant=plant;
		this.loc=loc;
		this.date=date;
		this.quant=quant;
		this.obs=obs;
	}
	
	public Plant(long _id, String plant, String loc,String date,String quant,String obs, Bitmap photo){
		this._id=_id;
		this.plant=plant;
		this.loc=loc;
		this.date=date;
		this.quant=quant;
		this.obs=obs;
		this.photo = photo;
	}
	
	public long getId(){
		return _id;
	}
	public void setId(long _id){
		this._id=_id;
	}
	
	public String getPlant(){
        return plant;
    }
    public void setPlant(String plant){
        this.plant=plant;
    }
	
	public String getLoc(){
        return loc;
    }
    public void setLoc(String loc){
        this.loc=loc;
    }
	
	public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }
	
	public String getQuant(){
        return quant;
    }
    public void setQuant(String quant){
        this.quant=quant;
    }
	
	public String getObs(){
        return obs;
    }
    public void setObs(String obs){
        this.obs=obs;
    }
	
	public Bitmap getPhoto(){
		return photo;		
	}
	public void setPhoto(Bitmap photo){
		this.photo= photo;
	}
}