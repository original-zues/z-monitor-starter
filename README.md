# Z-Monitor 🚀
### Next-Gen SRE Monitoring Starter for Spring Boot

Z-Monitor is a powerful, "No-Server" monitoring suite designed for Spring Boot projects. It provides a sophisticated, glassmorphism-inspired command center for real-time technical health, JVM telemetry, and business metrics without requiring external monitoring infrastructure.

---

## 📦 Installation (via JitPack)

Add the JitPack repository and Z-Monitor dependency to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.YOUR_GITHUB_USERNAME</groupId>
        <artifactId>z-monitor-starter</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

---

## 🛠 Features

- **Real-time Telemetry:** JVM Memory, CPU, and Load Average tracking.
- **Edge Performance:** Real-time RPS (Requests Per Second) monitoring.
- **Infrastructure Health:** Granular health status for DB, Redis, Disk, etc.
- **Diagnostics:** Runtime log-level management and thread stack snapshots.
- **Yangon Context:** UI-wide timezone conversion for regional clarity.
- **Modern UI:** High-density, glass-bento dashboard built with Tailwind CSS.

---

## 🧩 Custom Metrics (Pluggable)

Z-Monitor allows you to plug in your own project-specific metrics. Simply implement the `ZMonitorMetricsProvider` interface as a `@Bean`:

```java
@Service
public class MyCustomMetrics implements ZMonitorMetricsProvider {
    @Override
    public Map<String, Object> getMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("activeUsers", userService.getActiveCount());
        metrics.put("newSales", orderService.getTodaySales());
        return metrics;
    }
}
```

---

## 🔐 Security & Access

The Z-Monitor UI is available at `/admin/login`. 

By default, the starter provides the controllers and templates. You should secure these paths in your project's Spring Security configuration:

```java
.requestMatchers("/admin/**", "/api/v1/admin/**").hasRole("ADMIN")
```

---

## ⚡ Requirements

- **Spring Boot 3.3.x+**
- **Java 17+**
- **Spring Boot Actuator** (enabled with `management.endpoints.web.exposure.include=*`)

---

## 📝 License
Distributed under the MIT License.
