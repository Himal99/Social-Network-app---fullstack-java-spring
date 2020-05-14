package com.cibt.app.facebook.Repository;

import java.util.List;

import com.cibt.app.facebook.Entity.PostDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail, Integer>{
    @Query(value = " select *from tbl_posts_detail where post_id=?", nativeQuery = true)
    List<PostDetail> getComment(int id);
}