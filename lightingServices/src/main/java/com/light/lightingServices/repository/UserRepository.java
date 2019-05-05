package com.light.lightingServices.repository;

import com.light.lightingServices.model.DTO.UserMsg;
import com.light.lightingServices.model.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select * from user where phone=?1 and psw=?2",nativeQuery = true)
    User login(BigInteger phone, String psw);

    @Query(value = "select * from user where phone=?1",nativeQuery = true)
    List<User> getUser(BigInteger id);

    @Query(value = "select 1 from user where phone=?1",nativeQuery = true)
    Integer exist(BigInteger phone);
}
