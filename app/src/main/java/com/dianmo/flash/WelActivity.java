package com.dianmo.flash;

import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Window;
import android.view.WindowManager;


public class WelActivity extends Activity {
    Graph list = new Graph();
    private SharedPreferences pre;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        pre = getSharedPreferences("UserInfo",MODE_PRIVATE);
        final int isLogin = pre.getInt("isLogin",0);
        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.MOUNT_UNMOUNT_FILESYSTEMS"};



        //兼容Android6.0运行时权限解决方案
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(this,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



//去掉信息栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable()
        {
            //封装的run()方法,用在
            @Override
            public void run()
            {
//页面跳转
                if(isLogin==1){
                    Intent intent = new Intent(WelActivity.this,MustActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(WelActivity.this,MainActivity.class);
                    startActivity(intent);
                }

//保存跳转信息

//进入第二个界面前销毁当前的活动,"finish()"销毁活动
                WelActivity.this.finish();
            }
//这里的数字为延时时长
        }, 1500);
    }
}


