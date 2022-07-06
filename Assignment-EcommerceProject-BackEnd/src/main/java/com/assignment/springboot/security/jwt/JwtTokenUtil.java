package com.assignment.springboot.security.jwt;

import com.assignment.springboot.service.impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
	private final String SECRET_KEY;
	
	public JwtTokenUtil(@Value("${app.jwt.secret}") String SECRET_KEY) {
		this.SECRET_KEY = SECRET_KEY;
	}
	
	public String generateAccessToken(Authentication authentication) {
		UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(String.format(user.getGmail()))
				//.claim("role",account.getRole())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			LOGGER.error("JWT expired ", e);
		} catch (IllegalArgumentException ex) {
			LOGGER.error("Token is null, empty or only whitespace ", ex);
		} catch (MalformedJwtException ex) {
			LOGGER.error("JWT is invalid", ex);
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("JWT is not supported", ex);
		} catch (SignatureException ex) {
			LOGGER.error("Signature validation failed");
		}
		return false;
	}
	
	public String getEmailFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}
	
}
