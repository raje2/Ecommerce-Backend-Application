package com.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerce.holder.TokenHolder;
import com.ecommerce.security.JwtAuthenticationEntryPoint;
import com.ecommerce.security.JwtAuthenticationFilter;
import com.ecommerce.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	    @Autowired
	    private CustomUserDetailsService customUserDetailsService;
	
	    @Autowired
	    private JwtAuthenticationEntryPoint point;
	    
	    @Autowired
	    private JwtAuthenticationFilter filter;
	    
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http.csrf(csrf -> csrf.disable())
	        .cors(cors-> cors.disable())
	        .authorizeHttpRequests(auth->auth.requestMatchers("/category/**").authenticated()
	        		.requestMatchers("/auth/login").permitAll()
	        		.requestMatchers("/cart/**").permitAll()
	        		.requestMatchers("/order-history/**").permitAll()
	        		.requestMatchers("/order/**").permitAll()
	        		.requestMatchers("/register/signup").permitAll()
	        		.requestMatchers("/product/**").authenticated()
	        		.anyRequest()
	        		.authenticated())
	                .exceptionHandling(ex-> ex.authenticationEntryPoint(point))
	                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        
	        ;
	                
	        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	        
	        return http.build();
	    }
	    
	    
	  //creating own user and password
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(customUserDetailsService);
		}
	    
	    
	    @Bean
		public PasswordEncoder passwordEncoder() {
	    	return NoOpPasswordEncoder.getInstance();
		}
		
		@Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
		@Bean
	    public TokenHolder tokenHolder() {
	        return new TokenHolder();
	    }


}
