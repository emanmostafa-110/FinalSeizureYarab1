package com.example.finalseizureyarab.UI;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.finalseizureyarab.R;


public class UploadEmg extends AppCompatActivity {

    Button upload;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_eeg);
        upload = findViewById(R.id.UploadBtnEMG);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
                Intent intent = new Intent(UploadEmg.this,EMGResult.class);
                startActivity(intent);
            }
        });
    }

    private void uploadImage() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent();
            intent.setType("file/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,10);
        } else{
            ActivityCompat.requestPermissions(UploadEmg.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10&&resultCode== Activity.RESULT_OK){
            Uri uri = data.getData();
            Context context = UploadEmg.this;
            path = RealPathUtil.getRealPath(context,uri);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menue, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.profile:
                Intent intent1 =new Intent(UploadEmg.this , MyProfile.class);
                startActivity(intent1);
                return true;
            case R.id.seizureHistory:
                Intent intent2 =new Intent(UploadEmg.this , SeizureHistory.class);
                startActivity(intent2);
                return true;

            case R.id.medicalRecord:
                Intent intent3 =new Intent(UploadEmg.this , SeizureMR.class);
                startActivity(intent3);
                return true;

            case R.id.symptoms:
                Intent intent4 =new Intent(UploadEmg.this , Symptoms.class);
                startActivity(intent4);
                return true;

            case R.id.connection:
                Intent intent5 =new Intent(UploadEmg.this , Connection.class);
                startActivity(intent5);
                return true;

            case R.id.diet:
                Intent intent6 =new Intent(UploadEmg.this , Diet.class);
                startActivity(intent6);
                return true;

            case R.id.alarm:
                Intent intent7 =new Intent(UploadEmg.this , Alarm.class);
                startActivity(intent7);
                return true;
            case R.id.question:
                Intent intent8 =new Intent(UploadEmg.this , Questions.class);
                startActivity(intent8);
                return true;
            case R.id.logout:
                Intent intent9 =new Intent(UploadEmg.this , Login.class);
                startActivity(intent9);
                return true;
            case R.id.seizure:
                Intent intent10 =new Intent(UploadEmg.this , SeizureInfo.class);
                startActivity(intent10);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}