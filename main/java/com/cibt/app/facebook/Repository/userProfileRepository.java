package com.cibt.app.facebook.Repository;

import java.util.List;

import com.cibt.app.facebook.Entity.UserProfile;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface userProfileRepository extends JpaRepository<UserProfile,Integer>{

    @Query(value = " select *from tbl_user_profile where user_id=?",nativeQuery = true)
    List<UserProfile> getUserProfile(int id);

    UserProfile findByUserId(int id);

}