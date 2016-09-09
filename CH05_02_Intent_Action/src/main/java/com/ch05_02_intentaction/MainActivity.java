package com.ch05_02_intentaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.car.BenzActivity;
import com.car.BmwActivity;

public class MainActivity extends Activity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btBenz:
                intent.setClass(context, BenzActivity.class);
                startActivity(intent);
                break;
            case R.id.btBmw:
                intent.setClass(context, BmwActivity.class);
                startActivity(intent);
                break;
            case R.id.btGoodCar:
                intent.setAction("good.car");
                intent = Intent.createChooser(intent, "選擇一個APP來執行");
                startActivity(intent);
                break;
        }
    }
}
