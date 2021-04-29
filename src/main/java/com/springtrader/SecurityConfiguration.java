package com.springtrader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springtrader.service.MyUserDetailsService;
import com.springtrader.util.JwtRequestFilter;
import com.springtrader.util.UserSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService muds;
	
	@Autowired 
	private JwtRequestFilter jwtRequestFilter;
	
	@SuppressWarnings("unused")
	@Autowired
	private UserSecurity userSecurity;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(muds);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/authenticate").permitAll()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/users/{username}/**").access("@userSecurity.hasUserAuthority(authentication, #username)")
			.antMatchers("/stock/**").permitAll()
			.antMatchers("/listheaders/").permitAll()
			.antMatchers("/search/**").permitAll()
			.antMatchers("./js/**").permitAll()
			.antMatchers("./css/**").permitAll()
			.antMatchers("./vendor/**").permitAll()
			.antMatchers("./includes/**").permitAll()
			.antMatchers("/users/**").permitAll()
			.antMatchers("./index.html").permitAll()
			.antMatchers("./").permitAll()
			.antMatchers("./resources/**").permitAll()
	        .and().sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	

	
}
