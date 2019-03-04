package com.dianmo.flash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.dianmo.flash.Tool.ReadTxt;



public class ShowActivity extends Activity {
    private TextView text1 ,text2,text3,text4,text5;
   private int[] m =new int[2];
    private String start,end;
    private  Graph gra = new Graph();
    private String [] city = {};
    private String [] edges = {};
    private String [] path = {};
    private  String [] mm ={};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        city = SelectActivity.cities;
        for(int i = 0;i<city.length;i++){
            gra.insertVertex(city[i]);
        }
        edges = ReadTxt.ReadFile(this,"edge.txt").split("#");
        for(int m=1;m<edges.length;m+=4){
            gra.insertEdge(edges[m],edges[m+1],Integer.parseInt(edges[m+2]),Integer.parseInt(edges[m+3]));
        }


        start = getIntent().getExtras().getString("start");
        end = getIntent().getExtras().getString("end");
        m = gra.getRoute(start,end);
        if(m[0]!=0){
            path = gra.getPath(end).toString().split("#");
            StringBuilder m2 = gra.shortRoad(start,end);
             mm = m2.toString().split("#");
        }


        StringBuilder thePath = new StringBuilder();
        StringBuilder shortPath = new StringBuilder();
        for(int i = path.length-1;i>1;i--){
            thePath.append(path[i]+" 到 ");
        }
        for(int i = mm.length-1;i>1;i--){
            shortPath.append(mm[i]+" 到 ");
        }


        text1 = (TextView) findViewById(R.id.list_1);
        text2 = (TextView) findViewById(R.id.list_2);
        text3 = (TextView) findViewById(R.id.list_3);
        text4 = (TextView) findViewById(R.id.list_4);
        text5 = (TextView) findViewById(R.id.list_5);


        if(m[0]==0){
            text1.setText("   暂无车次信息");
            text2.setText("   ");
            text3.setText("   暂无车次信息");
            text4.setText("   ");
            text5.setText("   暂无车次信息");

        }else{
            text1.setText("   "+thePath.toString() +end);
            text2.setText("   "+String.valueOf(m[1])+" 元");
            text3.setText("   "+thePath.toString() +end);
            text4.setText("   "+String.valueOf(m[0])+" Km");
            text5.setText("   "+shortPath.toString() +end);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ShowActivity.this,MustActivity.class);
        startActivity(intent);
    }
}
