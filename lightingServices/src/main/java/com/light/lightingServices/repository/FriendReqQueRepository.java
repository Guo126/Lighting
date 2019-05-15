package com.light.lightingServices.repository;

import com.light.lightingServices.model.DTO.FriendReqMsg;
import com.light.lightingServices.model.bean.FriendReqQue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface FriendReqQueRepository extends JpaRepository<FriendReqQue,Integer> {

    @Query(value = "select 1 from friend_require_que where uid=?1 and fid=?2",nativeQuery = true)
    Integer hasExist(BigInteger uid,BigInteger fid);

    @Query(value = "select new com.light.lightingServices.model.DTO.FriendReqMsg(u.name,u.phone,u.icon) " +
            "from User u,FriendReqQue frq where u.phone=frq.fid and frq.uid=?1")
    List<FriendReqMsg> getReqMsg(BigInteger uid);

    @Query(value = "delete from friend_require_que where uid=?1 and fid=?2",nativeQuery = true)
    @Modifying
    void deleteOne(BigInteger uid, BigInteger fid);
}
