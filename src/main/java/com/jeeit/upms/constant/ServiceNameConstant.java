

package com.jeeit.upms.constant;

/**
 * @author  傅枫
 * @date 2018年06月22日16:41:01
 * 服务名称
 */
public interface ServiceNameConstant {

	/**
	 * 认证服务的SERVICEID（zuul 配置的对应）
	 */
	String AUTH_SERVICE = "jeeit-cloud-oauth";

	/**
	 * UMPS模块
	 */
	String UMPS_SERVICE = "jeeit-cloud-upms";

	/**
	 * 分布式事务协调服务
	 */
	String  TX_MANAGER = "tx-manager";
}
