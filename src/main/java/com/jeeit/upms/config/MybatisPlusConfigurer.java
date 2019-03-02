

package com.jeeit.upms.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.jeeit.upms.datascope.DataScopeInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author  傅枫
 * @date 2017/10/29
 */
@Configuration
@MapperScan("com.jeeit.upms.mapper")
public class MybatisPlusConfigurer {
	/**
	 * 分页插件
	 *
	 * @return PaginationInterceptor
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	/**
	 * 数据权限插件
	 *
	 * @return DataScopeInterceptor
	 */
	@Bean
	public DataScopeInterceptor dataScopeInterceptor() {
		return new DataScopeInterceptor();
	}
}
