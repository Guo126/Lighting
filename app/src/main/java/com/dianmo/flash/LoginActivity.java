package com.dianmo.flash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;

public class LoginActivity extends AppCompatActivity {
    private EditText phone, password;
    private Button login;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = (EditText) findViewById(R.id.phone_input);
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
                if(getFile(phone.getText().toString(),password.getText().toString())){
                    editor.putInt("isLogin",1);
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this,MustActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else{
                    Toast.makeText(getApplicationContext(),"手机号或密码错误",Toast.LENGTH_SHORT).show();
                }


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
