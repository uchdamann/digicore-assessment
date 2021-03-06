package com.digicore.devops.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.collect.Lists;

@SuppressWarnings("deprecation")
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.digicore")).paths(PathSelectors.any()).build()
				.apiInfo(metaData())
				.securitySchemes(apiKey())
				.securityContexts(Arrays.asList(swaggerSecurityContext()));
	}
	
	private SecurityContext swaggerSecurityContext() {
		return SecurityContext.builder().securityReferences(swaggerSecurityReferences()).build();
	}
	
	private List<SecurityReference> swaggerSecurityReferences(){
		AuthorizationScope auth = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authScopes = new AuthorizationScope[1];
		authScopes[0] = auth;
		
		return Lists.newArrayList(new SecurityReference("Authorization", authScopes));
	}
	
	private List<ApiKey> apiKey(){
		List<ApiKey> apiKeys = new ArrayList<>();
		apiKeys.add(new ApiKey("Authorization", "Authorization", "Header"));
		
		return apiKeys;
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Digicore API", "Swagger API documentation", "1.0", "Terms of service",
				"Integration@Digicore", "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");

		return apiInfo;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
		registry.addRedirectViewController("/api/swagger-resources/configuration/ui",
				"/swagger-resources/configuration/ui");
		registry.addRedirectViewController("/api/swagger-resources/configuration/security",
				"/swagger-resources/configuration/security");
		registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/api/swagger-ui.html**")
				.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}