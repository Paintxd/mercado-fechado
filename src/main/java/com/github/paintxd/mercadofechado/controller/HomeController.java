package com.github.paintxd.mercadofechado.controller;


import com.github.paintxd.mercadofechado.service.SessionContext;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named(value = "homeB")
@SessionScoped
public class HomeController {

    public String hello() {
        var user = SessionContext.getInstance().getAuthUser();

        return "Bem vindo " + user.getFullName();
    }
}
