package com.baeldung.networking.interfaces;

import org.junit.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import static org.junit.Assert.assertNotNull;

public class DebuggingMacAddressUnitTest {

    /*
        The given part describes the state of the world before you begin the behavior you're specifying in this scenario. You can think of it as the pre-conditions to the test.
        The when section is that behavior that you're specifying.
        Finally the then section describes the changes you expect due to the specified behavior.
    */

    @Test
    public void givenNetworkInterface_whenGetMacAddressForLocalhost_thenGetMacAddress() throws UnknownHostException, SocketException {
        InetAddress localHost = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
        System.out.println(ni.getDisplayName());
        String macAddress = formatMacAddress(ni.getHardwareAddress());
        assertNotNull(macAddress);
    }

    @Test
    public void whenLocalHostIPSpecified_thenGetMacAddress() throws SocketException, UnknownHostException {
        InetAddress localhost = InetAddress.getByName("192.168.1.108");
        NetworkInterface ni = NetworkInterface.getByInetAddress(localhost);
        System.out.println(ni.getDisplayName());
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
                System.out.println("host: " + inetAddress.getHostAddress());
            }


            //System.out.println(ni.getInetAddresses());

            //System.out.println(networkInterface.getDisplayName() + " " + networkInterface.getHardwareAddress());

            if (ni.getHardwareAddress() != null) {
                String macAddressFull = formatMacAddress(ni.getHardwareAddress());
            }
            System.out.println("*****");
            System.out.println("");
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
        String hardwareAddress = String.join("-", humanFriendly);
        System.out.println("MAC Address: " + hardwareAddress);
        return hardwareAddress;
    }
}
