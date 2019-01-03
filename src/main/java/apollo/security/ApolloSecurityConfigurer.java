package apollo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import accelerate.spring.config.AuthenticationConfigProps;
import accelerate.spring.web.config.WebConfigProps;
import accelerate.spring.web.security.SecurityConfigurer;
import accelerate.spring.web.security.SecurityUtils;

/**
 * PUT DESCRIPTION HERE
 * 
 * @version 1.0 Initial Version
 * @author Rohit Narayanan
 * @since October 30, 2018
 */
@Component
public class ApolloSecurityConfigurer implements SecurityConfigurer {
	/**
	 * {@link AuthenticationConfigProps} instance
	 */
	@Autowired
	protected AuthenticationConfigProps authenticationConfigProps = null;

	/**
	 * {@link WebConfigProps} instance
	 */
	@Autowired
	protected WebConfigProps webConfigProps = null;

	/**
	 * {@link ApolloUserService} instance
	 */
	@Autowired
	protected ApolloUserService apolloUserService = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see accelerate.spring.web.security.SecurityConfigurer#configure(org.
	 * springframework. security.config.annotation.web.builders.HttpSecurity)
	 */
	/**
	 * @param aHttpSecurity
	 * @throws Exception
	 */
	@Override
	public void configure(HttpSecurity aHttpSecurity) throws Exception {
		// login configuration
		aHttpSecurity.csrf().disable().formLogin()// .loginPage("/login").loginProcessingUrl("/login")
				.defaultSuccessUrl(this.webConfigProps.getHomePath(), false)
				.failureHandler(authenticationFailureHandler()).permitAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see accelerate.spring.web.security.SecurityConfigurer#configure(org.
	 * springframework. security.config.annotation.authentication.builders.
	 * AuthenticationManagerBuilder)
	 */
	/**
	 * @param aAuthenticationManagerBuilder
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void configure(AuthenticationManagerBuilder aAuthenticationManagerBuilder) throws Exception {
		aAuthenticationManagerBuilder.eraseCredentials(true).userDetailsService(this.apolloUserService)
				.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());

		// inMemoryAuthentication().withUser("admin")
//				.password("admin").roles("ADMIN").and().withUser("user").password("user").roles("USER").and()
//				
	}

	/**
	 * @return
	 */
	private final AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest aRequest, HttpServletResponse aResponse,
					AuthenticationException aAuthException) throws IOException, ServletException {
				setDefaultFailureUrl(SecurityUtils.getAuthErrorURL(
						ApolloSecurityConfigurer.this.authenticationConfigProps.getLoginPath(), aAuthException));
				super.onAuthenticationFailure(aRequest, aResponse, aAuthException);
			}
		};
	}
}
