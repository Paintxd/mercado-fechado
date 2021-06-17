package com.github.paintxd.mercadofechado.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named(value = "homeB")
@SessionScoped
public class HomeController {

    public String hello() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return "Bem vindo " + authentication.getName();
    }
}
