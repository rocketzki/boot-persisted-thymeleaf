package com.rocketzki.persistedthymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecuritySettings extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.authorities-query}")
    private String authQuery;

    @Autowired
    public SecuritySettings(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(authQuery)
                .dataSource(dataSource)
                .passwordEncoder(passEncrypt());
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/hall", "/game","/js/**","/").authenticated()
                .antMatchers("/register", "/h2/**").hasAnyAuthority("ADMIN")
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/game", true)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .logout();
    }



    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**",
                        "/images/**", "resources/uploads/**", "/uploads/**", "/resources/static/img/**");
    }


    @Bean
    public PasswordEncoder passEncrypt() {
        return new BCryptPasswordEncoder();
    }
}