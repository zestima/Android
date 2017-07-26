package com.android.ilmanaque;

import android.graphics.Bitmap;

public class Sementeira implements java.io.Serializable{
	
	long _id;
    String semente;
    String mes_plant ;
    int dias_crescimento;
	String ph;
	
	String obs;
	
	public Sementeira(long _id, String semente,String obs, String mes_plant,int dias_crescimento, String ph){
		this._id=_id;
		this.semente=semente;
		this.mes_plant=mes_plant;
		this.dias_crescimento=dias_crescimento;
		this.obs=obs;
	
		this.ph=ph;
	}
	
	
	public long getId(){
		return _id;
	}
	public void setId(long _id){
		this._id=_id;
	}
	
	public String getSemente(){
        return semente;
    }
    public void setSemente(String semente){
        this.semente=semente;
    }
	
	public String getMes_Plant(){
        return mes_plant;
    }
    public void setMes_Plant(String mes_plant){
        this.mes_plant=mes_plant;
    }
	
	public String getPh(){
        return ph;
    }
    public void setPh(String ph){
        this.ph=ph;
    }	
	

	public int getDias_Crescimeto(){
        return dias_crescimento;
    }
    public void setDias_Crescimento(int dias_crescimento){
        this.dias_crescimento=dias_crescimento;
    }
	
	public String getObs(){
        return obs;
    }
    public void setObs(String obs){
        this.obs=obs;
    }
	

}