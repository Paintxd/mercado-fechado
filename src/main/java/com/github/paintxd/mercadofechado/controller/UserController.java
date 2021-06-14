package com.github.paintxd.mercadofechado.controller;

import com.github.paintxd.mercadofechado.controller.dto.UserDto;
import com.github.paintxd.mercadofechado.model.User;
import com.github.paintxd.mercadofechado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "userB")
@SessionScoped
public class UserController implements Serializable {
    private Iterable<User> userList;
    private User user = new User();

    @Autowired
    private UserRepository userRepository;

    public UserController() {}

    public void getUserById(Long id) {
        this.user = userRepository.findById(id).orElse(new User());
    }

    public void saveUser() {
        userRepository.save(user);
        user = new User();
    }

    public void updateUser(Long id) {
        user = userRepository.findById(id).orElse(new User());
    }

    public void deleteUser(Long id) {
        var userDelete = userRepository.findById(id).orElseThrow();
        userRepository.delete(userDelete);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Iterable<User> getUserList() {
        return userRepository.findAll();
    }

    public void setUserList(Iterable<User> userList) {
        this.userList = userList;
    }
}
