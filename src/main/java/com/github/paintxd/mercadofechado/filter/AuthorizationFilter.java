package com.github.paintxd.mercadofechado.filter;

import com.github.paintxd.mercadofechado.model.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        User user = null;
        HttpSession sess = ((HttpServletRequest) request).getSession(false);

        if (sess != null) {
            user = (User) sess.getAttribute("authUser");
        }

        HttpServletRequest rqt = (HttpServletRequest) request;

        if (user == null && (rqt.getRequestURI().indexOf("login.xhtml") <= 0)
                && (rqt.getRequestURI().indexOf("home.xhtml") <= 0)) {
            String contextPath = rqt.getContextPath();

            ((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}