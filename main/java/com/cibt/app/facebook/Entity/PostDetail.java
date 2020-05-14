package com.cibt.app.facebook.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tbl_posts_detail")
public class PostDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "post_like")
    private int like;
    @Column(name = "share")
    private int share;
    @Column(name = "comment_user")
    private String comment_user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

   
    @JsonIgnore
    @JoinColumn(name = "post_id",referencedColumnName = "id")
    @ManyToOne()
    private UserPost post;
   
    
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @ManyToOne()
    private User user;

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

  

    public String getComment_user() {
        return comment_user;
    }

    public void setComment_user(String comment_user) {
        this.comment_user = comment_user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserPost getPost() {
        return post;
    }

    public void setPost(UserPost post) {
        this.post = post;
    }

    

    
    
}