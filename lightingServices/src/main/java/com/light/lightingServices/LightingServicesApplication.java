package com.light.lightingServices;

import com.light.lightingServices.services.WebSocketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class LightingServicesApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(LightingServicesApplication.class, args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//文件最大
		factory.setMaxFileSize("1024MB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("1024MB");
		return factory.createMultipartConfig();
	}
}
