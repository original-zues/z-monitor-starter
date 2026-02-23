package com.z.monitor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/z-monitor")
public class ZMonitorMetricsController {

    private final List<ZMonitorMetricsProvider> metricsProviders;

    public ZMonitorMetricsController(List<ZMonitorMetricsProvider> metricsProviders) {
        this.metricsProviders = metricsProviders;
    }

    @GetMapping("/business-metrics")
    public ResponseEntity<Map<String, Object>> getMonitorMetrics() {
        Map<String, Object> allMetrics = new HashMap<>();
        
        // Merge metrics from all registered providers
        if (metricsProviders != null) {
            for (ZMonitorMetricsProvider provider : metricsProviders) {
                Map<String, Object> moduleMetrics = provider.getMetrics();
                if (moduleMetrics != null) {
                    allMetrics.putAll(moduleMetrics);
                }
            }
        }
        
        return ResponseEntity.ok(allMetrics);
    }
}
