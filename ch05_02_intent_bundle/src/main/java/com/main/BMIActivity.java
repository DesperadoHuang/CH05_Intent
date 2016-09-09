package com.main;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BMIActivity extends Activity {
    private TextView textView_BmiResult;
    private String bmiRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        textView_BmiResult = (TextView) findViewById(R.id.textView_result);

        Bundle bundle = getIntent().getExtras();
        double height = bundle.getInt("height") / 100.0;
        double weight = bundle.getInt("weight");

        double bmiValue = weight / Math.pow(height, 2);

        NumberFormat numberFormat = new DecimalFormat("##.00");

        bmiRecord = String.format("%s %s %s",
                textView_BmiResult.getText().toString(),
                numberFormat.format(bmiValue),
                getBMIMessage(bmiValue));

        textView_BmiResult.setText(bmiRecord);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem=menu.add(0,0,0,"＜返回");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private String getBMIMessage(double bmiValue) {
        String message = "";

        if (bmiValue > 0 && bmiValue < 20) {
            message = getResources().getString(R.string.bmi_low);
        } else if (bmiValue >= 20 && bmiValue < 26) {
            message = getResources().getString(R.string.bmi_normal);
        } else if (bmiValue >= 26 && bmiValue < 30) {
            message = getResources().getString(R.string.bmi_high);
        } else if (bmiValue >= 30 && bmiValue < 40) {
            message = getResources().getString(R.string.bmi_overhigh);
        } else if (bmiValue >= 40 && bmiValue <= 100) {
            message = getResources().getString(R.string.bmi_dangerous);
        } else {
            message = getResources().getString(R.string.bmi_value_error);
        }

        return message;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (item.getItemId() == 0) {
            finish();
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
