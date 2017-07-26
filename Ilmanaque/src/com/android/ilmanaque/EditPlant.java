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

public class EditPlant extends Activity{
	
	int idPlant;
	DBManager oDB;
	Plant p;
	Button edit;
	Button del;
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
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maineditplant);
		
		oDB = new DBManager(this);
		
		oETplant = (EditText)findViewById(R.id.plantPe);
		oETloc = (EditText)findViewById(R.id.locPe);
		oETdate = (EditText)findViewById(R.id.datePe);
		oETquant = (EditText)findViewById(R.id.quantPe);
		oETobs = (EditText)findViewById(R.id.obsPe);
		edit = (Button) findViewById(R.id.editPe);
		del = (Button) findViewById(R.id.delPe);
		photo =  (Button) findViewById(R.id.photoPe);
		photo2 =  (Button) findViewById(R.id.photo2Pe);
		iv = (ImageView) findViewById(R.id.ivPe);
		
		loadPlant();
		//Apaga uma pessoa
		del.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				AlertDialog.Builder dialog = new AlertDialog.Builder(EditPlant.this);
				dialog.setTitle("Apagar");
				dialog.setMessage("Tens a certeza que queres apagar esta Sementeira ?");				
				dialog.setNegativeButton("Não", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){}
				});
				dialog.setPositiveButton("Sim",new DialogInterface.OnClickListener(){ 
					public void onClick(DialogInterface dialog, int id){
						oDB.open();
						oDB.DeletePlant(idPlant);
						oDB.close();
						Toast.makeText(mycntx, "Sementeira apaga com sucesso !", Toast.LENGTH_SHORT).show();
						finish();
					}
				});
				dialog.show();
			}
		});
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
		//Altera os dados de uma pessoa
		edit.setOnClickListener(new View.OnClickListener(){
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

					Plant p = oDB.UpdatePlant(idPlant, plant, loc, date, quant, obs, loadView(iv));
					Toast.makeText(mycntx, "Sementeira alterada com sucesso ! ", Toast.LENGTH_SHORT).show();
					oDB.close();
					finish();
				}
			}
		});
	}
	//Obtém a pessoa que corresponde ao id recebido
	private void loadPlant(){
		idPlant = getIntent().getIntExtra("idPlant", 0);

		oDB = new DBManager(this);
		oDB.open();
		p = oDB.getPlant(idPlant);
		oDB.close();

		iv.setImageBitmap(p.getPhoto());
		oETplant.setText(p.getPlant());
		oETloc.setText(p.getLoc());
		oETdate.setText(p.getDate());
		oETquant.setText(p.getQuant());
		oETobs.setText(p.getObs());
	}
	//Gera e exibe a imagem obita 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
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
	//Converte a imagem para um formato utilizável
	public static Bitmap loadView (View v){
		Bitmap b = Bitmap.createBitmap( 250, 250, Bitmap.Config.ARGB_8888);                
		Canvas c = new Canvas(b);
		v.layout(0, 0, 250, 250);
		v.draw(c);
		return b;
	}
}
