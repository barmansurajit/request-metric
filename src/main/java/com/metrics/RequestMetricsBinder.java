package com.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;

import java.util.Collections;

public class RequestMetricsBinder implements MeterBinder {

    private final RequestMetrics requestMetrics;
    private final Iterable<Tag> tags;

    public RequestMetricsBinder(RequestMetrics requestMetrics) {
        this(requestMetrics, Collections.emptyList());
    }

    public RequestMetricsBinder(RequestMetrics requestMetrics, Iterable<Tag> tags) {
        this.requestMetrics = requestMetrics;
        this.tags = tags;
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        Gauge.builder("counter.active_requests", requestMetrics, RequestMetrics::getActiveRequests)
                .tags(tags)
                .description("The number of currently active requests on all annotated methods")
                .register(meterRegistry);
    }
}
