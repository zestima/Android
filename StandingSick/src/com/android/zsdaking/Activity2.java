package com.android.zsdaking;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.io.FileOutputStream;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;

public class Activity2 extends Activity {

    TextView oTV;
    int size;
    Button oB3;
    int i = 1;
    String s = "";
    RadioGroup oRG;
    RadioButton oRB;
    int selectedId, valor;
    EditText oTE;
    String PREFS_NAME = Activity2.class.getSimpleName();
    DatabaseHandler db = new DatabaseHandler(this);

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        Intent iCameFromHome = getIntent();
        //Obter valor que foi guardado nas SharedPreferences, caso valor não exista atribuir ao valor o 0
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        valor = prefs.getInt("Num", 0);
        oTV = (TextView) findViewById(R.id.textview1);
        oRG = (RadioGroup) findViewById(R.id.radioAnswers);
        oTE = (EditText) findViewById(R.id.et1);
        oB3 = (Button) findViewById(R.id.b3);
        //Atribuir as respostas aos radio buttons
        for (int j=0;j<oRG.getChildCount();j++)
            ((RadioButton)oRG.getChildAt(j)).setText(db.getAnswer(j+1).getAnswer());
        size = db.getQuestionsCount();
        oTV.setText(db.getQuestion(i).getQuestion());
        //Se existir texto tornar impossível selecionar radio buttons e limpar a seleção
        //que tinha sido feita
        //Se não existir texto tornar possível selecionar radio buttons
        //tem de estar aqui senão a primeira vez que se entra nesta atividade não se pode
        //ver se o texto muda 
        oTE.addTextChangedListener( new TextWatcher(){
            @Override
            public void afterTextChanged(Editable editable){
                if (editable.length()==0){
                    for (int j=0;j<oRG.getChildCount();j++)
                        oRG.getChildAt(j).setEnabled(true);
                }
                else{
                    oRG.clearCheck();
                    for (int j=0;j<oRG.getChildCount();j++)
                        oRG.getChildAt(j).setEnabled(false);
                }
            }
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence c, int start, int before, int count) {
            }
        });
    }

    public void action(View v) {
        //mesmo que acima descrito
        oTE.addTextChangedListener( new TextWatcher(){
            @Override
            public void afterTextChanged(Editable editable){
                if (editable.length()==0){
                    for (int j=0;j<oRG.getChildCount();j++)
                        oRG.getChildAt(j).setEnabled(true);
                }
                else{
                    oRG.clearCheck();
                    for (int j=0;j<oRG.getChildCount();j++)
                        oRG.getChildAt(j).setEnabled(false);
                }
            }
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence c, int start, int before, int count) {
            }
        });
        //Obter ID do radio button selecionado e obter o seu texto
        int selectedId = oRG.getCheckedRadioButtonId();
        //Se nenhum radio button foi selecionado e a caixa de texto está vazia significa que 
        //a pergunta não foi respondida mostrar aviso
        if(selectedId == -1){
            if(oTE.getText().toString().equals("")){
                Toast.makeText(Activity2.this,"Tens responder a esta questão primeiro!",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                s = s + oTV.getText().toString() + "\n" + oTE.getText().toString() + "\n";
                oTE.setText("");
            }
        }
        else{
            oRB = (RadioButton) findViewById(selectedId);
            s = s + oTV.getText().toString() + "\n" + oRB.getText() + "\n";
            oRG.clearCheck();
        }
        //Se todas as perguntas já tiverem sido respondidas escrever para o ficheiro e guardar
        //no armazenamento interno
        //Mudar de atividade para ler relatório médico após o escrever para o ficheiro
        if (i == size) {
            try {
                valor = valor +1;
                Calendar c = Calendar.getInstance(TimeZone.getDefault());
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                s="Hora a que foi gerado : "+sdf.format(c.getTime())+"\n\n"+s; 
                int currentDay = c.get(Calendar.DATE);
                int currentMonth = c.get(Calendar.MONTH) + 1;
                int currentYear = c.get(Calendar.YEAR);
                FileOutputStream fos = openFileOutput("relatório_" + valor +"_"+currentDay+"_"+currentMonth+"_"+currentYear +".txt", Context.MODE_PRIVATE);
                fos.write(s.getBytes());
                fos.close();
                Toast.makeText(Activity2.this, "relatório_" + valor +"_"+currentDay+"_"+currentMonth+"_"+currentYear +".txt foi gerado", Toast.LENGTH_SHORT).show();
                Intent iGoTo4 = new Intent(this,Activity4.class);
                iGoTo4.putExtra("activity2", "relatório_" + valor +"_"+currentDay+"_"+currentMonth+"_"+currentYear +".txt");
                startActivity(iGoTo4);
                finish();
            } catch (Exception e) {
            }
        }
        
        //caso os botões estejam "desligados" pois existia texto na caixa de texto colocá-los 
        //"ligados" novamente
        //mudar de pergunta
        for (int j=0;j<oRG.getChildCount();j++)
            oRG.getChildAt(j).setEnabled(true);
        i = i + 1;
        if(i == size){
            oB3.setText("Terminar");
        }
        if (i <= size) {
            oTV.setText(db.getQuestion(i).getQuestion());
        }

    }
    //guardar o número do relatório no final do questionário para fazer um relatório com nome diferente
    //na próxima vez
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Num", valor);
        editor.commit();
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Num", valor);
        editor.commit();
    }
}
