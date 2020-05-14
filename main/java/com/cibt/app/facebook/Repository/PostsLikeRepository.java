package com.cibt.app.facebook.Repository;

import com.cibt.app.facebook.Entity.PostsLikes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsLikeRepository extends JpaRepository<PostsLikes, Integer> {
    
    @Query(value = "select *from tbl_posts_like where post_id=? and user_id=?", nativeQuery = true)
    PostsLikes userLikes(int post_id, int user_id);

}