package com.github.paintxd.mercadofechado.service;

import com.github.paintxd.mercadofechado.model.User;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionContext {
    private static SessionContext instance;

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }

        return instance;
    }

    private SessionContext() {

    }

    private ExternalContext currentExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    public User getAuthUser() {
        return (User) getAttribute("authUser");
    }

    public void setAuthUser(User user) {
        setAttribute("authUser", user);
    }

    public void terminateSession() {
        currentExternalContext().invalidateSession();
    }

    public Object getAttribute(String key) {
        return currentExternalContext().getSessionMap().get(key);
    }

    public void setAttribute(String key, Object value) {
        currentExternalContext().getSessionMap().put(key, value);
    }

}
