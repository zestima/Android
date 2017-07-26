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

public class AddPlant extends Activity{
	
	private DBManager oDB;
	int REQUEST_CODE_PICTURE = 1;
	Button add;
	Button photo;
	Button photo2;
	EditText oETplant, oETloc, oETdate, oETquant, oETobs;
	ImageView iv;
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
		setContentView(R.layout.mainaddplant);
		oDB = new DBManager(this);
		
		oETplant = (EditText)findViewById(R.id.plant);
		oETloc = (EditText)findViewById(R.id.loc);
		oETdate = (EditText)findViewById(R.id.dateP);
		oETquant = (EditText)findViewById(R.id.quant);
		oETobs = (EditText)findViewById(R.id.obsP);
		add =  (Button) findViewById(R.id.addP);
		photo =  (Button) findViewById(R.id.photoP);
		photo2 =  (Button) findViewById(R.id.photo2P);
		iv = (ImageView) findViewById(R.id.ivP);
		//Atividade para tirar foto
		photo.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(takePicture, 0);
			}
		});
		//Atividade para escolher foto
		photo2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(pickPhoto , 1);
			}
		});
		//Cria pessoas
		add.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				
				oDB.open();
				
				String plant = oETplant.getText().toString(),
				loc = oETloc.getText().toString(),
				date = oETdate.getText().toString(),
				quant = oETquant.getText().toString(),
				obs = oETobs.getText().toString();

				if(plant.equals("") || loc.equals("") || date.equals("") || quant.equals("")){
					Toast.makeText(mycntx, "Não pode haver campos vazios !", Toast.LENGTH_SHORT).show();
				}
				else if(!checkDate(date)){
					Toast.makeText(mycntx, "Data não existe", Toast.LENGTH_SHORT).show();
				}
				else{

					Plant p = oDB.AddPlant(plant, loc, date, quant, obs, loadView(iv));
					Toast.makeText(mycntx, "Sementeira criada com sucesso ! ", Toast.LENGTH_SHORT).show();
					oDB.close();
					finish();
				}
			}
		});
		
	}
	//Gera e exibe a imagem obita 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		switch(requestCode){
			case 0:
			if(resultCode == RESULT_OK){  
				Bundle extras = data.getExtras();
				Bitmap b = (Bitmap) extras.get("data");
				iv.setImageBitmap(b);
			}
			break; 
			case 1:
			if(resultCode == RESULT_OK){  
				Uri selectedImage = data.getData();
				iv.setImageURI(selectedImage);
			}
			break;
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public static Bitmap loadView(View v){
		Bitmap b = Bitmap.createBitmap( 250, 250, Bitmap.Config.ARGB_8888);                
		Canvas c = new Canvas(b);
		v.layout(0, 0, 250, 250);
		v.draw(c);
		return b;
	}
}