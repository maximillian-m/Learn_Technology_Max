package com.maximillian.logging.aspect_oriented_programming.aspects;

import com.maximillian.logging.annotation.Log;
import io.netty.util.internal.SocketUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.checkerframework.checker.units.qual.N;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

@Aspect
@Component
public class LoggingSecondAspect {

    private static final String DASHES = "=====================================================";
    private static final String NEWLINE = "\n";
    private static final Logger log = LogManager.getLogger(LoggingSecondAspect.class);

    @Pointcut("execution (* com.maximillian.logging.aspect_oriented_programming.services.*.*(..)) && @annotation(codeLog)")
    public void logPointCut(Log codeLog){}


    @Before("logPointCut(codeLog)")
    public void logMethods(JoinPoint joinPoint, Log codeLog){
        //get the simple class name instead of the package path together with the class
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        StringBuilder sb = new StringBuilder();
        sb.append(DASHES).append(NEWLINE);
        sb.append("ClassName:: ").append(className);
        sb.append(NEWLINE);
        sb.append("MethodName:: ").append(methodName);
        sb.append(NEWLINE);
        if(codeLog.printParamsValues()){
            Stream<Object> streamOfObject = Arrays.stream(joinPoint.getArgs());
            streamOfObject.forEach( o -> sb.append("value:: ").append(o.toString()).append(NEWLINE));
        }
        sb.append(DASHES);
        String logString = sb.toString();
        log.info(logString);
    }

    @Around("logPointCut(codeLog)")
    public Object logAround(ProceedingJoinPoint joinPoint, Log codeLog) throws Throwable {
        //This is the another method signature.........
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //Another way to get the method before retrieving the method name..........
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        System.out.println("Print method name here");
        Object result = joinPoint.proceed();
        if (result != null) {
            try {
                //invoke specific method on the return object
                Method toStringMethod = result.getClass().getMethod(codeLog.callMethodWithParamsToString());
                Object toStringResult = toStringMethod.invoke(result);
                System.out.println("Method " + methodName + " returned: " + toStringResult);
            } catch (Throwable ex) {
                System.out.println("Method " + codeLog.callMethodWithParamsToString() + " not found on return type..");
            }
        }
        return result;
    }
}
/*
*
execution(
    *                                     // Any return type
    com.apress.messaging.service.*Service.* // Any class ending with 'Service' in the specified package, any method
            (
            ..,                                 // Any number of parameters before
    @com.apress.messaging.annotation.ToUpper (*), // A parameter annotated with @ToUpper
        ..                                  // Any number of parameters after
)
)
 */
