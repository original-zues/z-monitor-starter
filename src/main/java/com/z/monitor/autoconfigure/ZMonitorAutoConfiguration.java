package com.z.monitor.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
import java.util.Optional;

@AutoConfiguration
@ConditionalOnWebApplication
public class ZMonitorAutoConfiguration {

    @Bean
    public ZMonitorDashboardController zMonitorDashboardController() {
        return new ZMonitorDashboardController();
    }

    @Bean
    public ZMonitorMetricsController zMonitorMetricsController(List<ZMonitorMetricsProvider> providers) {
        return new ZMonitorMetricsController(providers);
    }
}
