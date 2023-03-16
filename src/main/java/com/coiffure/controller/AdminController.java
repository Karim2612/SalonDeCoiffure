package com.coiffure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AdminController {

    @RequestMapping(value = { "/admin/dashboard" }, method = RequestMethod.GET)
    public String adminHome() {
        return "admin/dashboard";
    }

    @RequestMapping(value = { "/user/dashboard" }, method = RequestMethod.GET)
    public String userHome() {
        return "user/dashboard";
    }

}
