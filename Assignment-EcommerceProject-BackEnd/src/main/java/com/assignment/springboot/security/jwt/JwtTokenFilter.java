package com.assignment.springboot.security.jwt;

import com.assignment.springboot.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	private final JwtTokenUtil jwtTokenUtil;
	private final UserDetailsServiceImpl userDetailsService;
	
	public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, UserDetailsServiceImpl userDetailsService) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//		if (request.getServletPath().equals("/api/auth/login") || request.getServletPath().equals("/api/token/refresh")
//				|| request.getServletPath().equals("/swagger-ui.html") || request.getServletPath().equals("/api-docs")) {
//			filterChain.doFilter(request, response);
//		} else if (!hasAuthorizationBearer(request) || !jwtTokenUtil.validateAccessToken(token)) {
//			filterChain.doFilter(request, response);
//		}
		try {
			String token = getTokenFromRequest(request);
			if (token != null && jwtTokenUtil.validateAccessToken(token)) {
				setAuthenticationContext(token, request);
			}
		} catch (Exception e) {
			logger.error("cannot set user authentication: {}", e);
		}
		filterChain.doFilter(request, response);
	}
	
	private void setAuthenticationContext(String token, HttpServletRequest request) {
		String email = jwtTokenUtil.getEmailFromJwtToken(token);
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private String getTokenFromRequest(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}
}
