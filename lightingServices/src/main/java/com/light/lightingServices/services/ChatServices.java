package com.light.lightingServices.services;

import java.util.List;

public interface ChatServices {
    void targetNotOnline(String targetId,byte[] msg);
    void targetNotOnline(String targetId,String msg);
    List<byte[]> getMsg(String id);
}
