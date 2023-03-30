package com.secureme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.secureme.service.ApplicationUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.authorizeHttpRequests()
			.requestMatchers("/authentication/**", "/h2-console/**").permitAll().anyRequest()
			.authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.httpBasic();
		return http.build();

	}

	@Bean
    UserDetailsService userDetailsService() {
       /* 
		UserDetails user1 = User.withUsername("user1")
            .password(passwordEncoder.encode("user1Pass"))
            .roles("USER")
            .build();
        UserDetails user2 = User.withUsername("user2")
            .password(passwordEncoder.encode("user2Pass"))
            .roles("USER")
            .build();
        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("adminPass"))
            .roles("ADMIN")
            .build();
         return new InMemoryUserDetailsManager(user1,user2,admin);
         */   
        
        return new ApplicationUserDetailsService();
    }
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider daoAuthenticationProvider= 
				 new DaoAuthenticationProvider();
		 daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		 return daoAuthenticationProvider;
	}
	
	

}
