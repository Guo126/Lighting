package com.dianmo.flash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.*;
import com.dianmo.flash.Tool.ReadTxt;


public class SelectActivity extends Activity {
     public static String [] cities = { };
     private String[] m = new String[10];
     private ListView list;
     private SharedPreferences sp;
     private SharedPreferences.Editor editor;
     private int key;
     private TextView t1;
     private Button b1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        t1 = (TextView) findViewById(R.id.searchText);
        b1 = (Button) findViewById(R.id.btn_search);
        cities = ReadTxt.ReadFile(this,"city.txt").split("#");
        sp = getSharedPreferences("cityInfo", MODE_PRIVATE);
        key = getIntent().getExtras().getInt("key");
        editor = sp.edit();
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,cities));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(key==100){
                    editor.putString("start",((TextView) view).getText().toString());
                }else{
                    editor.putString("end",((TextView) view).getText().toString());
                }

                editor.apply();
                Intent intent = new Intent(SelectActivity.this,MustActivity.class);

                startActivity(intent);
                SelectActivity.this.finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCity(t1.getText().toString());
                list.setAdapter(new ArrayAdapter<String>(SelectActivity.this,R.layout.support_simple_spinner_dropdown_item,m));
            }
        });
    }

    public  void  searchCity (String city){
        for(int n=0;n<m.length;n++){
            m[n] = " ";
        }
        int index = 0;
        for(int i =0;i<cities.length;i++){
            if(cities[i].contains(city)){
                m[index] = cities[i];
                index +=1;
            }
        }

    }
}
