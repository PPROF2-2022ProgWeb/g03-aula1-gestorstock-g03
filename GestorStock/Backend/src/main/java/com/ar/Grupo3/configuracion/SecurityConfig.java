package com.ar.Grupo3.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

//	//Sin Postman
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//    	http.authorizeRequests()
//                .antMatchers("/gestor/**")
//                .hasRole("ADMIN")
//                .antMatchers("/index")
//                .hasAnyRole("USER", "ADMIN" , "SELLER")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .and()
//                .exceptionHandling().accessDeniedPage("/errores/403");
//	}

	// Para Probar en PostMan
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
	}

}