package py.sgarrhh.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import py.sgarrhh.security.ImplementsUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		/*.antMatchers(HttpMethod.GET, "/listaCargos").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/registrarCargo").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/registrarCargo").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/listaCargos").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/listaFunciones").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/registrarFuncion").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/registrarFuncion").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/listaFunciones").hasRole("ADMIN")*/

		.anyRequest().authenticated()
		.and().formLogin().failureUrl("/login?error")
		//.and().formLogin().loginPage("/login")
		.and().formLogin().permitAll()
		//.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));	
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/j_spring_security_logout")).logoutSuccessUrl("/login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/style/**");
	}
}
