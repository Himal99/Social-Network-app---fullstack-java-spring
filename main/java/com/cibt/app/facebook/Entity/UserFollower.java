package com.cibt.app.facebook.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user_follower")
public class UserFollower {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "follower_id",referencedColumnName = "id",updatable = true)
    @ManyToOne
    private User followeruser;
    @JoinColumn(name = "following_id",referencedColumnName = "id",updatable = true)
    @ManyToOne
    private User followinguser;
    @JoinColumn(name = "user_post",referencedColumnName = "id",updatable = true)
    @ManyToOne
    private UserPost post;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getFolloweruser() {
        return followeruser;
    }

    public void setFolloweruser(User followeruser) {
        this.followeruser = followeruser;
    }

    public User getFollowinguser() {
        return followinguser;
    }

    public void setFollowinguser(User followinguser) {
        this.followinguser = followinguser;
    }

    public UserPost getPost() {
        return post;
    }

    public void setPost(UserPost post) {
        this.post = post;
    }



    
}