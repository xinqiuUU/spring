package com.yc.project2.org.springframework.annotation;

import javax.annotation.Resource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD,
        ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface YcResource {

    String value() default ""; //用于指定要装配的Bean的名称
}
