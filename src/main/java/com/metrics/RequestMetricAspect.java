package com.metrics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class RequestMetricAspect {
    private final RequestMetrics requestMetrics;

    public RequestMetricAspect(RequestMetrics requestMetrics) {
        this.requestMetrics = requestMetrics;
    }

    public Object trackRequestCount(ProceedingJoinPoint joinPoint) throws Throwable {
        requestMetrics.incrementActiveRequests();
        try {
            return joinPoint.proceed();
        } finally {
            requestMetrics.decrementActiveRequests();
        }
    }
}
