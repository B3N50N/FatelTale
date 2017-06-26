package udp;

import java.io.*;

import java.net.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import tcp.*;
import cdc.*;
import cdc.CDC;

public class UDPBC extends Thread {
	
	
	private static int blocknum =0, nowblocknum =0;
	private static Vector v;
	private static int msg_crc;
	private static int stage_max =500,delay =10;
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
	 @SuppressWarnings("deprecation")
	 public  void pauseUDPServer()
	 {
		 t.stop();
	 }
	 @SuppressWarnings("deprecation")
	 public  void stopUDPServer()
	 {
		 t.suspend();
	 }
	 @SuppressWarnings("deprecation")
	 public  void resumeUDPServer()
	 {
		 t.resume();;
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
	
	 
	 private static void transfer() throws Exception {
	        DatagramPacket dp;
	        Scanner s = new Scanner(System.in);
	        System.out.println("UDPBC start: ");
	        String msg = "Key in";
	        Vector<InetAddress> IPtable = TCPServer.getServer().getClientIPTable();
	        //Vector<InetSocketAddress> IPtable = TCPServer.getClientIPTable();
	        while (true) {
	        	TimeUnit.MILLISECONDS.sleep(delay);
	            msg = s.next();//debug only
	            msg = encode();
	            //msg ="&Player 0 2 0 10000 west 0 100.0 100.0   &";
	            msg_crc = msg.hashCode();
	            msg ="$"+ msg_crc +"$" + msg;
	            System.out.println(msg);
	            System.out.println(msg_crc);
	            for(int i=0;i<IPtable.size();i++)
	    		{
	            	//msg = "$-592448706$&Monster 0 4 -54 0 -9 2&";
	            	//msg  = msg + " " + IPtable.get(i).getAddress().toString();
	            	dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, IPtable.get(i), port);
	            	//dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length,IPtable.get(i).getAddress(), port);
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
			 msg =msg + " &";
			 
			 msg =msg+v.get(i);
			 
			 //System.out.println(v.get(i)); 
			 //System.out.println(msg);
			 blocknum --;
		 }
		
		 nowblocknum =stagenum;
		 msg = msg +"&";
		return msg;
	 }

}
