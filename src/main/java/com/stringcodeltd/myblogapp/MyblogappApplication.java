package com.stringcodeltd.myblogapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "Stringcode Blog",
				description = "This is Blog API official documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Stringcode Limited",
						email="info@stringcodeltd.com",
						url="stringcodeltd.com"
				),
				license = @License(
						name = "MIT",
						url="strincodeltd.com/lincense"
				)

		)
)
public class MyblogappApplication {

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(MyblogappApplication.class, args);
	}

}
