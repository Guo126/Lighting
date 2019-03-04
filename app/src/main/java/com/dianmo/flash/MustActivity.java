package com.dianmo.flash;


import android.support.v4.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.dianmo.flash.Fragment.FragmentA;
import com.dianmo.flash.Fragment.FragmentB;



public class MustActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView item1,item2;
    private FragmentManager fragmentManager;
    private ImageView[] ivs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_must);
        fragmentManager = getSupportFragmentManager();
        item1 = (ImageView) findViewById(R.id.item1);
        item1.setOnClickListener(this);
        item2 = (ImageView) findViewById(R.id.item2);
        item2.setOnClickListener(this);
        ivs = new ImageView[]{item1,item2};
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();//创建一个事务
        transaction.replace(R.id.content, new FragmentA());
        transaction.commit();//事务一定要提交，replace才会有效
        setCheck(0);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item1: {
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();//创建一个事务
                transaction.replace(R.id.content, new FragmentA());
                transaction.commit();//事务一定要提交，replace才会有效
                setCheck(0);//自定义方法
                break;
            }
            case R.id.item2: {
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();//创建一个事务
                transaction.replace(R.id.content, new FragmentB());
                transaction.commit();//事务一定要提交，replace才会有效
                setCheck(1);//自定义方法
                break;
            }
            default:break;
        }
    }

    public void setCheck(int itemId){
               //这个方法设置底部导航栏选中时的效果
             for (int i = 0; i < 2; i++) {
                      ivs[i].setColorFilter(Color.BLACK);
                  //     tvs[i].setTextColor(Color.parseColor("#0f0f0f"));
                   }
                 ivs[itemId].setColorFilter(Color.parseColor("#FFD9682F"));
             //  tvs[itemId].setTextColor(Color.GREEN);
           }



}
