package com.dianmo.flash;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;

import static java.lang.System.out;


public class RegisterActivity extends Activity {

    private EditText phone, password;
    private Button reg;
    //声明一个SharedPreferences对象和一个Editor对象
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phone = (EditText) findViewById(R.id.phone_input);
        password = (EditText) findViewById(R.id.password_input);
        reg = (Button) findViewById(R.id.btn_login);
        //获取preferences和editor对象
        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = preferences.edit();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = phone.getText().toString().trim();
                String passwords = password.getText().toString().trim();
                if(name!=null){
                    if(name.length()!=11){
                        Toast.makeText(getApplicationContext(),"手机号格式错误",Toast.LENGTH_SHORT).show();
                    }else {

                        if (passwords != null) {
                            editor.putString("userPhone", name);
                            editor.putString("userPassword", passwords);
                            editor.apply();
                            try {
                                saveFile(name + "@" + passwords);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            RegisterActivity.this.finish();

                        } else {
                            //密码健壮性
                            Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    //手机号健壮性
                    Toast.makeText(getApplicationContext(),"手机号不能为空",Toast.LENGTH_SHORT).show();
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
