package com.light.lightingServices.services;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value = "/chat/{id}")
@Component
public class WebSocketService {
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String,WebSocketService> webSocketHashMap = new ConcurrentHashMap<>();

    private Session session;
    private String id;

    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session)
    {
        this.session = session;
        this.id = id;
        webSocketHashMap.put(id,this);
        addOnlineCount();
    }

    @OnClose
    public void onClose()
    {
        webSocketHashMap.remove(id);
        reduceOnlineCount();
    }

    @OnMessage
    public void onMessage(String msg,Session session)
    {

    }

    @OnError
    public void onError(Session session,Throwable throwable)
    {
        throwable.printStackTrace();
    }

    public void sendMessage(String msg) throws IOException
    {
        this.session.getBasicRemote().sendText(msg);
    }

    public void sendToUser(String msg,String id) throws IOException
    {
        if(webSocketHashMap.get(id) != null)
        {
            if(!this.id.equals(id))
            {
                webSocketHashMap.get(id).sendMessage(msg);
            }
        }
        else{
            //TODO 该用户不在线
        }
    }


    public static synchronized int getOnlineCount()
    {
        return onlineCount;
    }

    public static synchronized void addOnlineCount()
    {
        onlineCount++;
    }

    public static synchronized void reduceOnlineCount()
    {
        onlineCount--;
    }

}
