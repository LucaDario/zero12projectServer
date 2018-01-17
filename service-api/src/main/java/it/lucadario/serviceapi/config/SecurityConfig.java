package it.lucadario.serviceapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by lucadario on 13/01/18.
 */
@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()

                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/common/**").hasAnyRole("USER","ADMIN")
                .and()
                .httpBasic();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Luca").password("Luca")
                .roles("USER", "Luca")
                .and()
                .withUser("Jacopo").password("Jacopo")
                .roles("USER", "Jacopo")
                .and()
                .withUser("ADMIN").password("ADMIN")
                .roles("ADMIN");
    }



}
