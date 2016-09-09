package com.example.ch05_04_intent_camera;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private File file;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        action = MediaStore.ACTION_IMAGE_CAPTURE;

        //取得SD卡的根路徑
        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        file = new File(sdPath + "/" + "photo1.png");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            Uri imageUri=Uri.parse(file.getAbsolutePath());
            ImageView imageView=(ImageView)findViewById(R.id.imageView);
            imageView.setImageURI(imageUri);
            Toast.makeText(context,R.string.finish,Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick(View view){
        Intent intent=new Intent(action);
        //第一個參數：輸出參數
        //第二個參數：相機拍照後存入指定路徑
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent,100);
    }
}
