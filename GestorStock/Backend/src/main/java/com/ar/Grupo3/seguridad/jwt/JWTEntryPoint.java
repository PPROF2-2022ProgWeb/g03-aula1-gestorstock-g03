package com.ar.Grupo3.seguridad.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JWTEntryPoint implements AuthenticationEntryPoint {

	private final static Logger log = LoggerFactory.getLogger(JWTEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		log.error("Fallo el metodo commence");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		
	}

}
