package com.platzi.market.web.security;

import com.platzi.market.domain.service.PlatziUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity//para indicar que esta clase estara encargada de la seguridad
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(platziUserDetailsService);
    }

    //vamos a decirle a la clase que no se necesita seguridad para entrar a /authenticate
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //esto quiere decir que todas las peticiones que terminen en authenticate las permita
        http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest().authenticated();//aca le decimos que las peticiones que no terminen en authenticate si necesitan autenticacion


    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
