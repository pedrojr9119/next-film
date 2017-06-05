package com.nextfilme.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
				"/", 
				"/gentelella-master/**", 
				"/js/**", 
				"/usuarios/**", 
				"/videos/**", 
				"/categorias/**", 
				"/sessao/**", 
				"/generos/**", 
				"/atores/**"
				).permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll().and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		  .usersByUsernameQuery("SELECT login, senha, habilitado FROM usuario WHERE login=?")
		  .authoritiesByUsernameQuery("SELECT usuario, papel FROM papel_usuario WHERE usuario=?");
	}
}