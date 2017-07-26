package com.android.zsdaking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.util.Log;


public class home extends Activity {

    int id;
    String PREFS_NAME = home.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Obter valor que foi guardado nas SharedPreferences, caso valor não exista atribuir ao id o 0
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        id = prefs.getInt("MyId", 0); 
        //Adicionar as questões na base de dados apenas na primeira vez que a aplicação é iniciada
        DatabaseHandler db = new DatabaseHandler(this);
        if (id == 0) {
            db.addQuestion(new Question("Tem febre ?"));
            db.addQuestion(new Question("Tem dor de cabeça ?"));
            db.addQuestion(new Question("Tem dores no abdómen ?"));
            db.addQuestion(new Question("Tem dores no braço ou nas pernas ?"));
            db.addAnswer(new Answer("Sim"));
            db.addAnswer(new Answer("Não"));
            db.addAnswer(new Answer("Poucas vezes"));
            db.addAnswer(new Answer("Muitas vezes"));
            id=id+1;
        }
    }
    //Ir para outras atividades
    public void startActivity(View v) {
        if (v.getId() == R.id.b1) {
            Intent iGoToDiag = new Intent(this, Activity2.class);
            startActivity(iGoToDiag);
        } else if (v.getId() == R.id.b2) {
            Intent iGoToAll = new Intent(this, Activity3.class);
            startActivity(iGoToAll);
        } else if (v.getId() == R.id.b3) {
            Intent iGoToAdmin = new Intent(this, Activity5.class);
            startActivity(iGoToAdmin);
        }
    }
    //Guardar o id nas SharedPreferences cada vez que a aplicação passa no onStart
    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("MyId", id);
        editor.commit(); 
    }
}
