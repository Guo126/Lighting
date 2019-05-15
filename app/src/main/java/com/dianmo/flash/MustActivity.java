package com.dianmo.flash;


import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.dianmo.flash.Entity.user.UserInner;
import com.dianmo.flash.Fragment.FragmentA;
import com.dianmo.flash.Fragment.FragmentB;
import com.dianmo.flash.Fragment.FragmentC;
import com.dianmo.flash.Fragment.FragmentD;
import com.dianmo.flash.uitl.WsManager;


public class MustActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView item1,item2,item3,item4;
    private FragmentManager fragmentManager;
    private ImageView[] ivs;
    public String data;
    public UserInner inner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_must);

        item1 = (ImageView) findViewById(R.id.item1);
        item1.setOnClickListener(this);
        item2 = (ImageView) findViewById(R.id.item2);
        item2.setOnClickListener(this);
        item3 = (ImageView) findViewById(R.id.item3);
        item3.setOnClickListener(this);
        item4 = (ImageView) findViewById(R.id.item4);
        item4.setOnClickListener(this);
        ivs = new ImageView[]{item1,item2,item3,item4};
        fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();//创建一个事务
        transaction.replace(R.id.content, new FragmentA());
        transaction.commit();//事务一定要提交，replace才会有效
        ivs[0].setImageResource(R.drawable.messagese);

        LoginActivity.wsManager.register(new WsManager.IOnMsgReceive() {
            @Override
            public void onReceive(String code,String value) {
                if (!code.equals("pp"))
                    return;
                data = value.substring(11);
                if (updateMsg != null)
                    updateMsg.onUpdate(value.substring(0,10));
            }
        });
    }

    private IUpdateMsg updateMsg;

    public void setUpdateMsg(IUpdateMsg updateMsg) {
        this.updateMsg = updateMsg;
    }

    public interface IUpdateMsg{
        void onUpdate(String id) ;
    }
    @Override
    public void onClick(View view) {
        setIcon();
        switch (view.getId()){
            case R.id.item1: {
                item1.setImageResource(R.drawable.messagese);
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();//创建一个事务
                transaction.replace(R.id.content, new FragmentA());
                transaction.commit();//事务一定要提交，replace才会有效
                break;
            }
            case R.id.item2: {
                item2.setImageResource(R.drawable.bookse);
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();//创建一个事务
                transaction.replace(R.id.content, new FragmentB());
                transaction.commit();//事务一定要提交，replace才会有效
                break;
            }

            case R.id.item3: {
                item3.setImageResource(R.drawable.findse);
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();//创建一个事务
                transaction.replace(R.id.content, new FragmentC());
                transaction.commit();//事务一定要提交，replace才会有效
                break;
            }
            case R.id.item4: {
                item4.setImageResource(R.drawable.userse);
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();//创建一个事务
                transaction.replace(R.id.content, new FragmentD());
                transaction.commit();//事务一定要提交，replace才会有效
                break;
            }

        }
    }

    public void setIcon(){
        //这个方法设置底部导航栏选中时的效果

        ivs[0].setImageResource(R.drawable.message);
        ivs[1].setImageResource(R.drawable.book);
        ivs[2].setImageResource(R.drawable.find);
        ivs[3].setImageResource(R.drawable.user);


    }



}
