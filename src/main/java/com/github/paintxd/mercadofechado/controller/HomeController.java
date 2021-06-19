package com.github.paintxd.mercadofechado.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.net.http.HttpClient;

@Named(value = "homeB")
@SessionScoped
public class HomeController {

    @Autowired
    private HttpSession session;

    public String hello(String fullName) {
        System.out.println(session.getAttribute("user"));
        return "Bem vindo " + fullName;
    }
}
