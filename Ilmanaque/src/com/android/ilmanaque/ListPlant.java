package com.android.ilmanaque;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;

public class ListPlant extends ListActivity{
	DBManager oDB;
	ListAdapter adapter;
	
	@Override
	public void onCreate(Bundle state){
		super.onCreate(state);
		setContentView(R.layout.listtask);
		
		oDB = new DBManager(this);
		oDB.open();
		
		Cursor cursor = oDB.getAllPlants(); 
        String[] columns = new String[] {"Plant"};
		int[] to = new int[] {R.id.text};
        adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, columns, to); 
        this.setListAdapter(adapter);
		
		oDB.close();
	}
	//Atualiza tabela ao voltar atividade
	@Override
    protected void onResume() {
        super.onResume();
		oDB.open();
        
		Cursor cursor = oDB.getAllPlants(); 
        String[] columns = new String[] {"Plant"};
		int[] to = new int[] {R.id.text};
        adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, columns, to); 
        this.setListAdapter(adapter);
		
		oDB.close();
    }
	//Para clicar na lista e enviar id nova atividade para visualizar e editar
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Intent intent = new Intent(this, EditPlant.class);
        Cursor cursor = (Cursor) adapter.getItem(position);
		intent.putExtra("idPlant",cursor.getInt(cursor.getColumnIndex("_id")));
		startActivity(intent);
    }
}