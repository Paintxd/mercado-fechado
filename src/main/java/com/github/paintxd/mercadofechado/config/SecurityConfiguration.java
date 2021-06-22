package com.github.paintxd.mercadofechado.config;

import com.github.paintxd.mercadofechado.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final AuthenticationService authenticationService;

    public SecurityConfiguration(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//			.antMatchers("/resources/**").permitAll()
                .antMatchers("login.xhtml").permitAll()
                .antMatchers("register.xhtml").permitAll()
                .antMatchers("/h2-console").anonymous()
                .antMatchers("index.xhtml").authenticated()
                .antMatchers("/admin.xhtml").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login.xhtml").successForwardUrl("/index.xhtml")
                    .failureUrl("/login.xhtml?error=true").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login.xhtml")
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/*.xhtml", "/javax.faces.resource/**", "/h2-console");
    }

}
