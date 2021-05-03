package com.springtrader.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springtrader.service.MyUserDetailsService;
import com.springtrader.util.AuthenticationRequest;
import com.springtrader.util.AuthenticationResponse;
import com.springtrader.util.JwtUtil;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	// Working JWT using session storage 
//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//		try { 
//			authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//				);
//		} catch (BadCredentialsException e) {
//			throw new Exception("Incorrect username or password", e);
//		} 
//		
//		final UserDetails userDetails = userDetailsService
//				.loadUserByUsername(authenticationRequest.getUsername());
//		final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//	    return ResponseEntity.ok(new AuthenticationResponse(jwt));
//	}
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws Exception {
		try {
			authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		Cookie jwtTokenCookie = new Cookie("user-id", jwt);
		jwtTokenCookie.setMaxAge(86400); // 1 day 
		//jwtTokenCookie.setSecure(true); // https
		jwtTokenCookie.setHttpOnly(true);
		//jwtTokenCookie.setPath("/authenticate/");
		//jwtTokenCookie.setDomain("http://localhost:8080");
		
		response.addCookie(jwtTokenCookie);
		return "Response with cookie using HTTPServletResponse";
		}
}
