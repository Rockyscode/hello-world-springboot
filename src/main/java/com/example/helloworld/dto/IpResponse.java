package com.example.helloworld.dto;

import java.util.List;
import java.util.Map;

public record IpResponse(
    String systemIp,
    String hostName,
    String clientIp,
    Map<String, List<String>> networkInterfaces
) {}
