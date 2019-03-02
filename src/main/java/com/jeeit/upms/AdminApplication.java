package com.jeeit.upms;

import com.jeeit.upms.feign.EnableOauthFeignClients;
import com.jeeit.upms.swagger.annotation.EnableJeeitSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author  傅枫
 * @date 2018年06月21日
 * 用户统一管理系统
 */
@EnableJeeitSwagger2
@SpringCloudApplication
@EnableOauthFeignClients
public class AdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
