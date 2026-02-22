package com.z.monitor.autoconfigure;

import java.util.Map;

/**
 * Interface for contributing custom metrics to Z-Monitor.
 * Multiple beans implementing this can exist and will be merged.
 */
public interface ZMonitorMetricsProvider {
    /**
     * Get custom metrics (e.g., business counters, module statuses).
     */
    Map<String, Object> getMetrics();
}
