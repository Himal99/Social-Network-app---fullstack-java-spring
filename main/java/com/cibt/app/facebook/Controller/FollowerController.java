package com.cibt.app.facebook.Controller;

import java.util.List;

import javax.transaction.Transactional;

import com.cibt.app.facebook.Entity.User;
import com.cibt.app.facebook.Entity.UserFollower;
import com.cibt.app.facebook.Entity.UserPost;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/follower")
public class FollowerController extends SecuredController {

    @PostMapping(value = "/savefollow/")
    public String saveFollower(@RequestParam("username") String username,
     @RequestParam("id") int userid) {

        if (followerRepository.checkFollower(getLoggedInUser().getId(), userid) == null) {
            UserFollower follower = new UserFollower();
            follower.setFolloweruser(getLoggedInUser());
            User user = userRepository.findByUsername(username);
            UserPost post=postRepository.getUserPost(getLoggedInUser().getId());
            follower.setPost(post);
            follower.setFollowinguser(user);
            followerRepository.save(follower);
           



            return "redirect:/account?success";
        } else
            return "redirect:/account?success";

    }


    @PostMapping(value = "/deletefollower")
   @ResponseBody
   @Transactional
    public String unfollow(@RequestParam("followingId")int followingId){
        followerRepository.deleteByFolloweruserIdAndFollowinguserId(getLoggedInUser().getId()
        ,followingId);
        return "redirect:/account?success";
    }

    // to check wheather they are freinds or not
    @GetMapping(value = "/checkfollower/{id}")
    @ResponseBody
    public boolean checkfollower(@PathVariable("id") int nextuserid) {
        if (followerRepository.checkFollower(getLoggedInUser().getId(), nextuserid) != null) {
            return true;
        } else {
            return false;
        }
    }

    // get followers
    @GetMapping("/getfollower")
    @ResponseBody

    public List<UserFollower> getfollower() {
        return followerRepository.getFollower(getLoggedInUser().getId());
    }

    // get following
    @GetMapping("/getfollowing")
    @ResponseBody

    public List<UserFollower> getfollowing() {
        return followerRepository.getFollowing(getLoggedInUser().getId());
    }

}