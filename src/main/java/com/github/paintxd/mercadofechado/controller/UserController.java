package com.github.paintxd.mercadofechado.controller;

import com.github.paintxd.mercadofechado.controller.dto.UserDto;
import com.github.paintxd.mercadofechado.model.User;
import com.github.paintxd.mercadofechado.repository.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String REDIRECT_HOME = "redirect:/user";
    private static final String LOAD_HOME = "userCrud";
    private static final String FORM_ACTION = "action";
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String usersHome(Model model) {
        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("user", new UserDto());
        model.addAttribute(FORM_ACTION, "save");

        return LOAD_HOME;
    }

    @GetMapping("/id/{id}")
    public String getUserById(@PathVariable("id") Long id, @Param("action") String action, Model model) {
        var user = userRepository.findById(id).orElse(new User());

        String formAction = action != null ? "update/id/" + id : "save";

        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("user", new UserDto(user));
        model.addAttribute(FORM_ACTION, formAction);

        return LOAD_HOME;
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Validated UserDto user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute(FORM_ACTION, "save");
            return LOAD_HOME;
        }
        userRepository.save(user.parse());

        return REDIRECT_HOME;
    }

    @PostMapping("/update/id/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Validated UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            model.addAttribute(FORM_ACTION, "/update/id/" + id);
            return LOAD_HOME;
        }
        var user = userRepository.findById(id).orElseThrow();
        userRepository.save(userDto.update(user));

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/id/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        var user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);

        return REDIRECT_HOME;
    }

}
