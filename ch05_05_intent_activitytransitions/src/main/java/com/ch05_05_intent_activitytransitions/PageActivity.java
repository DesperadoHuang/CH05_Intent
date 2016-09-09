package com.ch05_05_intent_activitytransitions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class PageActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);

        String title = getIntent().getStringExtra("title");
        int resID = getIntent().getIntExtra("resId", 0);
        int txtID = getIntent().getIntExtra("txtId", 0);
        String txt = getResources().getString(txtID);

        showData(title, resID, txt);
    }

    private void showData(String title, int resId, String txt) {
        setTitle(title);
        String html = txt.replaceAll(title,
                "<u><font color='yellow'>" + title + "</font></u>");
        textView.setText(Html.fromHtml(html));
        imageView.setImageResource(resId);
    }
}
