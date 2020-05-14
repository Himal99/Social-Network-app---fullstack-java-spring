package com.cibt.app.facebook.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping(value = "/refresh")
public class RefreshmentController extends SecuredController {
 
    @GetMapping(value = "/viewDetail/{id}")
  
    public String index(Model model,
    @PathVariable("id")int id){
       model.addAttribute("post", postRepository.findById(id).get());
       
        return "post/view-comment";
    }

   


}