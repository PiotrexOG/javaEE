package com.example.skins.Logger;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.*;

// Custom interceptor binding annotation
@InterceptorBinding
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {
}