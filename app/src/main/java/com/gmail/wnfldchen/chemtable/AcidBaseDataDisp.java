package com.gmail.wnfldchen.chemtable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AcidBaseDataDisp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_acid_base_data_disp);
        final int AcidOrBase = getIntent().getExtras().getInt("AcidOrBase");
        final int Index = getIntent().getExtras().getInt("Index");
        String[] ABdata;
        if (AcidOrBase == 0){
            ABdata = MainActivity.acids.get(Index);
        } else {
            ABdata = MainActivity.bases.get(Index);
        }
        TextView name = (TextView)findViewById(R.id.ABName);
        TextView conj = (TextView)findViewById(R.id.ABConjugate);
        TextView form = (TextView)findViewById(R.id.ABFormula);
        TextView k = (TextView)findViewById(R.id.ABK);
        TextView pk = (TextView)findViewById(R.id.ABpK);
        String nameAB, conjAB, kAB, pkAB;
        if (AcidOrBase == 0) {
            nameAB = "Acid name: ";
            conjAB = "Conjugate base: ";
            kAB = "Ka: ";
            pkAB = "pKa: ";
        } else {
            nameAB = "Base name: ";
            conjAB = "Conjugate acid: ";
            kAB = "Kb: ";
            pkAB = "pKb: ";
        }
        name.setText(nameAB+ABdata[0]);
        form.setText("Formula: "+ABdata[1]);
        conj.setText(conjAB+ABdata[2]);
        k.setText(kAB+ABdata[3]);
        pk.setText(pkAB+ABdata[4]);
    }
}
