package com.android.ilmanaque;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.provider.MediaStore;
import android.net.Uri;
import android.widget.Toast;
import android.content.Context;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;


public class AddTask extends Activity{
	
	private DBManager oDB;
	Button add;
	Button photo;
	Button photo2;
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
	protected void onCreate(Bundle state){
		super.onCreate(state);
		setContentView(R.layout.mainaddtask);
		oDB = new DBManager(this);
		
		oETtitle = (EditText)findViewById(R.id.titleTa);
		oETdesc = (EditText)findViewById(R.id.descTa);
		oETdate = (EditText)findViewById(R.id.dateTa);
		add =  (Button) findViewById(R.id.addTa);
		
		//Cria pessoas
		add.setOnClickListener(new View.OnClickListener(){
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

					Task t = oDB.AddTask(title, desc, date);
					Toast.makeText(mycntx, "Tarefa criada com sucesso ! ", Toast.LENGTH_SHORT).show();
					oDB.close();
					finish();
				}
			}
		});
		
	}
}