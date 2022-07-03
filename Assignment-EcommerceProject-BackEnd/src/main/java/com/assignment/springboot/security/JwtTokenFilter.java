package com.assignment.springboot.security;

import com.assignment.springboot.data.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token=getAccessToken(request);
		if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh")
				|| request.getServletPath().equals("/swagger-ui.html") || request.getServletPath().equals("/api-docs")) {
			filterChain.doFilter(request, response);
		} else if (!hasAuthorizationBearer(request)) {
			filterChain.doFilter(request, response);
		} else if (!jwtTokenUtil.validateAccessToken(token)) {
			filterChain.doFilter(request,response);
		}
		setAuthenticationContext(token,request);
		
	}
	
	private void setAuthenticationContext(String token, HttpServletRequest request) {
		UserDetails userDetails=getUserDetails(token);
		UsernamePasswordAuthenticationToken authenticationToken=
				new UsernamePasswordAuthenticationToken(userDetails,null,null);
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
	
	private UserDetails getUserDetails(String token) {
		Account account=new Account();
		String[] jwtSubject=jwtTokenUtil.setSubject(token).split(",");
		account.setId(Integer.parseInt(jwtSubject[0]));
		account.setGmail(jwtSubject[1]);
		return account;
	}
	
	private String getAccessToken(HttpServletRequest request) {
		String header=request.getHeader("Authorization");
		return header.split(" ")[1].trim();
	}
	
	private boolean hasAuthorizationBearer(HttpServletRequest request){
		String header=request.getHeader("Authorization");
		if(ObjectUtils.isEmpty(header)||!header.startsWith("Bearer")){
			return false;
		}
		return true;
	}
}
