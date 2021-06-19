package com.github.paintxd.mercadofechado.controller;

import com.github.paintxd.mercadofechado.model.User;
import com.github.paintxd.mercadofechado.service.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "authB")
@SessionScoped
public class LoginController implements Serializable {
    private User user = new User();
    private String email;
    private String password;

    private boolean auth = false;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginController() {
        User user =	(User) SessionContext.getInstance().getAttribute("authUser");
        if (user != null) {
            auth = true;
        }
    }

    public String login() {
        FacesMessage message = null;
        try {
            var authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(this.email, this.password));
            auth = true;
            SessionContext.getInstance().setAuthUser((User) authentication.getPrincipal());
            password = null;
            return "/index.xhtml?faces-redirect=true";
        } catch (BadCredentialsException e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bad credentials", null);
        } catch (AuthenticationException e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to authenticate", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (message != null) {
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage("loginFormMessages", message);
        }

        return "";
    }

    public String logoutAction() {
        user = new User();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
