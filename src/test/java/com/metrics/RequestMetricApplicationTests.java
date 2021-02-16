package com.metrics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RequestMetricApplicationTests {

    private RequestMetricAspect target;

    @Mock
    private RequestMetrics requestMetrics;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Before
    public void setUp() {
        target = new RequestMetricAspect(requestMetrics);
    }

    @Test
    public void mainTest() throws Throwable {
        target.trackRequestCount(joinPoint);
        verify(requestMetrics, times(1)). incrementActiveRequests();
        verify(requestMetrics, times(1)). decrementActiveRequests();
    }

    @Test
    public void mainTest_JoinPointThrows() throws Throwable {
        Exception expectedExcpn = new Exception("expected");

        when(joinPoint.proceed()).thenThrow(expectedExcpn);

        try {
            target.trackRequestCount(joinPoint);
        }
        catch (Exception e){
            assertEquals(expectedExcpn,e);
        }

        verify(requestMetrics, times(1)). incrementActiveRequests();
        verify(requestMetrics, times(1)). decrementActiveRequests();
    }
}
