

package com.jeeit.upms.swagger.annotation;

import com.jeeit.upms.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author  傅枫
 * @date 2018/7/21
 * 开启pigx swagger
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableJeeitSwagger2 {
}
