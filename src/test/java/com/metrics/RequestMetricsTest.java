package com.metrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RequestMetricsTest {

    @Test
    public void test() throws Exception {
        final RequestMetrics requestMetrics = new RequestMetrics();

        Runnable work = () -> {
            for(int i=0; i<10000; ++i){
                requestMetrics.incrementActiveRequests();
                requestMetrics.decrementActiveRequests();
            }
        };

        Thread t1 = new Thread(work);
        Thread t2 = new Thread(work);
        Thread t3 = new Thread(work);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        assertTrue(requestMetrics.getActiveRequests() == 0d);
    }
}