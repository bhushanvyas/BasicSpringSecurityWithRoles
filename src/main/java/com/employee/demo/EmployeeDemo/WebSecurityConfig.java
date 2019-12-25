package com.employee.demo.EmployeeDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


 /**
 * @author bhushanvyas
 *
 */
 

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private MyAccessDeniedHandler deniedHandeller;
	
	@Autowired
	private LoginAuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}


	
	  @Autowired public void configure(AuthenticationManagerBuilder
	  authenticationMgr) throws Exception {
	  authenticationMgr.inMemoryAuthentication()
	  .withUser("bhushanvyas").password("{noop}password").authorities(
	  "ROLE_USER") .and()
	  .withUser("admin").password("{noop}password").authorities("ROLE_USER",
	  "ROLE_ADMIN");
	  
	  }
	 
	/**
	 * Through this method we configure url access and redirection pattern after authenticate user
	 */
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/homePage").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/user/**").access("hasRole('ROLE_USER')")
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.and()
			.formLogin().loginPage("/loginPage").permitAll()
			.successHandler(authenticationSuccessHandler).permitAll()
			.failureUrl("/loginPage?error")
			.usernameParameter("username").passwordParameter("password")				
		.and()
			.logout().logoutSuccessUrl("/loginPage?logout")
		.and().exceptionHandling().accessDeniedHandler(deniedHandeller);
	
	}
	
	/**
	 * for setup in memory users
	 */

	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() { UserDetails
	 * user = User.withDefaultPasswordEncoder() .username("user")
	 * .password("password") .roles("USER") .build(); UserDetails admin =
	 * User.withDefaultPasswordEncoder() .username("admin") .password("password")
	 * .roles("USER","ADMIN") .build(); Collection<UserDetails> users = new
	 * ArrayList<UserDetails>(); users.add(user); users.add(admin);
	 * 
	 * 
	 * return new InMemoryUserDetailsManager(users); }
	 */
	
}	
