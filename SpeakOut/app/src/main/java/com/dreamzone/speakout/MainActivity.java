package com.dreamzone.speakout;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSettings(View view){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    public void openText(View view) {
        Intent i = new Intent(this, TextActivity.class);
        startActivity(i);
    }

    public void openFile(View view) {
        Intent i = new Intent(this, FileActivity.class);
        startActivity(i);
    }
}
