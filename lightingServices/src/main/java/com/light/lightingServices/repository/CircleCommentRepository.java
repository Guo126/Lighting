package com.light.lightingServices.repository;

import com.light.lightingServices.model.bean.CircleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CircleCommentRepository extends JpaRepository<CircleComment,Integer> {

    @Query(value = "select * from circle_comment where circle_id=?1 order by time DESC",nativeQuery = true)
    List<CircleComment> getAllComment(Integer cid);


}
