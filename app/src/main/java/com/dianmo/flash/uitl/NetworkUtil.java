package com.dianmo.flash.uitl;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NetworkUtil {

    static OkHttpClient client  = new OkHttpClient();
    static Gson gson = new Gson();
    public static <T> void getMethod(String url,final Class<T> cls,final INetCallback<T> callback)
    {

        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){//回调的方法执行在子线程。
                    callback.onSuccess(gson.fromJson( response.body().string(),cls));

                }
            }
        });


    }

    public static <T> void postMethod(String url, HashMap<String,String> map, final Class<T> cls, final INetCallback<T> callback){
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        for (String key:map.keySet())
        {
            formBody.add(key,map.get(key));
        }
        Request request = new Request.Builder()//创建Request 对象。
                .url(url)
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Network","failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    callback.onSuccess(gson.fromJson(response.body().string(),cls));
                }
            }
        });
    }

}
