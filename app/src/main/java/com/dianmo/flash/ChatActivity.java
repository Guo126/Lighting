package com.dianmo.flash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {
    private TextView name ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        name =(TextView) findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
    }
}
