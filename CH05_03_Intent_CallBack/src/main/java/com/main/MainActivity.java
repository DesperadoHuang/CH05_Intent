package com.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText editTextHeight;
    private EditText editTextWeight;
    private ImageButton imageButton;
    private TextView textViewBMIRecord;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        editTextHeight = (EditText) findViewById(R.id.editText_Height);
        editTextWeight = (EditText) findViewById(R.id.editText_Weight);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        textViewBMIRecord =(TextView)findViewById(R.id.textView);
    }

    public void onClick(View view) {
        try {
            int height = Integer.parseInt(editTextHeight.getText().toString());
            int weight = Integer.parseInt(editTextWeight.getText().toString());

            Bundle bundle = new Bundle();
            bundle.putInt("height", height);
            bundle.putInt("weight", weight);

            Intent intent = new Intent(context, BMI.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);//第二個參數是requestCode，可自由制定
        } catch (Exception e) {
            Toast.makeText(context, R.string.error_message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==resultCode){
            String record=data.getStringExtra("bmi_record");
            textViewBMIRecord.setText(textViewBMIRecord.getText().toString()+record);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
