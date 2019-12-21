//package com.security.demospringsecurity.controller;
//
//import com.security.demospringsecurity.model.User;
//import com.security.demospringsecurity.security.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.validation.Valid;
//
//@Controller
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping(value = {"/login","/"} ,method = RequestMethod.GET)
//    public ModelAndView login() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("user/login");
//        return mv;
//    }
//
//    @RequestMapping(value = "/signup", method = RequestMethod.GET)
//    public ModelAndView signup(){
//        ModelAndView mv = new ModelAndView();
//        User user = new User();
//        mv.setViewName("user/signup");
//        mv.addObject("user", user);
//        return mv;
//    }
//
//    @RequestMapping(value = "/signup",method =RequestMethod.POST )
//    public ModelAndView createUser(@Valid User user, BindingResult bindingResult){
//        ModelAndView mv= new ModelAndView();
//        User userExits = userService.findUserByEmail(user.getEmail());
//        if(userExits != null){
//            bindingResult.rejectValue("email","error.user","this email already exist");
//        }if (bindingResult.hasFieldErrors()){
//            mv.setViewName("user/signup");
//        }else {
//            userService.saveUser(user);
//            mv.addObject("msg","Registed successfully!!");
//            mv.addObject("user", new User());
//            mv.setViewName("user/signup");
//        }
//        return mv;
//    }
//
//    @RequestMapping(value = "/home",method = RequestMethod.GET)
//    public ModelAndView home(){
//        ModelAndView mv = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//
//        mv.setViewName("/home/home");
//        mv.addObject("userName", user.getFirstname()+ " "+user.getLastname());
//        return mv;
//    }
//
//    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
//    public ModelAndView accessDenied(){
//        ModelAndView mv= new ModelAndView();
//        mv.setViewName("error/access_denied");
//        return mv;
//    }
//}
