package com.adidas.service.product.swagger;

import com.adidas.service.product.entity.Product;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger REST API configuration.
 *
 * @author Pavel Savinov
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket api() {

		TypeResolver typeResolver = new TypeResolver();

		return new Docket(DocumentationType.SWAGGER_2).select()
			.apis(RequestHandlerSelectors
				.basePackage("com.adidas.service.product.controller"))
			.paths(PathSelectors.regex("/.*"))
			.build().apiInfo(apiEndPointsInfo()).alternateTypeRules();
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Product Service REST API")
			.description("Product Service REST API endpoint")
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

