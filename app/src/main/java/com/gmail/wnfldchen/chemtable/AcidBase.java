package com.gmail.wnfldchen.chemtable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AcidBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acid_base);
        final int AcidOrBase = getIntent().getExtras().getInt("AcidOrBase");
        ArrayList<String[]> acidbasedata;
        if (AcidOrBase == 0){
            acidbasedata = MainActivity.acids;
        } else {
            acidbasedata = MainActivity.bases;
        }
        ArrayList<String> acidbase = new ArrayList<String>();
        boolean skipkey = false;
        for (String[] line:acidbasedata
                ) {
            if (skipkey) {
                acidbase.add(line[0] + " - " + line[1]);
            }
            else
                skipkey = true;
        }
        ListView lv = (ListView)findViewById(R.id.listView2);
        ArrayAdapter aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,acidbase);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AcidBase.this, AcidBaseDataDisp.class);
                intent.putExtra("AcidOrBase",AcidOrBase);
                intent.putExtra("Index",position + 1);
                startActivity(intent);
            }
        });
    }
}
