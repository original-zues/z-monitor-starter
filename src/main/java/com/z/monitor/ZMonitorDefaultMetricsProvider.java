package com.z.monitor;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Default implementation of ZMonitorMetricsProvider for demonstration.
 * In a real application, this would be replaced by actual business logic.
 */
@Service
public class ZMonitorDefaultMetricsProvider implements ZMonitorMetricsProvider {

    private final Random random = new Random();

    @Override
    public Map<String, Object> getMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        
        // Simulating some business metrics for the dashboard
        metrics.put("activeUsers", 100 + random.nextInt(50));
        metrics.put("totalUsers", 1000 + random.nextInt(100));
        metrics.put("newUsersToday", 10 + random.nextInt(10));
        
        return metrics;
    }
}
