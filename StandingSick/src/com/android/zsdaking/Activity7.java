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

public class Activity7 extends Activity {

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
        setContentView(R.layout.main7);
        Intent iCameFrom6 = getIntent();
        oLV = (ListView) findViewById(R.id.list);
        populate();
    }
    //Popular a ListView com as perguntas disponíveis
    public void populate(){
        db = new DatabaseHandler(this);
        int size = db.getQuestionsCount();
        String[] questions = new String[size];
        for (int i = 0; i < size; i++) {
            questions[i] = db.getQuestion(i + 1).getQuestion();
        }
        //Quando se clicka na pergunta ir para outra atividade para a poder alterar
        ListAdapter la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questions);
        oLV.setEmptyView(oTV);
        oLV.setAdapter(la);
        mycntx = this;
        oLV.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    fname = String.valueOf(parent.getItemAtPosition(position));
                    Toast.makeText(mycntx, "A abrir pergunta para alteração!", Toast.LENGTH_SHORT).show();
                    Intent iGoTo8 = new Intent(mycntx, Activity8.class);
                    iGoTo8.putExtra("activity7", position);
                    startActivity(iGoTo8);
                }
            }
            );
    }
    //Quando retorna a esta atividade faz refresh da ListView
    @Override
    public void onResume(){
        super.onResume();
        populate();
    }
}
