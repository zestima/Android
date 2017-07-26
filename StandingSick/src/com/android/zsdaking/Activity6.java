package com.android.zsdaking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Activity6 extends Activity {

    /**
     * Called when the activity is first created.
     */ 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main6);
        Intent iCameFrom5 = getIntent();
    }
    //Ir para as outras atividades
    public void startActivity(View v) {
        if (v.getId() == R.id.quest) {
            Intent iGoToQuest = new Intent(this, Activity7.class);
            startActivity(iGoToQuest);
        }else if (v.getId() == R.id.answ) {
            Intent iGoToAnsw = new Intent(this, Activity9.class);
            startActivity(iGoToAnsw);
        }
    }
}
