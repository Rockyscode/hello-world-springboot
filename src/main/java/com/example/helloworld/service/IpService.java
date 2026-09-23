package com.example.helloworld.service;

import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class IpService {

    /**
     * Resolves the primary IPv4 address of the current host system.
     * Prefers active non-loopback network interfaces over localhost loopback.
     */
    public String getPrimarySystemIp() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = interfaces.nextElement();
                    if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                        continue;
                    }
                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress addr = addresses.nextElement();
                        if (addr instanceof Inet4Address && !addr.isLoopbackAddress() && !addr.isLinkLocalAddress()) {
                            return addr.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException ignored) {
            // fallback below
        }

        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

    /**
     * Retrieves the host name of the current machine.
     */
    public String getSystemHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown-host";
        }
    }

    /**
     * Enumerates all active network interfaces and their assigned IP addresses.
     */
    public Map<String, List<String>> getAllNetworkInterfaces() {
        Map<String, List<String>> map = new LinkedHashMap<>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    NetworkInterface netIf = interfaces.nextElement();
                    if (!netIf.isUp()) {
                        continue;
                    }
                    List<String> ips = new ArrayList<>();
                    Enumeration<InetAddress> addresses = netIf.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress addr = addresses.nextElement();
                        ips.add(addr.getHostAddress());
                    }
                    if (!ips.isEmpty()) {
                        map.put(netIf.getDisplayName() + " (" + netIf.getName() + ")", ips);
                    }
                }
            }
        } catch (SocketException ignored) {
            // Return whatever was collected
        }
        return map;
    }
}
