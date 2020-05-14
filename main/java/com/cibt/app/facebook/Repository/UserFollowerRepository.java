package com.cibt.app.facebook.Repository;

import java.util.List;

import com.cibt.app.facebook.Entity.UserFollower;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowerRepository extends JpaRepository<UserFollower,Integer> {

    @Query(value = " select *from tbl_user_follower where follower_id=? and  following_id=?",nativeQuery = true)

    UserFollower checkFollower(int follower_id, int following_id);
    @Query(value = " select *from tbl_user_follower where following_id=?",nativeQuery = true)

    List<UserFollower> getFollower(int id);

    @Query(value = " select *from tbl_user_follower where follower_id=?",nativeQuery = true)

    List<UserFollower> getFollowing(int id);

   int deleteByFolloweruserIdAndFollowinguserId(int id,int folloingId);
    
}