package com.ra.busBooking.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class OpenAPIConfig {
	
	@Bean
	public OpenAPI myOpenAPI()
	{
		Server devServer=new Server();
		devServer.setUrl("https://localhost:8080/");
		devServer.setDescription("Server url is development environment");
		
		Server prodServer=new Server();
		prodServer.setUrl("https://www.test.com/");
		prodServer.setDescription("Server url is production environment");
		
		
		Contact contact=new Contact();
		contact.setName("Test Organization");
		contact.setEmail("test@gmail.com");
		contact.setUrl("https://test.com/");
		
		License license=new License().name("MIT License").url("https://opensource.org/license/mit");
		
		
		Info info=new Info()
				.title("User management API")
				.version("1.0")
				.contact(contact)
				.description("his API allows you to manage users in the system. Below are the instructions for setting up and running the project.")
				.license(license);
		
		return new OpenAPI().info(info).servers(List.of(devServer,prodServer));
		
		
	}

}
