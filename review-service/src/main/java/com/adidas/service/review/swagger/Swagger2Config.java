package com.adidas.service.review.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 2 configuration, to provide API documentation.
 *
 * @author Pavel Savinov
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket api() {
		List<SecurityScheme> schemeList = new ArrayList<>();

		schemeList.add(new BasicAuth("basicAuth"));

		return new Docket(DocumentationType.SWAGGER_2).select()
			.apis(RequestHandlerSelectors
				.basePackage("com.adidas.service.review.controller"))
			.paths(PathSelectors.regex("/.*"))
			.build().apiInfo(apiEndPointsInfo()).securitySchemes(schemeList);
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Spring Boot REST API")
			.description("Review Service REST API")
			.contact(
				new Contact(
					"Pavel Savinov", "www.pavelsavinov.info",
					"me@pavelsavinov.info"))
			.license("Apache 2.0")
			.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
			.version("1.0.0")
			.build();
	}

}

