package com.ar.Grupo3.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ar.Grupo3.seguridad.jwt.JWTEntryPoint;
import com.ar.Grupo3.seguridad.jwt.JWTTokenFilter;
import com.ar.Grupo3.seguridad.servicio.UsuarioServiceDetalles;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =  true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UsuarioServiceDetalles usuarioServiceDetalles;

	@Autowired
	JWTEntryPoint jwtEntryPoint;
	
	@Bean
	public JWTTokenFilter jwtTokenFilter() {
		return new JWTTokenFilter();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioServiceDetalles).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}

	// Sin PostMan
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable()
//		.authorizeRequests()
//		.antMatchers("/auth/**").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
//		.and()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(jwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);
//	}
	
	// Para Probar en PostMan
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().permitAll();		
	}

}