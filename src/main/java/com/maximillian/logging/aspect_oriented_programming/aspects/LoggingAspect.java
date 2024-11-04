package com.maximillian.logging.aspect_oriented_programming.aspects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LogManager.getLogger(LoggingAspect.class);
    @Pointcut("execution(* com.maximillian.logging.aspect_oriented_programming.services.*.*(..))")
    public void logPointCut(){}

    @Pointcut("execution(* com.maximillian.logging.aspect_oriented_programming..*.*(..))")
    public void logPointCutOutWardPackage(){}

    @Before("logPointCut()")
    public void doLoggingAspect(JoinPoint jointPoint){
        String methodName = jointPoint.getSignature().getName();
        Object [] args = jointPoint.getArgs();
        String methodArguments = args.length > 0 ? argumentString(jointPoint.getArgs()) : "No argument";
        log.info("============= entering method, methodName:: {} and parameters {} =================", methodName, methodArguments);
    }
//Be mindful of the double ..* that is within the execution; the double dots denotes that it is within the parent folder.
    @AfterThrowing(
            pointcut = "logPointCutOutWardPackage()",
            throwing = "exception"
    )
    public void doLoggingException(JoinPoint jointPoint, Throwable exception){

        String methodName = jointPoint.getSignature().getName();
        Object [] args = jointPoint.getArgs();
        String methodArguments = args.length > 0 ? argumentString(jointPoint.getArgs()) : "No argument";

        log.error("Exception in method: {} with arguments: {} with exception message: {}",
                methodName, methodArguments, exception.getMessage(), exception);
    }

    @AfterReturning(
            pointcut = "logPointCutOutWardPackage()",
            returning = "value"
    )
    public void doLoggingThenReturning(JoinPoint joinPoint, Object value){
        String methodName = joinPoint.getSignature().getName();
        Object [] args = joinPoint.getArgs();
        String methodArguments = args.length > 0 ? argumentString(joinPoint.getArgs()) : "No argument";

        log.info("============= entering method, methodName:: {} and parameters {}; and returning value:: {} =================",
                methodName, methodArguments, value);
    }

    private String argumentString(Object... args){
        StringBuilder sb = new StringBuilder();
        Arrays.stream(args).forEach(c -> {
            sb.append(c);
            sb.append(" ");
        });
        return sb.toString();
    }
}
