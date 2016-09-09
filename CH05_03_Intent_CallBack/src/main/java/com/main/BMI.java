package com.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BMI extends Activity {
    private TextView textView;
    private String bmiRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        textView = (TextView) findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        double height = bundle.getInt("height") / 100.0;
        double weight = bundle.getInt("weight");

        double bmiValue = weight / Math.pow(height, 2);

        NumberFormat numberFormat = new DecimalFormat("##.00");
        bmiRecord = String.format("%s %s %s",
                textView.getText().toString(),
                numberFormat.format(bmiValue),
                getBMIMessage(bmiValue));
        textView.setText(bmiRecord);
    }

    private String getBMIMessage(double bmiValue) {
        String message = "";
        if (bmiValue > 0 && bmiValue < 20) {
            message = getResources().getString(R.string.bmi_low);
        } else if (bmiValue >= 20 && bmiValue < 26) {
            message = getResources().getString(R.string.bmi_normal);
        } else if (bmiValue >= 26 && bmiValue < 30) {
            message = getResources().getString(R.string.bmi_high);
        } else if (bmiValue >= 30 && bmiValue <= 10) {
            message = getResources().getString(R.string.bmi_overhigh);
        } else {
            message = getResources().getString(R.string.bmi_dangerous);
        }
        return message;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem menuItem=menu.add(0,0,0,"←返回");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        if (item.getItemId()==0)
        {
            Intent intent =new Intent();
            intent.putExtra("bmi_record",bmiRecord);
            setResult(1,intent);//將資料結果回傳，第一個參數是responseCode
            finish();
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
