package com.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText editTextPhoneNumber;
    private Context context;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        action = Intent.ACTION_CALL;//設定action字串
        editTextPhoneNumber = (EditText) findViewById(R.id.editText_PhoneNumber);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btCall:
                String phoneNumber = editTextPhoneNumber.getText().toString();
                if ((phoneNumber == null) || (phoneNumber.equals(""))) {
                    Toast.makeText(context, "", Toast.LENGTH_LONG).show();
                } else {
                    Uri uri = Uri.parse("tel:" + phoneNumber);//將電話轉成URI格式
                    Intent intent = new Intent(action, uri);
                    startActivity(intent);
                }
                break;
        }
    }
}
