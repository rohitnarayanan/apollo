package apollo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import accelerate.spring.web.security.UserSession;

/**
 * PUT DESCRIPTION HERE
 * 
 * @version 1.0 Initial Version
 * @author Rohit Narayanan
 * @since October 31, 2018
 */
@Component
public class ApolloUserService implements UserDetailsService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	/**
	 * @param aUsername
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String aUsername) throws UsernameNotFoundException {
		UserSession userSession = new UserSession();
		userSession.setUsername(aUsername);
		userSession.setPassword(aUsername);
		userSession.getAuthorities().add(new SimpleGrantedAuthority("ROLE_" + aUsername.toUpperCase()));
		return userSession;
	}
}
