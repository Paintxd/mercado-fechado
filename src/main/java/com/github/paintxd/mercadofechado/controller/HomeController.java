package com.github.paintxd.mercadofechado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Locale;

@Controller
public class HomeController {

    public String renderHome(Locale locale) {
        System.out.println("Home Page Requested, locale = " + locale);

        return "index.jsp";
    }
}
