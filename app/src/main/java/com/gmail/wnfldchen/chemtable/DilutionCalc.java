package com.gmail.wnfldchen.chemtable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DilutionCalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilution_calc);
    }
    public void calc(View view){
        String m1in = ((EditText)findViewById(R.id.editText2)).getText().toString();
        String v2in = ((EditText)findViewById(R.id.editText3)).getText().toString();
        String m2in = ((EditText)findViewById(R.id.editText4)).getText().toString();
        if (m1in.isEmpty() || v2in.isEmpty() || m2in.isEmpty()) {
            Toast.makeText(getApplicationContext(),"A value is missing!",Toast.LENGTH_SHORT).show();
            ((TextView)findViewById(R.id.textView9)).setText("Original volume needed: Error!");
            return;
        }
        double m1 = Double.parseDouble(m1in);
        double v2 = Double.parseDouble(v2in);
        double m2 = Double.parseDouble(m2in);
        if (m1 <= 0 || v2 <= 0 || m2 <= 0) {
            Toast.makeText(getApplicationContext(),"Value negative or zero!",Toast.LENGTH_SHORT).show();
            ((TextView)findViewById(R.id.textView9)).setText("Original volume needed: Error!");
            return;
        }
        double v1 = v2*m2/m1;
        ((TextView)findViewById(R.id.textView9)).setText("Original volume needed: " + String.format("%g",v1) + " L");
    }
}
