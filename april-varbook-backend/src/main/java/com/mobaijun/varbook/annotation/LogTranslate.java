package com.mobaijun.varbook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: []
 * Author: [mobaijun]
 * Date: [2025/6/27 14:55]
 * IntelliJ IDEA Version: [IntelliJ IDEA 2023.1.4]
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogTranslate {
}
