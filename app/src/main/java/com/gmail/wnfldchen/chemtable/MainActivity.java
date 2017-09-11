package com.gmail.wnfldchen.chemtable;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<String[]> ptdata = new ArrayList<String[]>();
    public static ArrayList<String[]> acids = new ArrayList<String[]>();
    public static ArrayList<String[]> bases = new ArrayList<String[]>();
    public static int Z = 22;
    enum INFOVIEW{
        PERIODIC,PHYSICAL,ELECTRICAL,BONDING,STRUCTURE,THERMODYNAMIC,REACTIVITY,OTHER
    }
    public INFOVIEW infoview = INFOVIEW.PERIODIC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        if (!ptdata.isEmpty()){
            refresh();
        }
    }

    private void refresh() {
        String[] Zdata = ptdata.get(Z);
        TextView name = (TextView)findViewById(R.id.textView2);
        TextView symbol = (TextView)findViewById(R.id.textView3);
        TextView number = (TextView)findViewById(R.id.textView4);
        name.setText(Zdata[5]);
        symbol.setText("Symbol "+Zdata[1]);
        ImageView sample = (ImageView)findViewById(R.id.imageView3);
        ImageView shell = (ImageView)findViewById(R.id.imageView2);
        int sid = getResources().getIdentifier("com.gmail.wnfldchen.chemtable:drawable/s"+Z,null,null);
        int shid = getResources().getIdentifier("com.gmail.wnfldchen.chemtable:drawable/sh" + String.format("%03d",Z),null,null);
        sample.setImageResource(sid);
        shell.setImageResource(shid);
        number.setText("Number "+Z);
        TableLayout tl = (TableLayout)findViewById(R.id.tableLayout);
        tl.removeAllViews();
        int[] idcs;
        switch (infoview){
            case PERIODIC:
                idcs = new int[]{2,3,4,10,11};
                break;
            case PHYSICAL:
                idcs = new int[]{12,13,14,15,39,41,51,52,54};
                break;
            case ELECTRICAL:
                idcs = new int[]{16,17,18,19,20,21,22,23,24,25,26,27,28};
                break;
            case BONDING:
                idcs = new int[]{29,30,31,32,33,34,35,36,37,38,64};
                break;
            case STRUCTURE:
                idcs = new int[]{14,39,51,52,53,54};
                break;
            case THERMODYNAMIC:
                idcs = new int[]{42,43,44,45,65};
                break;
            case REACTIVITY:
                idcs = new int[]{56,57,58,59,60,61,62,63};
                break;
            case OTHER:
                idcs = new int[]{40,46,47,48,49,50,51,52,55,66,67,68,69,70,71,72};
                break;
            default:
                idcs = new int[]{2,3,4,10,11};
                break;
        }
        String[] key = ptdata.get(0);
        int mpx = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10,
                getResources().getDisplayMetrics()
        );
        for (int idx:idcs) {
            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            lp.setMargins(lp.leftMargin,lp.topMargin,mpx,mpx);
            row.setLayoutParams(lp);
            TextView tf1 = new TextView(this);
            TextView tf2 = new TextView(this);
            tf1.setText(key[idx]);
            tf1.setLayoutParams(lp);
            tf2.setText(Zdata[idx]);
            tf2.setLayoutParams(lp);
            row.addView(tf1);
            row.addView(tf2);
            tl.addView(row);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_bonding) {
            infoview = INFOVIEW.BONDING;
        } else if (id == R.id.nav_electrical) {
            infoview = INFOVIEW.ELECTRICAL;
        } else if (id == R.id.nav_other) {
            infoview = INFOVIEW.OTHER;
        } else if (id == R.id.nav_periodic) {
            infoview = INFOVIEW.PERIODIC;
        } else if (id == R.id.nav_physical){
            infoview = INFOVIEW.PHYSICAL;
        } else if (id == R.id.nav_reactivity){
            infoview = INFOVIEW.REACTIVITY;
        } else if (id == R.id.nav_structure){
            infoview = INFOVIEW.STRUCTURE;
        } else if (id == R.id.nav_thermodynamic){
            infoview = INFOVIEW.THERMODYNAMIC;
        } else if (id == R.id.Acids){
            Intent intent = new Intent(this,AcidBase.class);
            intent.putExtra("AcidOrBase",0);
            startActivity(intent);
        } else if (id == R.id.Bases){
            Intent intent = new Intent(this,AcidBase.class);
            intent.putExtra("AcidOrBase",1);
            startActivity(intent);
        } else if (id == R.id.Dilution) {
            Intent intent = new Intent(this,DilutionCalc.class);
            startActivity(intent);
        } else if (id == R.id.Solution) {
            Intent intent = new Intent(this,SolutionCalc.class);
            startActivity(intent);
        } else if (id == R.id.Buffer) {
            Intent intent = new Intent(this,BufferCalc.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (!ptdata.isEmpty()){
            refresh();
        }
        return true;
    }

    public void onBrowseClick(MenuItem item){
        Intent browseActivity = new Intent(this, browse.class);
        startActivity(browseActivity);
    }
}
