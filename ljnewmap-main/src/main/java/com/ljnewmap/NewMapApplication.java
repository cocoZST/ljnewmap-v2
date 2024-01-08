package com.ljnewmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@SpringBootApplication
public class NewMapApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NewMapApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NewMapApplication.class);
	}
}

@Configuration
@EnableAsync
class AsyncConfig implements AsyncConfigurer {

	@Bean("thread-pool")
	@Override
	public ThreadPoolTaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		//核心线程数
		taskExecutor.setCorePoolSize(10);
		//最大线程数
		taskExecutor.setMaxPoolSize(30);
		//队列大小
		taskExecutor.setQueueCapacity(2000);
		//初始化
		taskExecutor.initialize();
		return taskExecutor;
	}
}
