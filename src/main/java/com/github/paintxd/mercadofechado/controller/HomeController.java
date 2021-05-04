package com.github.paintxd.mercadofechado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class HomeController {

    @GetMapping("/")
    public String renderHome(Model model, Locale locale) {
        System.out.println("Home Page Requested, locale = " + locale);

        return "index.jsp";
    }

    @GetMapping("/jsf")
    public String renderJsf(Model model) {
        return "teste.xhtml";
    }
}
