package com.cibt.app.facebook.Repository;

import java.util.List;

import com.cibt.app.facebook.Entity.UserPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Integer> {
  
    List<UserPost> findByuserIdOrderByIdDesc(int id);
    UserPost findByuserId(int id);

    @Query(value = " select *from tbl_user_posts where user_id=?",nativeQuery = true)
    UserPost getUserPost(int id);




  



    
}