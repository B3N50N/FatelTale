package UDPUS;

import java.io.*;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import DOM.*;

public class UDPUS {
	public static void transfer() throws Exception {
        byte[] buffer = new byte[65507];
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        DatagramSocket ds = new DatagramSocket(80); // Set Server Port
        System.out.println("���A���Ұʩ� : "
                + InetAddress.getLocalHost().getHostAddress() + ":" + ds.getLocalPort());
        String msg = "No Message...";
        while (true) {
            ds.receive(dp);
            msg = new String(dp.getData(), 0, dp.getLength());
            System.out.println("�ǨӪ��T�� : " + msg);
            decode(msg);
        }
    }
	private static void decode(String msg)
	{
		
	}

}
