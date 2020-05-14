package com.cibt.app.facebook.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.cibt.app.facebook.Entity.PostDetail;
import com.cibt.app.facebook.Entity.User;

import com.cibt.app.facebook.Entity.UserProfile;
import com.cibt.app.facebook.Repository.UserPostRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/account")
public class ProfileController extends SecuredController {

  @Autowired
  private HttpSession session;
  @Autowired
  private UserPostRepository postrepoSitory;

  public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img";


  @GetMapping
  public String index(Model model) {
    User user = (User) session.getAttribute("user");

    UserProfile profile = profileRepository.findByUserId(user.getId());
    model.addAttribute("profile", profile);
    org.springframework.data.domain.Pageable page = PageRequest.of(0, 5, Sort.by("id").descending());
    model.addAttribute("posts", postrepoSitory.findAll(page));

    return "profile/index";
  }


 

  // for page refreshing
  @GetMapping(value = "/about")
  public String about(Model model) {
    User user = (User) session.getAttribute("user");

    UserProfile profile = profileRepository.findByUserId(getLoggedInUser().getId());
    model.addAttribute("profile", profile);
    org.springframework.data.domain.Pageable page = PageRequest.of(0, 5, Sort.by("id").descending());
    model.addAttribute("posts", postrepoSitory.findAll(page));

    return "profile/shared/about";
  }

  @GetMapping(value = "/userpost")
 
  public String post(Model model){
    UserProfile profile=profileRepository.findByUserId(getLoggedInUser().getId());
    model.addAttribute("profile", profile);
    return "post/statusmodal";
  }
  @GetMapping(value = "/userActivity")
 
  public String useractivity(Model model){
    UserProfile profile=profileRepository.findByUserId(getLoggedInUser().getId());
    model.addAttribute("profile", profile);
    return "profile/shared/statusmodal";
  }
  // getprpfilepic
// baki x
  // @GetMapping(value = "/profilepic")
  // @ResponseBody
  // public UserProfile profile(){

  //     return  profileRepository.findByUserId(getLoggedInUser().getId());
  // }

  @GetMapping(value = "/json")
  @ResponseBody
  public User json(HttpServletRequest request) {
    User user = (User) request.getSession().getAttribute("user");
    return user;
  }

  // save profile
  @PostMapping(value="/saveprofile")
  @ResponseBody
  public String saveProfile(HttpSession session,
   @ModelAttribute("userProfile") UserProfile userprofile,
   final @RequestParam("file")MultipartFile file) {

try{
  String fileName = file.getOriginalFilename();
  String filePath = Paths.get(uploadDirectory, fileName).toString();
  String fileType = file.getContentType();
  long size = file.getSize();
  String fileSize = String.valueOf(size);
  Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
  // Save the file locally
  
  BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
 
  stream.write(file.getBytes());
  stream.close();
  
  User user = (User) session.getAttribute("user");

  userprofile.setId(user.getProfile().getId());
  userprofile.setUser(user);
  userprofile.setProfileImage(fileName);

  profileRepository.save(userprofile);

}catch(Exception e){
  e.printStackTrace();
}
    return "redirect:/account";
  }




  @GetMapping(value = "/suggestion")
  @ResponseBody
  public List<User> getSuggestion(){
    return userRepository.finBydesc();
}


@GetMapping(value = "/comments/{id}")
@ResponseBody
public List<PostDetail> getPostDetail(@PathVariable("id") int id) {
  return DetailRepository.getComment(id);
}

@GetMapping(value = "/commentDown/{id}")

public String comment(@PathVariable("id")int id, Model model){
model.addAttribute("comment", DetailRepository.getComment(id));
return "post/commentDown";

}

@GetMapping(value = "/getcomments")
@ResponseBody
public List<PostDetail> comments(){
   return DetailRepository.findAll();
}
}