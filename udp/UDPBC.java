package udp;

import java.io.*;

import java.net.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.Vector;

import tcp.*;
import cdc.*;
import cdc.CDC;

public class UDPBC extends Thread {
	
	
	private static int blocknum =0, nowblocknum =0;
	private static Vector v;
	
	
	 MyThread my = new MyThread();
	 Thread t = new Thread(my,"_");
	 public static UDPBC meow;
	 public static synchronized UDPBC getInstance()
	 {
		 if(meow==null)
		 {
			 meow=new UDPBC();
		 }
		 return meow;
	 }
	 
	 public  void startUDPBroadCast()
	 {
		 t.start();
	 }
	 
	 class MyThread extends Thread 
	 {
		 public void run()
		 {
			 try
			 {
				 transfer();
			 }catch( Exception e){
				 System.out.println("fail to start UDP");
			 }
			  
		 }
		
	 }
	
	 
	 public static void transfer() throws Exception {
	        DatagramPacket dp;
	        Scanner s = new Scanner(System.in);
	        System.out.println("UDPBC start: ");
	        String msg = "Key in";
	        Vector<InetSocketAddress> IPtable = TCPClient.getClientIPTable();
	        while (true) {
	            msg = s.next();
	            msg = encode();
	            
	            for(int i=0;i<IPtable.size();i++)
	    		{
	            	//msg = encode();
	            	//msg  = msg + " " + IPtable.get(i).getAddress().toString();
	            	dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length,IPtable.get(i).getAddress(), 80);
	            	DatagramSocket socket = new DatagramSocket();
	 				socket.send(dp);
	 				socket.close();
	    		}
	            
	            
	            
	        }
	    }
	 
	 private static String encode()
	 {
		 String msg ="3";
		 
		 
		 msg = "";
		 
		 int stagenum=0;
		 
		 if(blocknum == 0)
		 {
			 v =  CDC.getInstance().getUpdatInfo();
			 //System.out.println(v.size());
			 blocknum = v.size();
			 nowblocknum =0;
		 }
		 
		 if(nowblocknum+1000> v.size())
		 {
			 stagenum = v.size();
		 }
		 else
		 {
			 stagenum = nowblocknum+1000;
		 }
		 
		 for(int i=nowblocknum;i<stagenum;i++)
		 {
			 msg =msg + "&";
			 
			 msg =msg+v.get(i);
			 
			 System.out.println(v.get(i)); 
			 //System.out.println(msg);
			 blocknum --;
		 }
		
		 nowblocknum =stagenum;
		 msg = msg +"&";
		return msg;
	 }

}
