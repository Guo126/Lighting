package com.dianmo.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.dianmo.flash.R;

public class DialogManager {
    private Dialog mDialog;
    private ImageView icon,voice;
    private TextView alert;
    private Context context;

    public DialogManager(Context context) {
        this.context = context;
    }

    public void showRecordingDialog(){
        mDialog = new Dialog(context,R.style.DialogTheme);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_recording,null);
        mDialog.setContentView(view);
        icon = (ImageView)mDialog.findViewById(R.id.recorder_img);
        voice = (ImageView)mDialog.findViewById(R.id.recorder_img1);
        alert = (TextView)mDialog.findViewById(R.id.recorder_dialog);
        mDialog.show();
    }

    public void recording(){
        if(mDialog!=null&&mDialog.isShowing()){
            icon.setVisibility(View.VISIBLE);
            voice.setVisibility(View.VISIBLE);
            alert.setVisibility(View.VISIBLE);
            icon.setImageResource(R.drawable.recorder);
            alert.setText("手指上滑，取消发送");
        }
    }

    public void wantCancelDialog(){
        if(mDialog!=null&&mDialog.isShowing()){
            icon.setVisibility(View.VISIBLE);
            voice.setVisibility(View.GONE);
            alert.setVisibility(View.VISIBLE);
            icon.setImageResource(R.drawable.cancel);
            alert.setText("松开手指，取消发送");
        }
    }

    public void tooShort(){
        if(mDialog!=null&&mDialog.isShowing()){
            icon.setVisibility(View.VISIBLE);
            voice.setVisibility(View.GONE);
            alert.setVisibility(View.VISIBLE);
            icon.setImageResource(R.drawable.voice_to_short);
            alert.setText("录音时间过短");
        }
    }

    public void dismissDialog(){
        if(mDialog!=null&&mDialog.isShowing()){
           mDialog.dismiss();
           mDialog = null;
        }
    }

    public void updateVoice(int level){
        if(mDialog!=null&&mDialog.isShowing()){
            icon.setVisibility(View.VISIBLE);
            voice.setVisibility(View.VISIBLE);
            alert.setVisibility(View.VISIBLE);
            int resID = context.getResources().getIdentifier("v"+level,"drawable",context.getPackageName());
            voice.setImageResource(resID);
        }

    }
}
