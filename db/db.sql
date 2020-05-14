create table tbl_user(id int primary key auto_increment, username varchar(100),email varchar(100),
password varchar(100),
created_date timestamp default current_timestamp,status boolean default true);

create table tbl_user_profile(id int primary key auto_increment, education text,
skills text,gender varchar(10),address  varchar(50) ,description text,
firstname varchar(100),lastname varchar(100),
user_id int, date_of_birth Date
);

ALTER TABLE tbl_user_profile add followers_id int;



alter table tbl_user_profile add FOREIGN KEY(user_id) REFERENCES tbl_user(id);

create table tbl_user_posts(id int primary key auto_increment,
message text, user_id int,
created_date timestamp default current_timestamp,status boolean default true);

alter table tbl_user_posts ADD FOREIGN KEY (user_id) REFERENCES tbl_user(id);

create table tbl_posts_detail(id int primary KEY auto_increment, comment text, post_like int, share int,
created_date timestamp default current_timestamp, post_id int
);

alter TABLE tbl_posts_detail ADD FOREIGN KEY(post_id) REFERENCES tbl_user_posts(id);
alter TABLE tbl_posts_detail add comment_user varchar(50);
alter TABLE tbl_posts_detail add user_id int;
alter TABLE tbl_posts_detail add FOREIGN KEY(user_id) REFERENCES tbl_user(id);


create TABLE tbl_user_follower(id int primary key auto_increment, follower_id int, following_id int );

alter TABLE tbl_user_follower ADD FOREIGN KEY(follower_id) REFERENCES tbl_user(id);
alter TABLE tbl_user_follower ADD FOREIGN KEY(following_id) REFERENCES tbl_user(id);
alter table tbl_user_profile add profile_pic varchar(50);
alter table tbl_user_posts add userProfile_id int;

ALTER TABLE tbl_user_posts add FOREIGN KEY(userProfile_id) REFERENCES tbl_user_profile(id);

alter table tbl_user_follower add user_post int;

alter table tbl_user_follower add FOREIGN KEY(user_post) REFERENCES tbl_user_posts(id);


create table tbl_posts_comment(id int primary key auto_increment,
 post_id int, comment_id int, created_date timestamp default current_timestamp);

 alter TABLE tbl_posts_comment ADD FOREIGN KEY(post_id) REFERENCES tbl_user_posts(id);
 ALTER TABLE tbl_posts_comment ADD FOREIGN KEY(comment_id) REFERENCES tbl_posts_detail(id);

 create table tbl_posts_like(id int primary key auto_increment, likes varchar(10), post_id int,
 user_id int,
 created_date timestamp default current_timestamp);

 ALTER TABLE tbl_posts_like ADD FOREIGN KEY(post_id) REFERENCES tbl_user_posts(id);
 ALTER TABLE tbl_posts_like ADD FOREIGN KEY(user_id) REFERENCES tbl_user(id);