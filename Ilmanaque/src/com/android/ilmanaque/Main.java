package com.android.ilmanaque;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class Main extends ListActivity{

	DBManager oDB;
	ListAdapter adapter;
	Button oButton_sementeira, oButton_tarefas, oButton_gps, oButton_semen;
	Intent abre_plant,abre_task,abre_gps,abre_semen;

	//Cria tabela ao iniciar a atividade
	@Override
	public void onCreate(Bundle state){
		super.onCreate(state);
		setContentView(R.layout.main);
		oButton_semen=(Button)findViewById(R.id.semen);
		oButton_sementeira=(Button)findViewById(R.id.sementeira);
		oButton_tarefas=(Button)findViewById(R.id.tarefa);
		oButton_gps=(Button)findViewById(R.id.gps);

		oButton_semen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				abre_semen = new Intent(Main.this, ListSementeira.class);
				startActivity(abre_semen);
			}
		});


		oButton_sementeira.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				abre_plant = new Intent(Main.this, PlantMan.class);
				startActivity(abre_plant);
			}
		});

		oButton_tarefas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				abre_task = new Intent(Main.this, TaskMan.class);
				startActivity(abre_task);
			}
		});

		oButton_gps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				abre_gps = new Intent(Main.this, WeatherActivity.class);
				startActivity(abre_gps);
			}
		});
	}

	@Override
	public void onResume(){
		super.onResume();

		oDB = new DBManager(Main.this);
		oDB.open();

		Cursor cursor = oDB.getAllTasksDate();
		String[] columns = new String[] {"Title" , "Date"};
		int[] to = new int[] {R.id.text1, R.id.text2};
		adapter = new SimpleCursorAdapter(Main.this, R.layout.twolist, cursor, columns, to);
		Main.this.setListAdapter(adapter);

		oDB.close();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(Main.this, ViewTaskMain.class);
		Cursor cursor = (Cursor) adapter.getItem(position);
		intent.putExtra("idTask",cursor.getInt(cursor.getColumnIndex("_id")));
		startActivity(intent);
	}
}