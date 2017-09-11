package com.gmail.wnfldchen.chemtable;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class browse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        ArrayList<String> elems = new ArrayList<String>();
        boolean skipkey = false;
        for (String[] line:MainActivity.ptdata
             ) {
            if (skipkey) {
                elems.add(line[0] + " - " + line[1] + " - " + line[5]);
            }
            else
                skipkey = true;
        }
        ListView lv = (ListView)findViewById(R.id.listView);
        ArrayAdapter aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,elems);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.Z = position + 1;
                finish();
            }
        });
    }

}
