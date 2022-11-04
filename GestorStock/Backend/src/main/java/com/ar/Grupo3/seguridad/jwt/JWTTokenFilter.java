package com.ar.Grupo3.seguridad.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTTokenFilter extends OncePerRequestFilter {

	private final static Logger log = LoggerFactory.getLogger(JWTTokenFilter.class);

	@Autowired
	JWTProvider jwtProvider;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getToken(request);
			if (token != null && jwtProvider.validateToken(token)) {
				String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
				UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);

			}

		} catch (Exception e) {
			log.error("Fallo el metodo 'doFilterInternal' ");
		}
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Autorization");
		if (header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer", "");
		}
		return null;
	}
}
