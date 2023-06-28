package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @GetMapping
    public String showDashboardPage(){
        return "customer/template";
    }
    @GetMapping("/list")
    public String showListPage(){
        return "customer/list";
    }
    @GetMapping("/create")
    public String showCreatePage(){
        return "customer/create";
    }
}
