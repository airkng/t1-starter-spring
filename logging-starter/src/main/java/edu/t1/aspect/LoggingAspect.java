package edu.t1.aspect;

import edu.t1.aspect.utils.LoggingProperties;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.t1.aspect.utils.LogMessages.*;

@Aspect
@RequiredArgsConstructor
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    private final LoggingProperties props;

    @Before("@annotation(edu.t1.aspect.annotation.Loggable)")
    public void loggingMethodBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        logIt(TRACE_METHOD_CALLING, signature.toString(), args);
    }

    @After("@annotation(edu.t1.aspect.annotation.Loggable)")
    public void loggingMethodAfter(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        logIt(TRACE_METHOD_CALLING_END, signature.toString(), args);
    }

    @Pointcut("within(*..controller.*)")
    public void controllerPackage() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    public void controllerAnnotations() {}

    @Around("controllerPackage() || controllerAnnotations()")
    public Object logRequestAndResponse(ProceedingJoinPoint jp) throws Throwable {
        logIt(TRACE_METHOD_CALLING, jp.getSignature(), jp.getArgs());
        Object proceeded;
        try {
            proceeded = jp.proceed();
        } catch (Exception e) {
            throw e;
        } catch (Throwable throwable) {
            log.error("Серьезная ошибка во время выполнения процесса: {}", jp.getSignature());
            throw throwable;
        }
        logIt(TRACE_METHOD_CALLING_END, jp.getSignature(), jp.getArgs());
        return proceeded;
    }

    @AfterReturning(pointcut = "controllerPackage() || controllerAnnotations()",
    returning = "result")
    public void logResult(JoinPoint jp, Object result) {
        logIt(HANDLE_RESULT_MESSAGE, result);
    }

    @Around("@annotation(edu.t1.aspect.annotation.TestPerformance)")
    public Object testingPerformance(ProceedingJoinPoint jp) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceeded;
        try {
            proceeded = jp.proceed();
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            log.error("Серьезная ошибка во время выполнения процесса: {}", jp.getSignature());
            throw throwable;
        }
        long end = System.currentTimeMillis();
        long result = end - start;
        if (result > 1500) {
            log.warn(LOW_PERFORMANCE_MESSAGE, jp.getSignature(), jp.getArgs(), result);
        } else {
            log.info(PERFORMANCE_MESSAGE, jp.getSignature(), jp.getArgs(), result);
        }
        return proceeded;
    }

    private void logIt(String message, Object... params) {
        switch (props.getLevel()) {
            case DEBUG -> log.debug(message, params);
            case INFO -> log.info(message, params);
            case TRACE -> log.trace(message, params);
            case WARN -> log.warn(message, params);
            case ERROR -> log.error(message, params);
        }
    }


}
