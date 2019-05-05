package com.dianmo.flash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {
    private TextView name ;
    private ImageView oprate,more;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatt);

        name =(TextView) findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
        oprate = (ImageView)findViewById(R.id.oprate);
        more = (ImageView)findViewById(R.id.more);

    }
}
