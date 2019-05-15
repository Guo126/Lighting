package com.dianmo.flash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dianmo.flash.Entity.FriendLists;
import com.dianmo.flash.Entity.user.UserInner;
import com.dianmo.flash.Entity.user.UserMsg;

import com.dianmo.flash.uitl.INetCallback;
import com.dianmo.flash.uitl.NetworkUtil;

import com.dianmo.flash.uitl.WsManager;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity {
    private EditText phone, password;
    private Button login;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private UserInner userInner =new UserInner();
    public static WsManager wsManager ;
    private ProgressBar bar;
    private List<BigInteger> friIds;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = (EditText) findViewById(R.id.phone_input);
        bar = (ProgressBar) findViewById(R.id.bar);
        password = (EditText) findViewById(R.id.password_input);
        login = (Button) findViewById(R.id.btn_login);
        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = preferences.edit();

        final String name = preferences.getString("userPhone",null);
        if (name != null) {
            phone.setText(name);
        }
        final String passwords = preferences.getString("userPassword", null);
        if (passwords != null) {
            password.setText(passwords);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    bar.setVisibility(View.VISIBLE);
                    login.setClickable(false);
                    NetworkUtil.postMethod("http://39.106.81.100:9999/firefly/user/login", new HashMap<String, String>() {{
                                put("phone", phone.getText().toString());
                                put("psw", password.getText().toString());
                            }}, UserMsg.class, new INetCallback<UserMsg>() {
                                @Override
                                public void onSuccess(final UserMsg msg) {
                                    if(!msg.isSuccess()){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getApplicationContext(),
                                                        msg.getAlter(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }else{
                                        //实例化wsManager
                                        wsManager = new WsManager.Builder(getBaseContext()).client(
                                                new OkHttpClient().newBuilder()
                                                        .pingInterval(15, TimeUnit.SECONDS)
                                                        .retryOnConnectionFailure(true)
                                                        .build())
                                                .needReconnect(true)
                                                .wsUrl("ws://39.106.81.100:9999/firefly/chat/"+phone.getText())
                                                .build();
                                        wsManager.startConnect();
                                        userInner = msg.getUserInner();
                                        Intent intent = new Intent(LoginActivity.this, MustActivity.class);
                                        intent.putExtra("userInner",userInner);

                                        editor.putString("phoneNum",phone.getText().toString());
                                        editor.apply();
                                        friIds=userInner.getFriendIDList();
                                        FriendLists.getInstance().GetFriends(friIds);
                                        FriendLists.getInstance().GetNewFriendsList(userInner.getId());
                                        bar.setVisibility(View.INVISIBLE);
                                        login.setClickable(true);
                                        startActivity(intent);
                                        LoginActivity.this.finish();
                                    }
                                }
                            }
                    );
            }
        });
    }

    private boolean getFile(String name,String password){
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder("");
        boolean isRight = false;
        try {
            String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();//获取SDCard目录
            File dir = new File(sdCardDir + "/" + getPackageName());
            FileInputStream input = new FileInputStream(dir+"/userInfo.txt");
            reader=new BufferedReader(new InputStreamReader(input));
            String line = "";

            while ((line=reader.readLine())!=null) {
                String [] result = line.split("@");
                if(result[0].equals(name) &&result[1].equals(password)){
                    isRight = true;
                }else{
                    isRight =  false;
                }


            }
            reader.close();
            //reader = new BufferedReader(new InputStreamReader(fileInputStream));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                if(line==name+"@"+password){
//                    isRight = true;
//                }else{
//                    isRight =  false;
//                }
//
//                reader.close();
            } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

             return isRight;
        }


}
