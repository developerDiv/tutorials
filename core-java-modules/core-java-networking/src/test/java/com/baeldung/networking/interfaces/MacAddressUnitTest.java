package com.baeldung.networking.interfaces;

import org.junit.Test;
import sun.nio.ch.Net;

import java.net.*;
import java.util.Enumeration;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class MacAddressUnitTest {

    /*
        The given part describes the state of the world before you begin the behavior you're specifying in this scenario. You can think of it as the pre-conditions to the test.
        The when section is that behavior that you're specifying.
        Finally the then section describes the changes you expect due to the specified behavior.
    */

    @Test
    public void whenLocalHostSpecified_thenGetMacAddress() throws UnknownHostException, SocketException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());
        NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
        String macAddress = formatMacAddress(ni.getHardwareAddress());
        assertNotNull(macAddress);
    }

    @Test
    public void whenLocalHostIPSpecified_thenGetMacAddress() throws SocketException, UnknownHostException {
        InetAddress localhost = InetAddress.getByName("169.254.191.216");
        NetworkInterface ni = NetworkInterface.getByInetAddress(localhost);
        String macAddress = formatMacAddress(ni.getHardwareAddress());
        assertNotNull(macAddress);
    }

    @Test
    /*
        utun2 null
        utun1 null
        utun0 null
        awdl0 [B@73a8dfcc
        en5 [B@ea30797
        en0 [B@7e774085
        lo0 null
     */
    public void getAllInterfaces() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()) {
            NetworkInterface ni = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
            System.out.println("Addresses associated with this network interface:");

            while(inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                System.out.println("display name: " + ni.getDisplayName());
                System.out.println("host: " + inetAddress);
            }
            System.out.println("*****");
            System.out.println("");

            //System.out.println(ni.getInetAddresses());

            //System.out.println(networkInterface.getDisplayName() + " " + networkInterface.getHardwareAddress());

//            if (networkInterface.getHardwareAddress() != null) {
//
//                byte[] macAddressBytes = networkInterface.getHardwareAddress();
//                String[] MAC = new String[macAddressBytes.length];
//
//                for (int i = 0; i < macAddressBytes.length; i++) {
//                    MAC[i] = String.format("%02X", macAddressBytes[i]);
//                }
//
//                String macAddressFull = String.join("-", MAC);
//                System.out.println("Full MAC Address: " + macAddressFull);
//            }
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
        String[] MacAddressHexadecimalFormat = new String[macAddress.length];
        for (int i = 0; i < macAddress.length; i++) {
            MacAddressHexadecimalFormat[i] = String.format("%02X", macAddress[i]);
        }
        String hardwareAddress = String.join("-", MacAddressHexadecimalFormat);
        System.out.println("MAC Address: " + hardwareAddress);
        return hardwareAddress;
    }
}
