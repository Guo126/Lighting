package com.dianmo.flash.Entity.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MsgManager {
    public static MsgManager getmInstance() {
        return mInstance;
    }

    public void setmInstance(MsgManager mInstance) {
        this.mInstance = mInstance;
    }

    private static MsgManager mInstance = new MsgManager();

    public HashMap<String, List<String>> getMsgList() {
        return msgList;
    }

    public void setMsgList(HashMap<String, List<String>> msgList) {
        this.msgList = msgList;
    }

    private HashMap<String,List<String>> msgList = new HashMap<String,List<String>>();



    public void addMsg(final String id,final String msg){
        if(msgList.containsKey(id)) {
            msgList.get(id).add("a" + msg);
            return;
        }
        final List<String> newMsg = new ArrayList<String>();
        newMsg.add("a" + msg);
        msgList.put(id,newMsg);

    }

    public void remove(){
        msgList.clear();
    }


}
