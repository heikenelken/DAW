package com.wallacomic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider userRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//http.antMatcher("/api/**");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/home").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/comic/{id}").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/comics").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/usuario/{id}").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/guardarComic").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/guardarUsuario").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/loginerror").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/logout").permitAll();
        //depercateds
        /*http.authorizeRequests().antMatchers("/api/home_autenticado").permitAll();
        http.authorizeRequests().antMatchers("/api/comic_autenticado").permitAll();
        http.authorizeRequests().antMatchers("/api/usuario_autenticado").permitAll();
        http.authorizeRequests().antMatchers("/api/miUsuario").permitAll();*/
		
		// URLs that need authentication to access to it
		/*http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/books/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/books/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN");*/		
		
		// Other URLs can be accessed without authentication
		//http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement with ng2)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(userRepoAuthProvider);
	}
}