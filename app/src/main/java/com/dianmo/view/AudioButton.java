package com.dianmo.view;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.dianmo.flash.R;

public class AudioButton extends android.support.v7.widget.AppCompatButton {
    private static final  int DIS_CANCEL =50;
    private static final  int STATE_NORMAL =1;
    private static final  int STATE_RECORDING =2;
    private static final  int STATE_CANCEL =3;
    private boolean isRecording = false;
    private int mCurrState = STATE_NORMAL;
    private DialogManager mDialog;
    public AudioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDialog = new DialogManager(getContext());
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mDialog.showRecordingDialog();
                isRecording =true;
                return false;
            }
        });
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
                if(mCurrState==STATE_RECORDING){
                    mDialog.dismissDialog();
                    //release
                    //callback
                }else if(mCurrState==STATE_CANCEL){
                    mDialog.dismissDialog();

                }
                reset();
                break;

        }
        return super.onTouchEvent(event);
    }
//恢复标志位
    private void reset() {
        isRecording=false;
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
