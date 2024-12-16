package com.example.skins.Logger;

import com.example.skins.skin.entity.Skin;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.security.enterprise.SecurityContext;

import java.util.UUID;
import java.util.logging.Logger;

@Loggable
@Interceptor
@Priority(10)
public class LoggerInterceptor {
    private static final Logger logger = Logger.getLogger(LoggerInterceptor.class.getName());
    private final SecurityContext securityContext;

    @Inject
    LoggerInterceptor(SecurityContext securityContext){
        this.securityContext = securityContext;
    }
    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        String methodName = context.getMethod().getName();
        Object[] parameters = context.getParameters();
        String caller = securityContext.getCallerPrincipal().getName();
        if(parameters[0] instanceof UUID) {
            UUID id = (UUID) parameters[0];
            System.out.println(String.format("User: %s called %s on : %s", caller, methodName, id.toString()));
            logger.info(String.format("User: %s called %s on : %s", caller, methodName, id.toString()));
        }
        else if (parameters[0] instanceof Skin){
            UUID id = ((Skin) parameters[0]).getId();
            logger.info(String.format("User: %s called %s on : %s", caller, methodName, id.toString()));
        }
        else{
            logger.info(String.format("User: %s called %s on : %s", caller, methodName, parameters.toString()));
        }
//        logger.info(String.format("Method: %s called with parameters: %s", methodName, parameters));
        return context.proceed();
    }
}
