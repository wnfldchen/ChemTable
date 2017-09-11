package com.gmail.wnfldchen.chemtable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SolutionCalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_calc);
    }
    public void calc(View view){
        String gmm = ((EditText)findViewById(R.id.gmmIn)).getText().toString();
        String vol = ((EditText)findViewById(R.id.volIn)).getText().toString();
        String conc = ((EditText)findViewById(R.id.concIn)).getText().toString();
        if (gmm.isEmpty() || vol.isEmpty() || conc.isEmpty()) {
            Toast.makeText(getApplicationContext(),"A value is missing!",Toast.LENGTH_SHORT).show();
            ((TextView)findViewById(R.id.mass)).setText("Mass needed: Error!");
            return;
        }
        double gmmD = Double.parseDouble(gmm);
        double volD = Double.parseDouble(vol);
        double concD = Double.parseDouble(conc);
        if (gmmD <= 0 || volD <= 0 || concD <= 0) {
            Toast.makeText(getApplicationContext(),"Value negative or zero!",Toast.LENGTH_SHORT).show();
            ((TextView)findViewById(R.id.mass)).setText("Mass needed: Error!");
            return;
        }
        double m = volD*concD*gmmD;
        ((TextView)findViewById(R.id.mass)).setText("Mass needed: " + String.format("%g",m) + " g");
    }
}
