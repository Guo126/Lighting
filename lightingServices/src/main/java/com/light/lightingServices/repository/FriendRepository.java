package com.light.lightingServices.repository;

import com.light.lightingServices.model.bean.Friends;
import com.light.lightingServices.model.bean.compositeId.FriendsID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface FriendRepository extends JpaRepository<Friends,FriendsID> {

    @Query(value = "select * from friends where user_a_id=?1",nativeQuery = true)
    List<Friends> getFriends(BigInteger id);

    @Query(value = "select 1 from friends where user_a_id=?1 and user_b_id=?2",nativeQuery = true)
    Integer hasFriend(BigInteger uid,BigInteger fid);
}
