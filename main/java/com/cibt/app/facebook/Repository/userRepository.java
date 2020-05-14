package com.cibt.app.facebook.Repository;

import java.util.List;

import com.cibt.app.facebook.Entity.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByemail(String email);
    User findByUsernameAndPassword(String username, String password);

    @Query(value = " select *from tbl_user order by created_date desc limit 4",nativeQuery = true)
    List<User> finBydesc();
}