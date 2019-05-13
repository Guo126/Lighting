package com.dianmo.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.dianmo.flash.R;

public class AudioButton extends android.support.v7.widget.AppCompatButton implements AudioManager.AudioStateListener {
    private static final  int DIS_CANCEL =50;
    private static final  int STATE_NORMAL =1;
    private static final  int STATE_RECORDING =2;
    private static final  int STATE_CANCEL =3;
    private boolean isRecording = false;
    private int mCurrState = STATE_NORMAL;
    private DialogManager mDialog;
    private AudioManager mAudioManager;
    private float mTime;
    //是否触发longclick
    private boolean mReady =false;

    public AudioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDialog = new DialogManager(getContext());
        String dir = Environment.getExternalStorageDirectory()+"/Fire_APP";
        mAudioManager = AudioManager.getInstance(dir);
        mAudioManager.setAudioStateListener(this);
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mReady=true;
                mAudioManager.prepareAudio();
                return false;
            }
        });
    }
//录音正常完成后的回调
    public interface  AudioFinishListener {
        void onFinish(float seconds,String filePath);
    }

    private AudioFinishListener mListener;

    public void setAudioFinishListener(AudioFinishListener listener){
        mListener =listener;
    }
    private Runnable mGetVoiceLevelRunnable = new Runnable() {
        @Override
        public void run() {
            while(isRecording){
                try {
                    Thread.sleep(100);
                    handler.sendEmptyMessage(MSG_VOICE_CHANGED);
                    mTime+=0.1f;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    };
    private static final int MSG_AUDIO_PREPARED = 1100;
    private static final int MSG_VOICE_CHANGED = 1101;
    private static final int MSG_DIALOG_DIMISS = 1111;

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case MSG_AUDIO_PREPARED:
                    mDialog.showRecordingDialog();
                    isRecording =true;
                    new Thread(mGetVoiceLevelRunnable).start();
                    break;
                case MSG_VOICE_CHANGED:
                    mDialog.updateVoice(mAudioManager.getVoiceLevel(7));
                    break;
                case MSG_DIALOG_DIMISS:
                    mDialog.dismissDialog();
                    break;
            }
        }
    };
    @Override
    public void wellPrepared() {
        handler.sendEmptyMessage(MSG_AUDIO_PREPARED);
    }

    public AudioButton(Context context) {
        this(context,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x=(int)event.getX();
        int y=(int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                changeState(STATE_RECORDING);
                break;
            case MotionEvent.ACTION_MOVE:
                if(isRecording){
                    //根据坐标，看是否取消
                    if(wantCancel(x,y)){
                        changeState(STATE_CANCEL);
                    }else{
                        changeState(STATE_RECORDING);
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                if(!mReady){
                    reset();
                    return super.onTouchEvent(event);
                }
                if(!isRecording||mTime<=0.6f){
                    mDialog.tooShort();
                    mAudioManager.cancel();
                    handler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS,1300);  //延时
                }
                if(mCurrState==STATE_RECORDING){
                    mDialog.dismissDialog();
                    if(mListener!=null){
                        mListener.onFinish(mTime,mAudioManager.getCurrentPath());
                    }
                    mAudioManager.release();

                }else if(mCurrState==STATE_CANCEL){
                    mDialog.dismissDialog();
                    mAudioManager.cancel();
                }
                reset();
                break;

        }
        return super.onTouchEvent(event);
    }
//恢复标志位
    private void reset() {
        isRecording=false;
        isRecording=false;
        mTime=0;
        changeState(STATE_NORMAL);
    }

    private boolean wantCancel(int x, int y) {
        if(x<0||x>getWidth()){
            return true;
        }
        if(y<-DIS_CANCEL||y>getHeight()+DIS_CANCEL){
            return true;
        }
        return false;
    }

    private void changeState(int state) {
        if(mCurrState!=state){
            mCurrState=state;
            switch (state){
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.btn_normal);
                    setText(R.string.recorder_normal);
                    break;
                case STATE_RECORDING:
                    setBackgroundResource(R.drawable.btn_recording);
                    setText(R.string.recorder_recording);
                    if(isRecording){
                        mDialog.recording();
                    }
                    break;
                case STATE_CANCEL:
                    setBackgroundResource(R.drawable.btn_recording);
                    setText(R.string.recorder_finish);
                    if(isRecording){
                        mDialog.wantCancelDialog();
                    }
                    break;
            }
        }
    }


}
