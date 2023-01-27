package com.example.orderservice.interceptor.annotation;


import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VersionCheck {
    @NotNull String versionKey();
    @NotNull String versionValue();
}
