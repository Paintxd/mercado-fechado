package com.github.paintxd.mercadofechado;

import com.github.paintxd.mercadofechado.model.Product;
import com.github.paintxd.mercadofechado.model.User;
import com.github.paintxd.mercadofechado.repository.ProductRepository;
import com.github.paintxd.mercadofechado.repository.UserRepository;
import com.sun.faces.config.ConfigureListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.faces.webapp.FacesServlet;
import java.time.LocalDate;

@SpringBootApplication
public class MercadofechadoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MercadofechadoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MercadofechadoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean facesServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
        registration.setLoadOnStartup(1);
        registration.addUrlMappings("*.jr");
        return registration;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        };
    }

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository, ProductRepository productRepository) {
        return (String[] args) -> {
            var carlim = new User("Carlos Da Silva", "01930291392", "Rua Tchurusbangotchurusbago, Bairro Nao sei 64 D", "carlossilva@gmail.com", LocalDate.of(2001, 9, 12), "159357");
            var joaozim = new User("João Augusto", "94039118392", "Rua Das Palmeiras, Bairro Top 44 E", "joao.augusto@gmail.com", LocalDate.of(1994, 9, 17), "123456");
            userRepository.save(carlim);
            userRepository.save(joaozim);
            userRepository.findAll().forEach(System.out::println);

            var notibruik = new Product("Notebook top", "eletronico", "esse notebook é zika d+ se loko", 3890.40, 19L, 0L);
            var mousezim = new Product("Mouse RGBT", "periferico", "é top, confia", 173.50, 23L, 5L);
            productRepository.save(notibruik);
            productRepository.save(mousezim);
            productRepository.findAll().forEach(System.out::println);
        };
    }

}
