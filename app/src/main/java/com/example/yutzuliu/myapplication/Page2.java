package com.example.yutzuliu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Page2 extends AppCompatActivity {

    TextView nameTV,genderTV,bloodTV,phoneTV,emailTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        nameTV = (TextView)findViewById(R.id.showname);
        genderTV = (TextView)findViewById(R.id.showgender);
        bloodTV=(TextView)findViewById(R.id.showblood);
        phoneTV=(TextView)findViewById(R.id.showphone);
        emailTV=(TextView)findViewById(R.id.showmail);


        Bundle bundle = this.getIntent().getExtras();
        nameTV.setText(bundle.getString("name"));
        genderTV.setText(bundle.getString("gender"));
        bloodTV.setText(bundle.getString("blood"));
        phoneTV.setText(bundle.getString("phone"));
        emailTV.setText(bundle.getString("email"));

    }
}
