package com.dreamzone.speakout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static com.dreamzone.speakout.MainActivity.verifyStoragePermissions;


public class FileSelectionActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_selection);


        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent,1);

        verifyStoragePermissions(this);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
     {
        super.onActivityResult(resultCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null )
        {

            String path =  data.getData().getPath();
            Intent info = new Intent(this, FileActivity.class);
            info.putExtra("KEY", path);

            startActivity(info);


        }
    }

}

