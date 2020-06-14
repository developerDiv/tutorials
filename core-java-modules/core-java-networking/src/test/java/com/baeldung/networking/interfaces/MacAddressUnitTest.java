package com.baeldung.networking.interfaces;

import org.junit.Test;
import sun.nio.ch.Net;

import java.net.*;
import java.util.Enumeration;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class MacAddressUnitTest {

    @Test
    public void whenUsingLocalHost_thenGetMacAddress() throws UnknownHostException, SocketException {
        InetAddress localHost = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
        byte[] macAddress = ni.getHardwareAddress();
        assertNotNull(macAddress);
    }

    @Test
    public void whenIPSpecified_thenGetMacAddress() throws SocketException, UnknownHostException {
        InetAddress localhost = InetAddress.getByName("192.168.1.108");
        NetworkInterface ni = NetworkInterface.getByInetAddress(localhost);
        byte[] macAddress = ni.getHardwareAddress();
        assertNotNull(macAddress);
    }

    @Test
    public void givenNetworkInterface_whenGetAllIntefaces_thenReturnValidMacAddresses() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()) {
            NetworkInterface ni = networkInterfaces.nextElement();
            if (ni.getHardwareAddress() != null) {
                byte[] macAddressBytes = ni.getHardwareAddress();
            }
        }
    }


    @Test
    public void givenName_whenReturnsNetworkInterface_thenCorrect() throws SocketException {
        NetworkInterface nif = NetworkInterface.getByName("en0");
        System.out.println(nif.toString());
        assertNotNull(nif);
    }

    @Test
    public void getLoopbackAddress() {
        InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
        System.out.println(loopbackAddress);
    }

    @Test
    public void whenSpecifyingACertainPort_thenReturnMacAddress() throws SocketException, UnknownHostException {
        InetAddress localhost = InetAddress.getByName("192.168.1.108");
        NetworkInterface ni = NetworkInterface.getByInetAddress(localhost);
        System.out.println(ni);
    }

    private String formatMacAddress(byte[] macAddress) {
        String[] humanFriendly = new String[macAddress.length];
        for (int i = 0; i < macAddress.length; i++) {
            humanFriendly[i] = String.format("%02X", macAddress[i]);
        }
        return String.join("-", humanFriendly);
    }
}
