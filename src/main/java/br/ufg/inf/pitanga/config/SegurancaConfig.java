package br.ufg.inf.pitanga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SegurancaConfig extends WebSecurityConfigurerAdapter {

    public static final String PARAMETRO_USERNAME = "email";
    public static final String PARAMETRO_PASSWORD = "senha";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/jquery/**", "/resources/**")
            .permitAll()
            .anyRequest().authenticated();
        http.formLogin()
            .usernameParameter(PARAMETRO_USERNAME)
            .passwordParameter(PARAMETRO_PASSWORD)
            .loginPage("/login").permitAll().defaultSuccessUrl("/");
        http.logout().permitAll();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("pitanga")
            .password("pitanga")
            .roles("USER");
    }

}
