package com.light.lightingServices.repository;

import com.light.lightingServices.model.bean.Circle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface CircleRepository extends JpaRepository<Circle,Integer> {

    @Query(value = "select c.* from circle c,friends f where c.user_id=?1 or " +
            "f.user_a_id=?1 and f.user_b_id=c.user_id order by time DESC",nativeQuery = true)
    List<Circle> getAllCircle(BigInteger uid);
}
