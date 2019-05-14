package com.light.lightingServices.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;



@ServerEndpoint(value = "/chat/{id}")
@Component
public class WebSocketService {
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String,WebSocketService> webSocketHashMap = new ConcurrentHashMap<>();

    private Session session;
    private String id;

    public static ChatServices chatServices;
    private static final Logger log = LoggerFactory.getLogger(WebSocketService.class);


    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session)
    {
        this.session = session;
        this.id = id;
        webSocketHashMap.put(id,this);
        addOnlineCount();

        log.info("new connection\ncurrent online:"+onlineCount);

        sendNotDelivered(id);
    }

    @OnClose
    public void onClose()
    {
        webSocketHashMap.remove(id);
        reduceOnlineCount();

        log.info("close a connect\ncurrent online:"+onlineCount);
    }

    @OnMessage
    public void onMessage(String msg,Session session)
    {
    }

    public static void sendMessage(String uid,String tid,String msg)
    {
        String newMsg = uid + msg;
        if(webSocketHashMap.get(tid) != null)
        {
            WebSocketService webSocketService = webSocketHashMap.get(tid);
            try {
                webSocketService.sendToUser(newMsg,tid);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //TODO 用户不在线
            //StaticServicesUtil.callServices(ChatServices.class).targetNotOnline(tid, newMsg);
            chatServices.targetNotOnline(tid,msg);
        }
    }

    public static boolean hasExist(BigInteger uid)
    {
        String id = uid.toString();
        return webSocketHashMap.containsKey(id);
    }

    public static void sendMessage(String uid,String tid,byte[] msg) {

        byte[] newMsg = new byte[msg.length + 11];
        for (int i = 0; i < 11; i++) {
            newMsg[i] = (byte) Integer.parseInt(String.valueOf(uid.charAt(i)));
        }
        System.arraycopy(msg, 0, newMsg, 11, newMsg.length - 11);
        sendMessage(tid,newMsg);
    }

    public static void sendMessage(String tid,byte[] msg)
    {
        if (webSocketHashMap.get(tid) != null) {
            WebSocketService webSocketService = webSocketHashMap.get(tid);
            try {
                webSocketService.sendToUser(msg, tid);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //TODO 用户不在线
            //StaticServicesUtil.callServices(ChatServices.class).targetNotOnline(tid, msg);
            chatServices.targetNotOnline(tid,msg);
        }
    }



    @OnError
    public void onError(Session session,Throwable throwable)
    {
        throwable.printStackTrace();
    }

    private void sendMessage(String msg) throws IOException
    {
        this.session.getBasicRemote().sendText(msg);
    }
    private void sendMessage(byte[] msg) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg);
        this.session.getBasicRemote().sendBinary(byteBuffer);
    }

    private void sendToUser(String msg, String id) throws IOException
    {
        if(webSocketHashMap.get(id) != null)
        {
            if(!this.id.equals(id))
            {
                webSocketHashMap.get(id).sendMessage(msg);
            }
        }
    }

    private void sendToUser(byte[] msg, String id) throws IOException
    {
        if(webSocketHashMap.get(id) != null)
        {
            if(!this.id.equals(id))
            {
                webSocketHashMap.get(id).sendMessage(msg);
            }
        }
    }

    private void sendNotDelivered(String id)
    {
        //List<byte[]> msg= StaticServicesUtil.callServices(ChatServices.class).getMsg(id);
        List<byte[]> msg = chatServices.getMsg(id);
        if(msg == null)
            return;

        for (byte[] m : msg)
        {
            sendMessage(id,m);
        }
    }

    public static synchronized int getOnlineCount()
    {
        return onlineCount;
    }

    private static synchronized void addOnlineCount()
    {
        onlineCount++;
    }


    private static synchronized void reduceOnlineCount()
    {
        onlineCount--;
    }

}
