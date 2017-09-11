package com.gmail.wnfldchen.chemtable;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class ad extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
//    private static final String TOAST_TEXT = "Test ads are being shown. "
//            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

//    private static final int START_LEVEL = 1;
//    private int mLevel;
//    private Button mNextLevelButton;
    private InterstitialAd mInterstitialAd;
//    private TextView mLevelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        MobileAds.initialize(this,"ca-app-pub-9921268342443053~4731583527");
        // Create the next level button, which tries to show an interstitial when clicked.
//        mNextLevelButton = ((Button) findViewById(R.id.next_level_button));
//        mNextLevelButton.setEnabled(false);
//        mNextLevelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showInterstitial();
//            }
//        });

        // Create the text view to show the level number.
//        mLevelTextView = (TextView) findViewById(R.id.level);
//        mLevel = START_LEVEL;

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        loadInterstitial();

        csvIO ptdata = new csvIO(getResources().openRawResource(R.raw.pt));
        MainActivity.ptdata = ptdata.read();
        csvIO acids = new csvIO(getResources().openRawResource(R.raw.acids));
        MainActivity.acids = acids.read();
        csvIO bases = new csvIO(getResources().openRawResource(R.raw.bases));
        MainActivity.bases = bases.read();
        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
//        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ad, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                showInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                goToNextActivity();
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next activity.
                goToNextActivity();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 2 seconds
                // Show the ad if it's ready. Otherwise toast and reload the ad.
                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    goToNextActivity();
                }
            }
        }, 1000);
    }

    private void loadInterstitial() {
        mInterstitialAd = newInterstitialAd();
        // Disable the next level button and load the ad.
//        mNextLevelButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextActivity() {
        // Show the next activity and reload the ad to prepare for any after.
//        mLevelTextView.setText("Level " + (++mLevel));
        Intent main = new Intent (this, MainActivity.class);
        startActivity(main);
        finish();
    }
}
