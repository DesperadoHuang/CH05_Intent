package com.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private EditText editText;
    private String sdPath;
    private String fileName_key = "key.png";
    private String fileName_THX = "thx.png";
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        editText = (EditText) findViewById(R.id.editText);
        sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        action = Intent.ACTION_SEND;//單筆分享的常數字串 "android.intent.action.SEND"
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_share_text:
                String shareText = editText.getText().toString();
                Intent sendTextIntent = new Intent();
                sendTextIntent.setAction(action);
                sendTextIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                sendTextIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendTextIntent, getResources().getString(R.string.share)));
                break;

            case R.id.bt_share_image:
                File resFile = resToFile(R.drawable.key, fileName_key);
                Intent shareImageIntent = new Intent();
                shareImageIntent.setAction(action);
                shareImageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(resFile));
                shareImageIntent.setType("image/png");
                startActivity(Intent.createChooser(shareImageIntent, getResources().getText(R.string.share)));
                break;

            case R.id.bt_share_multiple:
                File fileKEY = resToFile(R.drawable.key, fileName_key);
                File fileTHX = resToFile(R.drawable.thx, fileName_THX);

                ArrayList<Uri> imageUris = new ArrayList<Uri>();
                imageUris.add(Uri.fromFile(fileKEY));
                imageUris.add(Uri.fromFile(fileTHX));

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                shareIntent.setType("iamge/*");
                startActivity(Intent.createChooser(shareIntent, getResources().getString(R.string.share)));
                break;
        }
    }

    private File resToFile(int resId, String fileName) {
        InputStream inputStream = getResources().openRawResource(resId);
        try {
            OutputStream outputStream = new FileOutputStream(new File(sdPath, fileName));
            byte[] buffer = new byte[2048];
            int len;
            while ((len = inputStream.read()) != -1) {
                outputStream.write(buffer, 0, len);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
        }
        return new File(sdPath + "/" + fileName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new File(sdPath + "/" + fileName_key).delete();
        new File(sdPath + "/" + fileName_THX).delete();
    }
}
