package com.example.helloworld.controller;

import com.example.helloworld.dto.IpResponse;
import com.example.helloworld.service.IpService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IpController {

    private final IpService ipService;

    public IpController(IpService ipService) {
        this.ipService = ipService;
    }

    /**
     * Returns full IP information including current system IP, hostname,
     * network interface details, and client IP in JSON format.
     */
    @GetMapping(value = "/ip", produces = MediaType.APPLICATION_JSON_VALUE)
    public IpResponse getSystemIpInfo(
            HttpServletRequest request,
            @RequestParam(name = "format", required = false) String format) {
        
        String clientIp = extractClientIp(request);
        return new IpResponse(
                ipService.getPrimarySystemIp(),
                ipService.getSystemHostName(),
                clientIp,
                ipService.getAllNetworkInterfaces()
        );
    }

    /**
     * Returns just the raw IP string of the current system.
     * Useful for curl, command line, or simple scripts.
     */
    @GetMapping(value = "/ip/raw", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getRawSystemIp() {
        return ipService.getPrimarySystemIp();
    }

    private String extractClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isBlank()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
