package com.android.zsdaking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class Activity5 extends Activity {

    EditText oET1, oET2;
    Button oBT;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5);
        oET1 = (EditText) findViewById(R.id.et1);
        oET2 = (EditText) findViewById(R.id.et2);
        oBT = (Button) findViewById(R.id.log);
    }
    //Verificar username e password para prosseguir para a próxima atividade
    public void startActivity(View v) {
        if (oET1.getText().toString().equals("admin") && oET2.getText().toString().equals("teste123")) {
            Toast.makeText(Activity5.this, "Login em progresso", Toast.LENGTH_SHORT).show();
            Intent iGoTo6 = new Intent(this, Activity6.class);
            startActivity(iGoTo6);
            finish();
        } else {
            Toast.makeText(Activity5.this, "Utilizador ou password errados!", Toast.LENGTH_SHORT).show();
            oET1.setText("");
            oET2.setText("");
            oET1.requestFocus();
        }
    }

}
