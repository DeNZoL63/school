package com.practice.school;

import com.practice.school.security.CustomSecurityContextHolderStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SchoolApplication {

	@Configuration
	@EnableGlobalMethodSecurity(securedEnabled = true)
	public static class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			FileInputStream fileInputStream;
			Properties properties = new Properties();

			try {
				//TODO wrong using path
				fileInputStream = new FileInputStream("src/main/resources/users.properties");
				properties.load(fileInputStream);

				int count = properties.size() / 3;

				for (int i = 1; i <= count; i++) {
					if (!properties.containsKey("user" + i + ".username")
							|| !properties.containsKey("user" + i + ".password")
							|| !properties.containsKey("user" + i + ".role")) {
						continue;
					}
					auth.inMemoryAuthentication()
							.withUser(properties.getProperty("user" + i + ".username"))
							.password(encoder.encode(properties.getProperty("user" + i + ".password")))
							.roles(properties.getProperty("user" + i + ".role"));
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return authenticationManager();
		}

		static {
			SecurityContextHolder.setStrategyName(CustomSecurityContextHolderStrategy.class.getName());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}
}
