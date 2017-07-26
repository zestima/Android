package com.android.ilmanaque;

import android.content.ContentValues;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.content.Intent;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import android.graphics.Canvas;
import android.view.View;
import android.provider.MediaStore;
import android.net.Uri;
import android.database.Cursor;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class EditTask extends Activity{
	
	int idTask;
	DBManager oDB;
	Task t;
	Button edit;
	Button del;
	EditText oETtitle, oETdesc, oETdate;
	Context mycntx = this;

	public boolean checkDate(String d){
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			df.setLenient(false);
			df.parse(d);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainedittask);
		
		oDB = new DBManager(this);
		
		oETtitle = (EditText)findViewById(R.id.titleTe);
		oETdate = (EditText)findViewById(R.id.dateTe);
		oETdesc = (EditText)findViewById(R.id.descTe);
		edit = (Button) findViewById(R.id.editTe);
		del = (Button) findViewById(R.id.delTe);
		
		loadTask();
		//Apaga uma pessoa
		del.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				AlertDialog.Builder dialog = new AlertDialog.Builder(EditTask.this);
				dialog.setTitle("Apagar");
				dialog.setMessage("Tens a certeza que queres apagar esta tarefa?");				
				dialog.setNegativeButton("Não", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){}
				});
				dialog.setPositiveButton("Sim",new DialogInterface.OnClickListener(){ 
					public void onClick(DialogInterface dialog, int id){
						oDB.open();
						oDB.DeleteTask(idTask);
						oDB.close();
						Toast.makeText(mycntx, "Tarefa apaga com sucesso !", Toast.LENGTH_SHORT).show();
						finish();
					}
				});
				dialog.show();
			}
		});
		//Altera os dados de uma pessoa
		edit.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				oDB.open();
				
				String title = oETtitle.getText().toString(),
				desc = oETdesc.getText().toString(),
				date = oETdate.getText().toString();
				if(title.equals("") || desc.equals("") || date.equals("")){
					Toast.makeText(mycntx, "Não pode haver campos vazios !", Toast.LENGTH_SHORT).show();
				}
				else if(!checkDate(date)){
					Toast.makeText(mycntx, "Data não existe", Toast.LENGTH_SHORT).show();
				}
				else{

					Task t = oDB.UpdateTask(idTask,title, desc, date);
					Toast.makeText(mycntx, "Tarefa alterada com sucesso ! ", Toast.LENGTH_SHORT).show();
					oDB.close();
					finish();
				}
			}
		});
	}
	//Obtém a pessoa que corresponde ao id recebido
	private void loadTask(){
		idTask = getIntent().getIntExtra("idTask", 0);
		
		oDB = new DBManager(this);
		oDB.open();
		t = oDB.getTask(idTask);
		oDB.close();
		
		oETtitle.setText(t.getTitle());
		oETdesc.setText(t.getDesc());
		oETdate.setText(t.getDate());
	}
}