package tinhnv.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tinhnv.security.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = false,
		prePostEnabled = true,
		jsr250Enabled = false
		)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService service;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// to use https protocol on heroku app
		http.requiresChannel()
	      .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
	      .requiresSecure();
		
		http
//		.csrf().disable()
		.authorizeRequests()
         	.antMatchers("/register", "/login").permitAll()
				.antMatchers("/account-manage/**", "/nation-manage/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and().formLogin()
//				.loginPage("/login")
				.defaultSuccessUrl("/welcome")
				.failureUrl("/login_error")
				.permitAll()
				.and().logout()
				.logoutSuccessUrl("/bye")
//				.deleteCookies("JSESSIONID")
				.permitAll();
	}
}
