package com.theartisanbase.crm;

import com.theartisanbase.crm.domain.User;
import com.theartisanbase.crm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableWebMvc
@SpringBootApplication
@EnableSwagger2
public class CrmApplication implements CommandLineRunner {
//	@Autowired
//	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		ApiInfo apiInfo= new ApiInfoBuilder().title("The ArtisanBase Customer Relationship Management API")
				.description("API Documentation for internal users")
				.contact(new Contact("Vanessa Tran", "https://vanntechs.com", "vanessa@vanntechs.com"))
				.licenseUrl("vanessa@vanntechs.com").version("1.0").build();

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.any())
				.build();
	}


	/**
	 * Initialize the database at application startup
	 * @param strings
	 * @throws Exception
	 */
	@Override
	public void run(String... strings) throws Exception {
	//	userRepository.save(new User("Vanessa", "Tran", "vanessa@vanntechs.com", "1234567"));
	}
}
