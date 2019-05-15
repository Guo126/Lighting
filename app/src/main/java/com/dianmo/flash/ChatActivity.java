package com.dianmo.flash;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dianmo.flash.Adapter.RecorderAdapter;
import com.dianmo.flash.Entity.user.ChatMsg;
import com.dianmo.flash.Fragment.FragmentA;
import com.dianmo.flash.Fragment.FragmentEdit;
import com.dianmo.flash.Fragment.FragmentSay;
import com.dianmo.flash.uitl.INetCallback;
import com.dianmo.flash.uitl.NetworkUtil;
import com.dianmo.flash.uitl.WsManager;
import com.dianmo.view.AudioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.dianmo.flash.LoginActivity.*;

public class ChatActivity extends AppCompatActivity {
    private TextView name ;
    private ImageView oprate,more;
    private ListView mList;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mData = new ArrayList<Recorder>();
    private AudioButton audioButton;
    private  View animView;
    private ChatMsg chatMsg = new ChatMsg();
    private String friId ;

    private boolean isWrite = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatt);
        //初始化好友id
        friId = getIntent().getStringExtra("id");


        name =(TextView) findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
        oprate = (ImageView)findViewById(R.id.oprate);
        more = (ImageView)findViewById(R.id.more);
        mList = (ListView)findViewById(R.id.list);
        audioButton = (AudioButton)findViewById(R.id.recorder);
        oprate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this,EditActivity.class);
                intent.putExtra("name",name.getText().toString());
                startActivity(intent);
            }
        });

        audioButton.setAudioFinishListener(new AudioButton.AudioFinishListener() {
            @Override
            public void onFinish(float seconds, String filePath) {
                Recorder recorder = new Recorder(seconds,filePath);
                mData.add(recorder);
                mAdapter.notifyDataSetChanged();
                mList.setSelection(mData.size()-1);
                NetworkUtil.transMsg("http://39.106.81.100:9999/firefly/chat/p2p",friId,filePath,ChatMsg.class,new INetCallback<ChatMsg>(){
                    @Override
                    public void onSuccess(ChatMsg msg) {
                        chatMsg = msg;
                    }
                });
            }
        });
        mAdapter = new RecorderAdapter(this,mData);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(animView!=null){
                    animView.setBackgroundResource(R.drawable.adj);
                    animView=null;
                }
                //播放动画
                animView = view.findViewById(R.id.anim);
                animView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable animationDrawable = (AnimationDrawable) animView.getBackground();
                animationDrawable.start();
                //播放语音
                MediaManager.playSound(mData.get(position).filePath, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        animView.setBackgroundResource(R.drawable.adj);
                    }
                });

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resunme();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public class Recorder{
        float time;
        String filePath;

        public Recorder(float time, String filePath) {
            super();
            this.time = time;
            this.filePath = filePath;
        }

        public float getTime() {
            return time;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }
}
