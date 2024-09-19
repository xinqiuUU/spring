package com.yc.project2.org.springframework.annotation;

import javax.inject.Scope;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RUNTIME)
public @interface YcScope {
    String value() default "SINGLETON";
}
