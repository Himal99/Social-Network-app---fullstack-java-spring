package com.cibt.app.facebook.Controller;

import java.util.List;

import javax.transaction.Transactional;

import com.cibt.app.facebook.Entity.PostDetail;

import com.cibt.app.facebook.Entity.PostsLikes;
import com.cibt.app.facebook.Entity.UserFollower;
import com.cibt.app.facebook.Entity.UserPost;
import com.cibt.app.facebook.Entity.UserProfile;

import com.cibt.app.facebook.Repository.UserPostRepository;
import com.cibt.app.facebook.Repository.userProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Controller
@RequestMapping(value = "/post")
public class PostController extends SecuredController {
  @Autowired
  private UserPostRepository postrepository;

  @GetMapping

  public String userpost(Model model) {

    // using pageable to display all users posts
    org.springframework.data.domain.Pageable page = PageRequest.of(0, 5, Sort.by("id").descending());
    model.addAttribute("posts", postrepository.findAll(page));
   
    return "post/user-post";
  }

  // save status
  @PostMapping(value = "/status")
  public String savepost(@RequestParam("message") String message) {
    UserPost post = new UserPost();
    post.setStatus(true);
    post.setUser(getLoggedInUser());
    // post.setProfile(getLoggedInUser().getProfile());
    // need to solve

    post.setMessage(message);
    postrepository.save(post);
    return "redirect:/account?success";
  }


  @GetMapping(value = "/userPost/{id}")
 @ResponseBody
  public UserPost getposts(@PathVariable("id")int id){
   return postRepository.findByuserId(id);

  }


  // save likes
  // @PostMapping(value = "/likes/{id}")
  // public String likes(PostsLikes like,
  // @PathVariable("id")int id){
  //   if(likesRepository.userLikes(id, getLoggedInUser().getId())==null){
  //   PostsLikes likes=new PostsLikes();
  //   likes.setUser(getLoggedInUser());
  //   likes.setLikes("likes");
  //   UserPost post=postRepository.findById(id).get();
  //   likes.setPost(post);
  //   likesRepository.save(likes);
  //   return "redirect:/account?success";
  // }
  // else{
  //    return "redirect:/account";
  // }

  // }

  // check user likes
@GetMapping(value = "/checklikes/{id}")
@ResponseBody
public boolean checklikes(@PathVariable("id")int id){
  if(likesRepository.userLikes(id, getLoggedInUser().getId())!=null){
     return true;
  }
  
  else{
     return false;
  }
}




  // get user profile detail according to user_id
  @GetMapping(value = "/userprofile/{id}")
  @ResponseBody

  public List<UserProfile> profile(@PathVariable("id") int id) {
    return profileRepository.getUserProfile(id);
  }

  // save a comment
  @PostMapping(value = "/comment/{id}")
  
  public String savecomment(@PathVariable("id") int id,
   @RequestParam("comment") String comment) {

    PostDetail detail = new PostDetail();
     UserPost post = postRepository.findById(id).get();
    detail.setPost(post);
    detail.setUser(getLoggedInUser());
    detail.setComment(comment);
    DetailRepository.save(detail);
    return "redirect:/account?success";
  }

  // get users comments according to post_id
 

  // get loggedinuserpost

  // @GetMapping(value = "/getPosts")
  // @ResponseBody
  // public List<UserPost> getPosts(){
  //   return postRepository.getUserPost(getLoggedInUser().getId());
  // }

}