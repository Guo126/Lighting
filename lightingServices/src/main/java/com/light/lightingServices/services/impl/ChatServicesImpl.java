package com.light.lightingServices.services.impl;


import com.light.lightingServices.services.ChatServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatServicesImpl implements ChatServices {

    private static Map<String,List<byte[]>> msgQue = new HashMap<>();
    private static final Object lock = new Object();

    @Override
    public void targetNotOnline(String targetId, byte[] msg) {
        synchronized (lock) {
            msgQue.computeIfAbsent(targetId, k -> new ArrayList<>());
            msgQue.get(targetId).add(msg);
        }
    }

    @Override
    public void targetNotOnline(String targetId, String msg) {
        synchronized (lock) {
            msgQue.computeIfAbsent(targetId, k -> new ArrayList<>());
            msgQue.get(targetId).add(msg.getBytes());
        }
    }

    @Override
    public List<byte[]> getMsg(String id) {
        synchronized (lock) {
            List<byte[]> msg = msgQue.get(id);
            if(msg == null)
                return null;
            msgQue.get(id).clear();
            return msg;
        }
    }
}
