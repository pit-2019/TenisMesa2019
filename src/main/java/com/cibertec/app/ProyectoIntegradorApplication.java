package com.cibertec.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

@MapperScan("com.cibertec.dao.mapper")
@ComponentScan("com.cibertec")
@EnableAutoConfiguration(exclude= {MultipartAutoConfiguration.class})
@SpringBootApplication
public class ProyectoIntegradorApplication {

	private static int MAX_UPLOAD_SIZE_MB = 5 * 1024 * 1024; //5MB
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoIntegradorApplication.class, args);
	}

	@Bean
	public CommonsMultipartResolver multiPartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setMaxUploadSize(MAX_UPLOAD_SIZE_MB * 2);
		cmr.setMaxUploadSizePerFile(MAX_UPLOAD_SIZE_MB);
		return cmr;
	}
	
	@Bean
	@Order(0)
	public MultipartFilter multiPartFilter() {
		MultipartFilter multiPartFilter = new MultipartFilter();
		multiPartFilter.setMultipartResolverBeanName("multiPartResolver");
		return multiPartFilter;
	}
}
