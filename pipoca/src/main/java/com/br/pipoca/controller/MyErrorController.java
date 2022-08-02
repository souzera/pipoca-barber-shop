//package com.br.pipoca.controller;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletResponse;
//
//@RestController
//public class MyErrorController implements ErrorController {
//
//    @RequestMapping(value = "/error")
//    public ModelAndView error(HttpServletResponse response){
//        ModelAndView modelAndView = new ModelAndView("error/error");
//        modelAndView.addObject("type", response.getStatus());
//        return modelAndView;
//    }
//
//    public String getErrorPath(){
//        return "/error";
//    }
//}
