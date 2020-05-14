package com.cibt.app.facebook.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_posts_like")
public class PostsLikes {

    @Id
    
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "likes")
    private String likes;
    @JoinColumn(name = " post_id",referencedColumnName = "id", nullable=false)
    @OneToOne
    private UserPost post;
    @JoinColumn(name = " user_id",referencedColumnName = "id", nullable=false)
    @OneToOne
    private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public UserPost getPost() {
		return post;
	}
	public void setPost(UserPost post) {
		this.post = post;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

    
}