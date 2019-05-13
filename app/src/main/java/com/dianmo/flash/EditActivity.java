package com.dianmo.flash;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dianmo.flash.Adapter.RecorderAdapter;
import com.dianmo.flash.Entity.user.ChatMsg;
import com.dianmo.flash.uitl.INetCallback;
import com.dianmo.flash.uitl.NetworkUtil;
import com.dianmo.view.AudioButton;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private TextView name ,send ;
    private ImageView oprate;
    private ListView mList;
    private EditText editText;
    private String friId ;
    private LinearLayout main;
    private boolean isUp = false;
    private int keyHeight = 0;
    private String  myMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //初始化好友id
        friId = "17863129122";
        main = (LinearLayout)findViewById(R.id.main);
        name =(TextView) findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
        oprate = (ImageView)findViewById(R.id.oprate);
        send = (TextView)findViewById(R.id.send);
        mList = (ListView)findViewById(R.id.list);
        editText = (EditText) findViewById(R.id.edit);
        oprate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(EditActivity.this,ChatActivity.class);
               intent.putExtra("name",name.getText().toString());
               startActivity(intent);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMsg = editText.getText().toString();
                LoginActivity.wsManager.sendMessage(friId+myMsg);
            }
        });

        editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            //当键盘弹出隐藏的时候会 调用此方法。
            @Override
            public void onGlobalLayout() {
               if(keyHeight==0) {
                   Rect r = new Rect();
                   //获取当前界面可视部分
                   EditActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                   //获取屏幕的高度
                   int screenHeight =  EditActivity.this.getWindow().getDecorView().getRootView().getHeight();
                   //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                   int heightDifference = screenHeight - r.bottom;
                   if(heightDifference !=0 ){
                       main.scrollBy(0,keyHeight);
                   }else{
                       main.scrollTo(0,0);
                   }
               }
            }

        });


    }
}
