package com.metrics;

import io.micrometer.core.annotation.Timed;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Timed.class)
@EnableConfigurationProperties(MetricsProperties.class)
@AutoConfigureBefore(CompositeMeterRegistryAutoConfiguration.class)
public class RequestMetricsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RequestMetrics requestMetrics() {
        return new RequestMetrics();
    }

}