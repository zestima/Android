package com.android.zsdaking;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.view.View;
import android.app.ListActivity;
import java.io.File;

public class Activity3 extends Activity {

    TextView oTV;
    String s = "";
    ListView oLV;
    String fname;
    Context mycntx;
    String[] nameOfFiles;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        Intent iCameFromHome = getIntent();
        oTV = (TextView) findViewById(R.id.tv1);
        oLV = (ListView) findViewById(R.id.list);
        populate();
    }
    //obter nome dos ficheiros para popular a ListView
    public void populate(){
        File path = getFilesDir();
        File[] files = path.listFiles();
        nameOfFiles = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            nameOfFiles[i] = files[i].getName();

        }
        //Criar ListView com todos os ficheiros e ao clickar neles ir para outra atividade mostrando o 
        //seu conteúdo
        ListAdapter la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameOfFiles);
        oLV.setEmptyView(oTV);
        oLV.setAdapter(la);
        mycntx = this;
        oLV.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    fname = String.valueOf(parent.getItemAtPosition(position));
                    Toast.makeText(Activity3.this, "A abrir " + fname, Toast.LENGTH_SHORT).show();
                    Intent iGoTo4 = new Intent(mycntx, Activity4.class);
                    iGoTo4.putExtra("activity3", fname);
                    startActivity(iGoTo4);
                }
            }
            );
    }
    //Quando volta a esta atividade faz refresh da ListView
    @Override
    public void onResume(){
        super.onResume();
        populate();
    }
}
