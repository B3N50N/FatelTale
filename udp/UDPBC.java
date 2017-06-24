package udp;

import java.io.*;

import java.net.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.Vector;

import tcp.*;
import cdc.*;
import cdc.CDC;
import logger.Logger;

public class UDPBC extends Thread {
	
	
	private static int blocknum =0, nowblocknum =0;
	private static Vector v;
	private static int msg_crc;
	private static int stage_max =1000;
	private static int port =8890;
	
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
				 Logger.log("fail to start UDPBC");
			 }
			  
		 }
		
	 }
	
	 
	 private static void transfer() throws Exception {
	        DatagramPacket dp;
	        Scanner s = new Scanner(System.in);
	        Logger.log("UDPBC start: ");
	        String msg = "Key in";
	        Vector<InetAddress> IPtable = TCPServer.getServer().getClientIPTable();
	        while (true) {
	            msg = encode();
	            msg_crc = msg.hashCode();
	            Logger.log(msg_crc);
	            msg ="$"+ msg_crc +"$" + msg;
	            
	            for(int i=0;i<IPtable.size();i++)
	    		{
	            	//msg = "&1=Player 2 0 10000 10000 west 0 -100.0 100.0 &";
	            	//msg  = msg + " " + IPtable.get(i).getAddress().toString();
	            	dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, IPtable.get(i), port);
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
		 
		 if(nowblocknum+stage_max> v.size())
		 {
			 stagenum = v.size();
		 }
		 else
		 {
			 stagenum = nowblocknum+stage_max;
		 }
		 
		 for(int i=nowblocknum;i<stagenum;i++)
		 {
			 msg =msg + "&";
			 
			 msg =msg+v.get(i);
			 
			 Logger.log(v.get(i).toString()); 
			 //System.out.println(msg);
			 blocknum --;
		 }
		
		 nowblocknum =stagenum;
		 msg = msg +"&";
		return msg;
	 }

}
