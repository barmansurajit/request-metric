package com.metrics;

public class RequestMetrics {

    private Double activeRequests = 0d;
    private final Object activeRequestLock = new Object();

    public Double getActiveRequests() {
        return activeRequests;
    }

    public void incrementActiveRequests() {
        synchronized (activeRequestLock) {
            activeRequests += 1d;
        }
    }

    public void decrementActiveRequests() {
        synchronized (activeRequestLock) {
            activeRequests -= 1d;
        }
    }
}
