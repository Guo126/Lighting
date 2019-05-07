package com.dianmo.view;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AudioManager {
    private MediaRecorder mediaRecorder;
    private String mDir;
    private String mCurrentPath;
    private  boolean isPrepare =false;
    private static AudioManager mInstance;
    private AudioManager(String dir){
        mDir = dir;
    };

    public String getCurrentPath() {
        return mCurrentPath;
    }

    public interface AudioStateListener{
       void wellPrepared();
    }

    public AudioStateListener mListener;
   public void setAudioStateListener(AudioStateListener listener){
       mListener = listener;
   }

    public static AudioManager getInstance(String dir){
        if(mInstance==null){
            synchronized (AudioManager.class){
                if(mInstance==null){
                    mInstance=new AudioManager(dir);
                }
            }
        }
        return mInstance;
    }


    public void prepareAudio(){
        try {
            isPrepare =false;
            File dir = new File(mDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = generateFileName();
            File file = new File(dir, fileName);
            mCurrentPath = file.getAbsolutePath();
            mediaRecorder = new MediaRecorder();
            //输出文件
            mediaRecorder.setOutputFile(file.getAbsolutePath());
            //设置音频源为麦克风
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置音频格式
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            //设置音频编码amr
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);


            mediaRecorder.prepare();
            mediaRecorder.start();
            isPrepare =true;
            if(mListener!=null){
                mListener.wellPrepared();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//随机生成文件名称
    private String generateFileName() {
       return UUID.randomUUID().toString()+".amr";
    }

    public void release(){
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder =null;
    }

    public int getVoiceLevel(int maxLevel){
        if(isPrepare){
           // mediaRecorder.getMaxAmplitude()  1-32767
            try{
                return maxLevel* mediaRecorder.getMaxAmplitude()/32768 + 1;
            }catch (Exception e){

            }

        }
        return 1;
    }

    public void cancel(){
        release();
        if(mCurrentPath!=null){
            File file = new File(mCurrentPath);
            file.delete();
            mCurrentPath=null;
        }


    }
}
