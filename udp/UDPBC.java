package UDPBC;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.Vector;

import TCPSM.*;
import CDC.*;

public class UDPBC extends Thread {
	
	 public static void transfer() throws Exception {
	        DatagramPacket dp;
	        Scanner s = new Scanner(System.in);
	        System.out.println("UDPBC start: ");
	        String msg = "Key in";
	        Vector<InetSocketAddress> IPtable = TCPSM.getClientIPTable();
	        while (true) {
	            msg = s.next();
	            msg = encode();
	            
	            for(int i=0;i<IPtable.size();i++)
	    		{
	            	msg = encode();
	            	msg  = msg + " " + IPtable.get(i).getAddress().toString();
	            	dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length,IPtable.get(i).getAddress(), 80);
	            	DatagramSocket socket = new DatagramSocket();
	 				socket.send(dp);
	 				socket.close();
	            	
	    		}
	        }
	    }
	 public static void startUDPBroadCast() throws Exception
	 {
		 transfer();
	 }
	 private static String encode()
	 {
		 String msg ="3";
		 /*Vector v =  CDC.getUpdateInfo();
		 for(int i=0;i<v.size();i++)
		 {
			 
		 }*/
		 msg = msg +"l";
		return msg;
	 }

}
