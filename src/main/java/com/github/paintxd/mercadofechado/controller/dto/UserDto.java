package com.github.paintxd.mercadofechado.controller.dto;

import com.github.paintxd.mercadofechado.model.User;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserDto {
    @NotEmpty(message = "Obrigatorio")
    private String firstName;
    @NotEmpty(message = "Obrigatorio")
    private String lastName;
    @Size(min = 6, message = "Senha deve ter no minimo 6 digitos")
    private String password;
    @NotEmpty(message = "Obrigatorio")
    private String document;
    @NotEmpty(message = "Obrigatorio")
    private String address;
    @Email(message = "Deve ser um email valido")
    private String email;
    @NotEmpty(message = "Obrigatorio")
    private String birthDate;

    public  UserDto() { }
    public UserDto(User user) {
        this.firstName = user.getFullName().substring(0, user.getFullName().indexOf(" "));
        this.lastName = user.getFullName().substring(user.getFullName().indexOf(" ") + 1);
        this.password = user.getPassword();
        this.document = user.getDocument();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.birthDate = user.getBirthDate().toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public User parse() {
        return new User(getFullName(), document, address, email, LocalDate.parse(birthDate), password);
    }

    public User update(User user) {
        user.setFullName(getFullName());
        user.setDocument(document);
        user.setAddress(address);
        user.setEmail(email);
        user.setBirthDate(LocalDate.parse(birthDate));
        user.setPassword(password);

        return user;
    }
}
