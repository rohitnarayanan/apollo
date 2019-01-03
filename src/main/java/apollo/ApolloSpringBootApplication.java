package apollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import accelerate.spring.constants.ProfileConstants;

/**
 * Main class for this Boot Application
 * 
 * @version 1.0 Initial Version
 * @author Rohit Narayanan
 * @since December 11, 2017
 */
@SpringBootApplication(scanBasePackages = { "accelerate", "apollo" }, exclude = { DataSourceAutoConfiguration.class })
public class ApolloSpringBootApplication {
	/**
	 * Main method to start web context as spring boot application
	 *
	 * @param aArgs
	 */
	public static void main(String[] aArgs) {
		try {
			runSpringApp(aArgs);
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 */
	public static final void quickTest() throws Exception {
		//
	}

	/**
	 * @param aArgs
	 */
	public static final void runSpringApp(String[] aArgs) {
		SpringApplication application = new SpringApplication(ApolloSpringBootApplication.class);
		application.setAdditionalProfiles(ProfileConstants.PROFILE_LOGGING, ProfileConstants.PROFILE_NO_SECURITY);

		application.run(aArgs);
	}
}
