package com.android.zsdaking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.app.ListActivity;
import android.net.Uri;

public class Activity8 extends Activity {

    TextView oTV;
    EditText oET;
    Button oBT,oBT2;
    int valor;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main8);
        Intent iCameFrom7 = getIntent();
        valor = iCameFrom7.getIntExtra("activity7", 0);
        oTV = (TextView) findViewById(R.id.tv1);
        oET = (EditText) findViewById(R.id.et1); 
        oBT = (Button) findViewById(R.id.b1);
        oBT2 = (Button) findViewById(R.id.b2);
        oTV.setText(db.getQuestion(valor + 1).getQuestion());
    }
    //Alterar a pergunta garantido que o texto inserido não é vazio
    public void alterar(View v) {
        if(oET.getText().toString().equals("")){
            Toast.makeText(Activity8.this, "Tens de inserir uma pergunta! ", Toast.LENGTH_SHORT).show();
        }
        else{
            db.updateQuestion(new Question(oET.getText().toString()), valor + 1);
            Toast.makeText(Activity8.this, "Pergunta alterada com sucesso! ", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void cancel(View v){
        finish();
    }
}
