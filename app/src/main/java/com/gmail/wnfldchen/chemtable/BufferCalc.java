package com.gmail.wnfldchen.chemtable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BufferCalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer_calc);
    }
    public void calc(View view){
        String pka = ((EditText)findViewById(R.id.pkaIn)).getText().toString();
        String aconc = ((EditText)findViewById(R.id.aconcIn)).getText().toString();
        String bconc = ((EditText)findViewById(R.id.bconcIn)).getText().toString();
        if (pka.isEmpty() || aconc.isEmpty() || bconc.isEmpty()) {
            Toast.makeText(getApplicationContext(),"A value is missing!",Toast.LENGTH_SHORT).show();
            ((TextView)findViewById(R.id.ph)).setText("pH: Error!");
            return;
        }
        double pkaD = Double.parseDouble(pka);
        double aconcD = Double.parseDouble(aconc);
        double bconcD = Double.parseDouble(bconc);
        if (aconcD <= 0 || bconcD <= 0) {
            Toast.makeText(getApplicationContext(),"Concentration negative or zero!",Toast.LENGTH_SHORT).show();
            ((TextView)findViewById(R.id.ph)).setText("pH: Error!");
            return;
        }
        double ph = pkaD + Math.log10(bconcD/aconcD);
        ((TextView)findViewById(R.id.ph)).setText("pH: " + String.format("%g",ph));
    }
}
