package com.ch05_05_intent_activitytransitions;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, PageActivity.class);
        intent.putExtra("title", view.getTag().toString());

        switch (view.getId()) {
            case R.id.ibt_taiwan:
                intent.putExtra("resId", R.drawable.banner1);
                intent.putExtra("txtId", R.string.taiwan_info);
                break;
            case R.id.ibt_sanya:
                intent.putExtra("resId", R.drawable.banner2);
                intent.putExtra("txtId", R.string.sanya_info);
                break;
            case R.id.ibt_chengde:
                intent.putExtra("resId", R.drawable.banner3);
                intent.putExtra("txtId", R.string.chengde_info);
                break;
            case R.id.ibt_great_wall:
                intent.putExtra("resId", R.drawable.banner4);
                intent.putExtra("txtId", R.string.great_wall_info);
                break;
            case R.id.ibt_chong_cing:
                intent.putExtra("resId", R.drawable.banner5);
                intent.putExtra("txtId", R.string.chong_cing_info);
                break;
        }

        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(
                        this,
                        findViewById(view.getId()),
                        view.getTag().toString()
                ).toBundle()
        );
    }
}
