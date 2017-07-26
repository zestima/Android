package com.android.zsdaking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.app.ListActivity;
import android.net.Uri;
import java.io.FileInputStream;
import android.content.DialogInterface;
import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Activity4 extends Activity {

    String valor;
    TextView oTV;
    Button oBT,oBT2;
    String s = "";
    Context mycntx = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main4);
        Intent iCameFrom3 = getIntent();
        Intent iCameFrom2 = getIntent();
        //Verificar de que atividade vem o Intent
        if (iCameFrom2.getStringExtra("activity2") != null) {
            valor = iCameFrom2.getStringExtra("activity2");
        } else if (iCameFrom3.getStringExtra("activity3") != null) {
            valor = iCameFrom3.getStringExtra("activity3");
        }
        oTV = (TextView) findViewById(R.id.textview4);
        oBT = (Button) findViewById(R.id.b4);
        oBT2 = (Button) findViewById (R.id.b5);
        //Mostrar conteúdo do ficheiro
        try {
            FileInputStream fis = openFileInput(valor);
            byte[] dataArray = new byte[fis.available()];
            while (fis.read(dataArray) != -1) {
                s = new String(dataArray);
            }
            fis.close();
        } catch (Exception e) {

        }
        oTV.setText(s);
    }
    //Criar um Intento para enviar um email onde o destinatário está vazio
    //subject é o Relatório médico
    //Texto é o conteúdo do ficheiro que acabamos de mostrar
    public void sendEmail(View v) {
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Relatório médico");
        emailIntent.putExtra(Intent.EXTRA_TEXT, s);
        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar Email..."));
        } catch (Exception e) {
        }
    }
    //Apagar ficheiros e mostrar um diálogo de confirmação no caso da pessoa se ter enganado
    public void delete(View view){

        AlertDialog.Builder dialog = new AlertDialog.Builder(mycntx);

        dialog.setTitle("Apagar relatório");
        dialog.setMessage("Quer mesmo apagar este relatório ? ").setCancelable(false).setPositiveButton("Sim", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int id){
                Boolean check;
                check = deleteFile(valor);
                if(check = true) {
                    Toast.makeText(mycntx, "relatório " + valor + " apagado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(mycntx, "Erro a apagar o relatório " + valor+"!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int id){
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }
}
