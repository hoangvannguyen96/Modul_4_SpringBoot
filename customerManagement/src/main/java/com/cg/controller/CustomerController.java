package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @RequestMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");
        return modelAndView;
    }

    @RequestMapping("/info")
    public String showInforPage() {
        return "customer/info";
    }

    @RequestMapping("/create")
    public String showCratePage() {
        return "customer/create";
    }
}
