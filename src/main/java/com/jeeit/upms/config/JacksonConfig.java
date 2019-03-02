

package com.jeeit.upms.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * JacksonConfig
 *
 * @author:  傅枫
 * @date: 2018/7/28  12:29
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfig {
	/**
	 * 针对JDK 1.8 特殊处理
	 * .registerModule(new ParameterNamesModule())
	 * .registerModule(new Jdk8Module())
	 * .registerModule(new JavaTimeModule()); // new module, NOT JSR310Module
	 *
	 * @return
	 */
	@Bean
	public ObjectMapper getObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.findAndRegisterModules();
		// 忽略json字符串中不识别的属性
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 忽略无法转换的对象
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return objectMapper;
	}
}
