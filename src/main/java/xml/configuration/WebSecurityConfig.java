package xml.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String LOGOUT_URL = "/logout";
    private static final String SESSION_COOKIE = "JSESSIONID";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        http.httpBasic().disable();
        http.logout().logoutUrl(LOGOUT_URL).deleteCookies(SESSION_COOKIE);
        http.rememberMe();
        http.csrf().disable();
    }
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home").permitAll().antMatchers("/login/activateUser").permitAll()
				.antMatchers("/login/loginUser").permitAll().and().formLogin().loginPage("/login").permitAll().and()
				.logout().permitAll().and().csrf().disable();

	}*/

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth .inMemoryAuthentication()
	 * .withUser("user").password("password").roles("USER"); }
	 */

}
