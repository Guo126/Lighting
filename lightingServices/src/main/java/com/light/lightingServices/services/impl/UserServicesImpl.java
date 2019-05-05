package com.light.lightingServices.services.impl;

import com.light.lightingServices.model.DTO.BaseMsg;
import com.light.lightingServices.model.DTO.UserMsg;
import com.light.lightingServices.model.bean.Friends;
import com.light.lightingServices.model.bean.User;
import com.light.lightingServices.repository.FriendRepository;
import com.light.lightingServices.repository.UserRepository;
import com.light.lightingServices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private
    UserRepository userRepository;
    @Autowired
    private
    FriendRepository friendRepository;

    @Override
    public BaseMsg<UserMsg> login(BigInteger phone, String psw) {
        User master = userRepository.login(phone,psw);
        if(master == null)
        {
            BaseMsg<UserMsg> baseMsg = new BaseMsg<>();
            baseMsg.setSuccess(false);
            return baseMsg;
        }

        List<Friends> friendsList = friendRepository.getFriends(master.getPhone());
        BaseMsg<UserMsg> returnMsg = new BaseMsg<>();
        returnMsg.setSuccess(true);
        UserMsg userMsg = new UserMsg();
        userMsg.setName(master.getName());
        List<BigInteger> friendsIdList = new ArrayList<>();
        for (Friends f : friendsList)
        {
            friendsIdList.add(f.getUser_b_id());
        }

        userMsg.setFriendIDList(friendsIdList);
        returnMsg.setMsg(userMsg);
        return returnMsg;
    }

    @Override
    public BaseMsg<UserMsg> register(BigInteger phone, String psw, String name) {
        User user = new User(phone,psw,name,null);

        if(userRepository.exist(phone) != null)
        {
            BaseMsg<UserMsg> baseMsg = new BaseMsg<>();
            baseMsg.setSuccess(false);
            baseMsg.setAlter("账号已存在");
            return baseMsg;
        }

        userRepository.save(user);

        BaseMsg<UserMsg> baseMsg = new BaseMsg<>();
        baseMsg.setSuccess(true);

        UserMsg userMsg = new UserMsg();
        userMsg.setName(user.getName());

        baseMsg.setMsg(userMsg);

        return baseMsg;
    }
}
