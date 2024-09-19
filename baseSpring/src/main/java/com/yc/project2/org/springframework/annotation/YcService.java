package com.yc.project2.org.springframework.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@YcComponent
public @interface YcService {

    String value() default "";
}
