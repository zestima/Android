package com.android.zsdaking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class Activity9 extends Activity {

    /**
     * Called when the activity is first created.
     */
    ListView oLV;
    Context mycntx;
    String fname;
    TextView oTV;
    DatabaseHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main9);
        Intent iCameFrom6 = getIntent();
        oLV = (ListView) findViewById(R.id.list);
        populate();
    }
    //Popular a ListView com as respostas disponíveis
    public void populate(){
        db = new DatabaseHandler(this);
        int size = db.getAnswersCount();
        String[] answers = new String[size];
        for (int i = 0; i < db.getAnswersCount(); i++) {
            answers[i] = db.getAnswer(i+1).getAnswer();
        }
        //Quando clickar na pergunta ir para outra atividade para a poder alterar
        ListAdapter la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, answers);
        oLV.setEmptyView(oTV);
        oLV.setAdapter(la);
        mycntx = this;
        oLV.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    fname = String.valueOf(parent.getItemAtPosition(position));
                    Toast.makeText(mycntx, "A abrir resposta para alteração! ", Toast.LENGTH_SHORT).show();
                    Intent iGoTo10 = new Intent(mycntx, Activityt.class);
                    iGoTo10.putExtra("activity9", position);
                    startActivity(iGoTo10);
                }
            }
            );
    }
    //Refresh da ListView aquando o retorno a esta atividade
    @Override
    public void onResume(){
        super.onResume();
        populate();
    }
}
