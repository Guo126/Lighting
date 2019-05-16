package com.dianmo.flash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dianmo.flash.Entity.user.UserMsg;
import com.dianmo.flash.uitl.INetCallback;
import com.dianmo.flash.uitl.NetworkUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class RegisterActivity extends Activity {

    private EditText phone, name,password;
    private Button reg;
    //声明一个SharedPreferences对象和一个Editor对象
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phone = (EditText) findViewById(R.id.phone_input);
        name = (EditText) findViewById(R.id.name_input);
        password = (EditText) findViewById(R.id.password_input);
        reg = (Button) findViewById(R.id.btn_login);
        //获取preferences和editor对象
        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = preferences.edit();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneNum = phone.getText().toString().trim();
                final String passwords = password.getText().toString().trim();
                final String userName = name.getText().toString().trim();
                if (phoneNum!=null||!phoneNum.equals("")) {
                    if (phoneNum.length() != 11) {
                        Toast.makeText(getApplicationContext(), "手机号格式错误", Toast.LENGTH_SHORT).show();
                    } else {
                        if(!userName.equals("")){

                            if (!passwords.equals("")) {
                                editor.putString("userPhone", phoneNum);
                                editor.putString("userPassword", passwords);
                                editor.putString("myName",userName);
                                editor.apply();
                                NetworkUtil.postMethod("http://39.106.81.100:9999/firefly/user/register", new HashMap<String, String>() {{
                                            put("phone", phoneNum);
                                            put("psw", passwords);
                                            put("name", userName);
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
                                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                    startActivity(intent);
                                                    RegisterActivity.this.finish();
                                                }
                                            }
                                        }
                                );



                            } else {
                                //密码健壮性
                                Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    //手机号健壮性
                    Toast.makeText(getApplicationContext(), "手机号不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void saveFile(String str) throws Exception {

        try{
            String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();//获取SDCard目录
            File dir = new File(sdCardDir + "/" + getPackageName());
            if(!dir.exists())
            {
                if(!dir.mkdirs())
                    throw new Exception("failure");
            }
            File saveFile = new File(dir,"userInfo.txt");
            if(!saveFile.exists())
                if(!saveFile.createNewFile())
                    throw new Exception("failure");

            FileOutputStream outStream = new FileOutputStream(saveFile,true);

            outStream.write((str+"\r\n").getBytes());

            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
