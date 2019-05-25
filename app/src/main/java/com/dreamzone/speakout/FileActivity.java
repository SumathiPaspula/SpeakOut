package com.dreamzone.speakout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;



public class FileActivity extends Activity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private Button btnSpeak;
    TextView txtText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        this.setTitle("File");
        Intent intent = getIntent();
        String filepath = intent.getStringExtra("KEY");

        TextView pane = findViewById(R.id.TextPanel);
        pane.setMovementMethod(new ScrollingMovementMethod());
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = br.readLine()) != null) {

                text.append(line);
                text.append("\n");
            }
            br.close();

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), filepath, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        pane.setText(text);
        Speakaloud();
    }




    private void Speakaloud() {
        tts = new TextToSpeech(this, this);

        btnSpeak = findViewById(R.id.play);
        txtText = findViewById(R.id.TextPanel);

        // button on click event
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });
    }


    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                btnSpeak.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {

        String text = txtText.getText().toString();

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void openFile(View view) {
        Intent i = new Intent(this, FileSelectionActivity.class);
        startActivity(i);
    }

    public void onBackPressed() {
        Intent startMain = new Intent(this,MainActivity.class);
        startActivity(startMain);
    }

}
