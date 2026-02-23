package com.z.monitor;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import java.util.List;

@AutoConfiguration
@ConditionalOnWebApplication
@EnableConfigurationProperties(ZMonitorProperties.class)
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

            @Bean
            public ZMonitorAuthController zMonitorAuthController(ZMonitorProperties properties) {
                return new ZMonitorAuthController(properties);
            }
        
            @org.springframework.context.annotation.Configuration
            @org.springframework.boot.autoconfigure.condition.ConditionalOnClass(name = "org.springframework.security.web.SecurityFilterChain")
            static class ZMonitorSecurityConfiguration {
                @Bean
                public org.springframework.boot.web.servlet.FilterRegistrationBean<ZMonitorSecurityFilter> zMonitorSecurityFilter() {
                    org.springframework.boot.web.servlet.FilterRegistrationBean<ZMonitorSecurityFilter> registrationBean = new org.springframework.boot.web.servlet.FilterRegistrationBean<>();
                    registrationBean.setFilter(new ZMonitorSecurityFilter());
                    registrationBean.addUrlPatterns("/z-monitor/*", "/actuator/*");
                    registrationBean.setOrder(org.springframework.core.Ordered.HIGHEST_PRECEDENCE);
                    return registrationBean;
                }
            }
        }
        