package ca.sheridancollege.belimal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration 
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable()	;

		http.authorizeHttpRequests((authz) -> authz
				.requestMatchers("/view").hasAnyRole("GUEST", "VENDER" )
				.requestMatchers("/add").hasRole("VENDER")
				.requestMatchers("/edit/{id}").hasRole("VENDER")
				.requestMatchers("/delete/{id}").hasRole("VENDER")
				.requestMatchers("/").permitAll()
				.requestMatchers("/register").permitAll()
				.requestMatchers("/style.css").permitAll()
				.requestMatchers("/images/**").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
				)
		.formLogin((formLogin) -> formLogin
				.loginPage("/login")
				.failureUrl("/login?failed")
				.permitAll()
				)
		.logout((logout) -> logout
				.deleteCookies("remove")
				.invalidateHttpSession(true) //Notes it says false
				.logoutUrl("/logout")
				.logoutSuccessUrl("/?logout")
				.permitAll()
				)
		.exceptionHandling((exception) -> exception
				.accessDeniedHandler(accessDenied))
		;
		return http.build();
	}
	private LoginAccessDeniedHandler accessDenied;
	
private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http,
	PasswordEncoder passwordEncoder) throws Exception {
	AuthenticationManagerBuilder authenticationManagerBuilder = http
	.getSharedObject(AuthenticationManagerBuilder.class);
	authenticationManagerBuilder.userDetailsService(userDetailsService)
	.passwordEncoder(passwordEncoder);
	return authenticationManagerBuilder.build();
	}
}
