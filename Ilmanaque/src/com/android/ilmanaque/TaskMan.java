package com.android.ilmanaque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TaskMan extends Activity{
	
	public void onClick2( View v ){
        Intent myIntent = new Intent(this, ListTask.class);
		startActivity(myIntent);
    }
	
	public void onClick( View v ){
        Intent myIntent = new Intent(this, AddTask.class);
		startActivity(myIntent);
    }
	//Cria tabela ao iniciar a atividade
	@Override
	public void onCreate(Bundle state){
		super.onCreate(state);
		setContentView(R.layout.mainman2);
	}
}