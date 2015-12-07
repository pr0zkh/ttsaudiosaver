package org.ttsaudiosaver.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.ttsaudiosaver.web")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/view/";
	private static final String VIEW_RESLVER_SUFFIX = ".jsp";
	
	private static final String JS_RESOURCE_LOCATION = "/WEB-INF/js/";
	private static final String JS_RESOURCE_URI_PATTERN = "/js/**";
	private static final String CSS_RESOURCE_LOCATION = "/WEB-INF/css/";
	private static final String CSS_RESOURCE_URI_PATTERN = "/css/**";
	private static final String ASSETS_RESOURCE_LOCATION = "/WEB-INF/assets/";
	private static final String ASSETS_RESOURCE_URI_PATTERN = "/assets/**";
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(VIEW_RESOLVER_PREFIX);
		resolver.setSuffix(VIEW_RESLVER_SUFFIX);
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(JS_RESOURCE_URI_PATTERN).addResourceLocations(JS_RESOURCE_LOCATION);
		registry.addResourceHandler(CSS_RESOURCE_URI_PATTERN).addResourceLocations(CSS_RESOURCE_LOCATION);
		registry.addResourceHandler(ASSETS_RESOURCE_URI_PATTERN).addResourceLocations(ASSETS_RESOURCE_LOCATION);
	}
}