package com.br.pipoca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @GetMapping
    @RequestMapping(value = "/dashboard/{chave}")
    public ModelAndView dashboard(@PathVariable String chave){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin.html");
        return modelAndView;
    }
}
