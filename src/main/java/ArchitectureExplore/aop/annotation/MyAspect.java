package ArchitectureExplore.aop.annotation;

import java.lang.annotation.*;

/**
 * @Description: 切面注解
 * @author: lzs
 * @date:: 2019-10-02 17:27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAspect {
    Class<? extends Annotation> value();
}
