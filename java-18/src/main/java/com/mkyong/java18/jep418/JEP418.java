package com.mkyong.java18.jep418;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.spi.InetAddressResolver;
import java.net.spi.InetAddressResolverProvider;

public class JEP418 {

    public static void main(String[] args) throws UnknownHostException {

        // Returns a set of ip address, ipv4 and ipv6.
        InetAddress[] machines = InetAddress.getAllByName("google.com");
        for (InetAddress address : machines) {
            System.out.println(address.getHostAddress());
        }

        // Returns first address in its set of addresses.
        InetAddress ip = InetAddress.getByName("google.com");
        System.out.println(ip.getHostAddress());

    }
}
