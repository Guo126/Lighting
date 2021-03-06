package com.dianmo.flash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dianmo.flash.Adapter.TextAdapter;
import com.dianmo.flash.Entity.user.BasMsg;
import com.dianmo.flash.Entity.user.MsgManager;
import com.dianmo.flash.uitl.INetCallback;
import com.dianmo.flash.uitl.NetworkUtil;
import com.dianmo.flash.uitl.WsManager;

import java.util.ArrayList;
import java.util.HashMap;
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
    private List<String> datas = new ArrayList<String>();
    private TextAdapter adapter;
    private SharedPreferences preferences;
    private HashMap<String,List<String>> msgList ;
    private  String phone = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //初始化好友id
        friId = getIntent().getStringExtra("id");
        msgList = MsgManager.getmInstance().getMsgList();
        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        phone = preferences.getString("phoneNum",null);
        datas = new ArrayList<String>();
        main = (LinearLayout)findViewById(R.id.main);
        name =(TextView) findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
        oprate = (ImageView)findViewById(R.id.oprate);
        send = (TextView)findViewById(R.id.send);
        mList = (ListView)findViewById(R.id.list);
        if(msgList.containsKey(friId)){
            datas.addAll(msgList.get(friId));
        }
        adapter = new TextAdapter(this,datas);
        mList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        editText = (EditText) findViewById(R.id.edit);
        oprate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(EditActivity.this,ChatActivity.class);
               intent.putExtra("name",name.getText().toString());
               startActivity(intent);
            }
        });

        LoginActivity.wsManager.register(new WsManager.IOnMsgReceive() {
                @Override
                public void onReceive(String code,String value) {
                    if (!code.equals("pp"))
                        return;
                    datas.add("a" + value.substring(11));
                    autoFlash();
                }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMsg = editText.getText().toString();
                editText.setText("");
                datas.add("b"+myMsg);
                autoFlash();
                NetworkUtil.postMethod("http://39.106.81.100:9999/firefly/chat/p2p/str", new HashMap<String, String>() {{
                    put("uid", phone);
                    put("target", friId);
                    put("msg", myMsg);
                }}, BasMsg.class, new INetCallback<BasMsg>() {
                    @Override
                    public void onSuccess(BasMsg msg) {

                    }
                });
            }
        });

        editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            //当键盘弹出隐藏的时候会 调用此方法。
            @Override
            public void onGlobalLayout() {

                   Rect r = new Rect();
                   //获取当前界面可视部分
                   EditActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                   //获取屏幕的高度
                   int screenHeight =  EditActivity.this.getWindow().getDecorView().getRootView().getHeight();
                   //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                   int heightDifference = screenHeight - r.bottom;
                   if(!isUp && heightDifference !=0 ){
                       keyHeight = heightDifference;
                       main.scrollBy(0,heightDifference);
                       isUp =true;

                   }
                   if(isUp && heightDifference ==0 ){
                       main.scrollBy(0,-keyHeight);
                       isUp = false;

                   }

            }

        });
    }
    private void autoFlash(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
            }
        });

        thread.start();

    }
}
