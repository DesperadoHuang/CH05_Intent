package com.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText editHeight, editWeight;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        editHeight = (EditText) findViewById(R.id.editHeight);
        editWeight = (EditText) findViewById(R.id.editWeight);
    }

    public void onClick(View view) {
        try {
            int height = Integer.parseInt(editHeight.getText().toString());
            int weight = Integer.parseInt(editWeight.getText().toString());

            Bundle bundle = new Bundle();
            bundle.putInt("height", height);
            bundle.putInt("weight", weight);

            Intent intent = new Intent(context, BMIActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, R.string.error_message, Toast.LENGTH_LONG).show();
        }
    }
}
