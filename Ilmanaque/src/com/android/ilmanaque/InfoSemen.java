package com.android.ilmanaque;



import android.content.ContentValues;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class InfoSemen extends Activity{
	
	long id;
	DBManager oDB;
	Sementeira t;
	Button edit;
	Button del;
	TextView oETtitle, oETdesc, oETmes, oETdias, oETph;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maininfo);
		oETtitle = (TextView)findViewById(R.id.semen);
		oETdesc = (TextView)findViewById(R.id.desc);
		oETmes = (TextView)findViewById(R.id.meses);
		oETdias = (TextView)findViewById(R.id.dias);
		oETph = (TextView)findViewById(R.id.ph);
		
		
		oDB = new DBManager(this);
		
		oDB.open();
		id = getIntent().getLongExtra("id", 1);
        Log.d("cursor index", String.valueOf(id));
		t = oDB.getSementeira((int)id);
		 Log.d("nome", t.getSemente());
		 Log.d("ph", t.getPh());
		oDB.close();
		
		oETtitle.setText(t.getSemente());
		oETdesc.setText(t.getObs());
		oETmes.setText(t.getMes_Plant());
		oETdias.setText(String.valueOf(t.getDias_Crescimeto()));
		oETph.setText(t.getPh());
	
		
	
		
	
		

	
		
	
	}
}