package com.cibt.app.facebook.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
@Table(name = "tbl_user_posts")
public class UserPost {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "message")
    private String message;
    @Column(name = "created_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;
    // @JoinColumn(name = "userProfile_id",referencedColumnName = "id")
    // @ManyToOne
    // private UserProfile profile;
  

    
    @Column(name = "status")
    private boolean status;
    

    public UserPost() {

    }

    public UserPost(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   

    

    // public UserProfile getProfile() {
    // return profile;
    // }

    // public void setProfile(UserProfile profile) {
    // this.profile = profile;
    // }

}