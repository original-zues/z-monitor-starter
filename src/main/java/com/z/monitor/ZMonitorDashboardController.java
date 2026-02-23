package com.z.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/z-monitor")
public class ZMonitorDashboardController {

    private final ZMonitorProperties properties;

    public ZMonitorDashboardController(ZMonitorProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("refreshInterval", properties.getRefreshInterval());
        model.addAttribute("appName", properties.getAppName());
        model.addAttribute("environment", properties.getEnvironment());
        return "admin/dashboard";
    }
}
