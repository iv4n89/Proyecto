package com.oreja.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{ //webclient 
    
//    @Autowired
//    private UserDetailsService userDetailsService;
//    
//    @Autowired
//    private WebClient webClient;
//    
//    @Autowired
//    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
//	build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication()
			.withUser("admin")
				.password("$2a$10$KTHUw4Z0f1txVLX2Ui3/FuZa51M.Hp8OnxhlnjZSPwYJ.GNDjJDF2") //Password 123
				.roles("ADMIN")
		.and()
			.passwordEncoder(passwordEncoder())
		//.and()
			//.userDetailsService(userDetailsService)
		;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable()
		.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/material/add","/material/update")
				.hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/material/delete")
				.hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/pruebas/deletePrueba")
				.hasRole("ADMIN")
		.and()
			.formLogin()
			.loginPage("/login")
		.and()
			.exceptionHandling()
			.accessDeniedPage("/error/403")
		.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/material");
		;
    }

}
