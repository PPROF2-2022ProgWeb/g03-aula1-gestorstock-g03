package com.ar.Grupo3.seguridad.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ar.Grupo3.seguridad.servicio.UsuarioDetalles;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTProvider {

	private final static Logger log = LoggerFactory.getLogger(JWTProvider.class);

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken(Authentication autenticacion) {
		UsuarioDetalles usuarioDetalles = (UsuarioDetalles) autenticacion.getPrincipal();

		return Jwts.builder().setSubject(usuarioDetalles.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String miToken) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(miToken);
			return true;
		} catch (MalformedJwtException e) {
			log.error("El Token no se ha formado Correctamente");
		} catch (UnsupportedJwtException e) {
			log.error("Token no es compatible");
		} catch (ExpiredJwtException e) {
			log.error("El Token e cuestion ya ha expirado");
		} catch (IllegalArgumentException e) {
			log.error("El Token enviado se encuentra vacio");
		} catch (SignatureException e) {
			log.error("El token no posee una firma valida");
		}
		return false;
	}
}
