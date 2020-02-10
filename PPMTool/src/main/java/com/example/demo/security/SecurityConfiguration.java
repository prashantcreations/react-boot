package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true,prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthendicationEntryPoint unauthorizedHandler;
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() {return new JwtAuthenticationFilter();}
	
	
	
	// authendicate the login details
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);
	}
	

	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}





	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.headers().frameOptions().sameOrigin()
		.and()
		.authorizeRequests()
		.antMatchers(
                "/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
				).permitAll()
				.antMatchers(com.example.demo.security.SecurityContant.SIGN_UP_URL).permitAll()
				.antMatchers(com.example.demo.security.SecurityContant.H2_CONSOLE_URL).permitAll()
				.anyRequest().authenticated();
		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		}
}
