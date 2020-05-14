package com.cibt.app.facebook.Controller;



import javax.servlet.http.HttpSession;

import com.cibt.app.facebook.Entity.User;

import com.cibt.app.facebook.Repository.PostDetailRepository;

import com.cibt.app.facebook.Repository.PostsLikeRepository;
import com.cibt.app.facebook.Repository.UserFollowerRepository;
import com.cibt.app.facebook.Repository.UserPostRepository;
import com.cibt.app.facebook.Repository.userProfileRepository;
import com.cibt.app.facebook.Repository.userRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class SecuredController {
    @Autowired
    protected userRepository userRepository;
    @Autowired
    protected userProfileRepository profileRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    protected PostDetailRepository DetailRepository;
    @Autowired
    protected UserFollowerRepository followerRepository;
    @Autowired
    protected UserPostRepository postRepository;
    
    @Autowired
    protected PostsLikeRepository likesRepository;

    // global variable of loggedinuser.
    @ModelAttribute(value = "currentuser")
    public User getLoggedInUser() {
        return (User) session.getAttribute("user");
        
    }

    

}