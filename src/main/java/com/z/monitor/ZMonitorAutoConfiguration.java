package com.z.monitor;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@AutoConfiguration
@ConditionalOnWebApplication
public class ZMonitorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ZMonitorMetricsProvider zMonitorMetricsProvider() {
        return new ZMonitorDefaultMetricsProvider();
    }

    @Bean
    public ZMonitorDashboardController zMonitorDashboardController() {
        return new ZMonitorDashboardController();
    }

    @Bean
    public ZMonitorMetricsController zMonitorMetricsController(List<ZMonitorMetricsProvider> providers) {
        return new ZMonitorMetricsController(providers);
    }
}
