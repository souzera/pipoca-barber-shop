package com.br.pipoca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController {

    @GetMapping
    @RequestMapping(value = "/error")
    public ModelAndView error(HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("type", response.getStatus());
        return modelAndView;
    }

}
