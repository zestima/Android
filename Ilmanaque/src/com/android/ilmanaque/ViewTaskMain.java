package com.android.ilmanaque;

import android.content.ContentValues;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.database.Cursor;
import android.content.Context;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import android.view.View.OnClickListener;

public class ViewTaskMain extends Activity{

	int idTask, idTasko;
	DBManager oDB;
	Task t;
	Button oButton;
	TextView oTVtitle, oTVdesc, oTVdate;
    Intent abre_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_task_main);

         idTask = getIntent().getIntExtra("idTask", 0);

        oTVtitle = (TextView)findViewById(R.id.titleVM);
		oTVdate = (TextView)findViewById(R.id.dateVM);
        oTVdesc = (TextView)findViewById(R.id.descVM);

		oButton=(Button)findViewById(R.id.editVM);

         oButton.setOnClickListener(new OnClickListener() {
             @Override
              public void onClick(View v) {
                   idTasko = getIntent().getIntExtra("idTask", 0);
                   abre_edit = new Intent(ViewTaskMain.this, EditTask.class);
                   abre_edit.putExtra("idTask",idTasko);
                   startActivity(abre_edit);
              }
         });



		oDB = new DBManager(this);
		oDB.open();
		t = oDB.getTask(idTask);
		oDB.close();


		oTVtitle.setText(t.getTitle());
		oTVdate.setText(t.getDate());
        oTVdesc.setText(t.getDesc());


    }


}



