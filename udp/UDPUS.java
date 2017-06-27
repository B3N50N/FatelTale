package udp;

import java.awt.Point;
import java.io.*;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import logger.Logger;
import tcp.TCPClient;

import dom.*;

public class UDPUS {
	
    static Player player;
    static int x =0;
    static int y =0;
    static int direction =DynamicObject.DIRECTION.DOWN;
    static Point direction2 = new Point();
    static int assetIndex =0;
    //static int frame;
    static int id =0;
    static Monster monster ;
    static Projector projector;
    static Item item;
    static int health =0;
    static int maxHealth =0;
    static int score =0;
    static char[] temp = new char[50];
    static char temp2;
    static String temp3;
    
    static int port_start =8890;
    static int clientno =0;
    static int myclientno =0;
    
    
    MyThread my = new MyThread();
	 Thread t = new Thread(my,"_");
	 public static UDPUS meow;
	 public static synchronized UDPUS getInstance()
	 {
		 if(meow==null)
		 {
			 meow=new UDPUS();
             myclientno = TCPClient.getClient().getClientNo();
		 }
		 return meow;
	 }
	 
	 public  void initUDPServer()
	 {
		 t.start();
	 }
	 @SuppressWarnings("deprecation")
	public  void pauseUDPServer()
	 {
		 t.suspend();
	 }
	 @SuppressWarnings("deprecation")
	public  void stopUDPServer()
	 {
		 t.stop();
	 }
	 @SuppressWarnings("deprecation")
	public  void resumeUDPServer()
	 {
		 t.resume();
	 }
	 
	 class MyThread extends Thread 
	 {
		 public void run()
		 {
			 try
			 {
				 transfer();
			 }catch( Exception e){
				 Logger.log("fail to start UDP");
			 }
			  
		 }
		
	 }
    
	
	private static void transfer() throws Exception {
        byte[] buffer = new byte[65507];
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        DatagramSocket ds = new DatagramSocket(port_start + myclientno); // Set Server Port
        Logger.log("UDPUS starts on port " + (port_start + myclientno));
        String msg = "No Message...";
        while (true) {
            ds.receive(dp);
            msg = new String(dp.getData(), 0, dp.getLength());
            //Logger.log("msg recive : " + msg);
            decode(msg);
        }
    }
	private static void decode(String msg)
	{
		int type = 0;
		int numofword=0;
		int numofelement = 0;
		
		int CRCcheck=0;
		
		temp3 ="";
		temp2 = ' ';
		
		for(int i = 0;i< msg.length();i++)
		{
			
			if(msg.charAt(0)!= '$')//check pakage
			{
				break;
			}
			
			temp2 = msg.charAt(i);
			if(temp2 == ' ')
			{
				for(int j= 0;j<numofword;j++)
				{
					temp3 = temp3 + Character.toString(temp[j]);
				}
				System.out.println(temp3);
				
				if(type ==0)
				{
					if(temp3.equals("Player"))
					{
						type = 1;
					}
					if(temp3.equals("Monster"))
					{
						type = 2;
					}
					if(temp3.equals("Projector"))
					{
						type = 3;
					}
					if(temp3.equals("Item"))
					{
						type = 4;
					}
					if(temp3.equals("PlayerInfo"))
					{
						type = 5;
					}
					System.out.println("type = "+type);
				}
			    else if(type ==1)
				{
					if(numofelement ==0)
					{
						clientno = Integer.parseInt(temp3);
						//System.out.println("clientno = "+clientno);
						numofelement++;
					}
					else if(numofelement ==1)
					{
						assetIndex = Integer.parseInt(temp3);
						//System.out.println("assetIndex = "+assetIndex);
						numofelement++;
					}
					else if(numofelement ==2)
					{
						health = Integer.parseInt(temp3);
						//System.out.println("health = "+health);
						numofelement++;
					}
					else if(numofelement ==3)
					{
						maxHealth = Integer.parseInt(temp3);
						//System.out.println("maxHealth = "+maxHealth);
						numofelement++;
					}
					else if(numofelement ==4)
					{
						if(temp3.equals("west"))
						{
							direction =DynamicObject.DIRECTION.LEFT;
						}
						else if(temp3.equals("north"))
						{
							direction =DynamicObject.DIRECTION.UP;
						}
						else if(temp3.equals("east"))
						{
							direction =DynamicObject.DIRECTION.RIGHT;
						}
						else if(temp3.equals("south"))
						{
							direction =DynamicObject.DIRECTION.DOWN;
						}
						  
						//System.out.println("direction = "+ direction);
						numofelement++;
					}
					else if(numofelement ==5)
					{
						score = Integer.parseInt(temp3);
						//System.out.println("score = "+score);
						numofelement++;
					}
					else if(numofelement ==6)
					{
						x = (int)Float.parseFloat(temp3);
						//System.out.println("location_X = "+x);
						numofelement++;
					}
					else if(numofelement ==7)
					{
						y = (int)Float.parseFloat(temp3);
						//System.out.println("location_X = "+y);
						numofelement++;
					}
					  
				  }
				  else if(type ==2)
				  {
					  if(numofelement ==0)
					  {
						  id = Integer.parseInt(temp3);
						  //System.out.println("clientno = "+clientno);
						  numofelement++;
					  }
					  else if(numofelement ==1)
					  {
						  x = (int)Float.parseFloat(temp3);
						  //System.out.println("location_X = "+x);
						  numofelement++;
					  }
					  else if(numofelement ==2)
					  {
						  y = (int)Float.parseFloat(temp3);
						  //System.out.println("location_X = "+y);
						  numofelement++;
					  }
					  else if(numofelement ==3)
					  {
						  if(temp3.equals("west"))
							{
								direction =DynamicObject.DIRECTION.LEFT;
							}
							else if(temp3.equals("north"))
							{
								direction =DynamicObject.DIRECTION.UP;
							}
							else if(temp3.equals("east"))
							{
								direction =DynamicObject.DIRECTION.RIGHT;
							}
							else if(temp3.equals("south"))
							{
								direction =DynamicObject.DIRECTION.DOWN;
							}
							  
							//System.out.println("direction = "+ direction);
							numofelement++;
					  }
					  else if(numofelement ==5)
					  {
						  assetIndex = Integer.parseInt(temp3);
						  //System.out.println("assetIndex = "+assetIndex);
						  numofelement++;
					  }
				  }
				  else if(type ==3)
				  {
					  if(numofelement ==0)
					  {
						  id = Integer.parseInt(temp3);
						  //System.out.println("clientno = "+clientno);
						  numofelement++;
					  }
					  else if(numofelement ==1)
					  {
						  x = (int)Float.parseFloat(temp3);
						  //System.out.println("location_X = "+x);
						  numofelement++;
					  }
					  else if(numofelement ==2)
					  {
						  y = (int)Float.parseFloat(temp3);
						  //System.out.println("location_X = "+y);
						  numofelement++;
					  }
					  else if(numofelement ==3)
					  {
						  direction2.x= (int)Float.parseFloat(temp3);
						  //System.out.println("location_X = "+x);
						  numofelement++;
					  }
					  else if(numofelement ==4)
					  {
						  direction2.y = (int)Float.parseFloat(temp3);
						  //System.out.println("location_X = "+y);
						  numofelement++;
					  }
					  else if(numofelement ==5)
					  {
						  assetIndex = Integer.parseInt(temp3);
						  //System.out.println("assetIndex = "+assetIndex);
						  numofelement++;
					  }
				  }
				  else if(type ==4)
				  {
					  if(numofelement ==0)
						{
						    assetIndex = Integer.parseInt(temp3);
							//Logger.log("assetIndex = "+assetIndex);
							numofelement++;
						}
					  else if(numofelement ==1)
					  {
						  id= (int)Float.parseFloat(temp3);
						  //Logger.log("id= "+id);
						  numofelement++;
					  }
					  else if(numofelement ==2)
					  {
						  x= (int)Float.parseFloat(temp3);
						  //Logger.log("x= "+x);
						  numofelement++;
					  }
					  else if(numofelement ==3)
					  {
						  y = (int)Float.parseFloat(temp3);
						  //Logger.log("y = "+y);
						  numofelement++;
					  }
				  }
				  /*else if(type ==5)
				  {
					  
				  }*/
				  
				
				
				
				
				 
				temp3 ="";
				numofword =0;
				
			}
			else if(temp2 == '=')
			{
				for(int j= 0;j<numofword;j++)
				{
					temp3 = temp3 + Character.toString(temp[j]);
				}
				//clientno = Integer.parseInt(temp3);
				//id = Integer.parseInt(temp3);
				temp3 ="";
				numofelement =0;
				numofword =0;
				type =0;
			}
			else if(temp2 == '&')
			{
				callDOM(type);
				numofword =0;
				numofelement =0;
				type =0;
			}
			else if(temp2 == '$' && CRCcheck==0)
			{
				int CRCstate=0;
				
				String CRC_value ="";
				String sample ="";
				int CDC_value2 =0;
				for(int k = 0;k< msg.length();k++)
				{
					if(CRCstate ==0 && msg.charAt(k) == '$')
					{
						CRCstate =1;
					}
					else if(CRCstate ==1 && msg.charAt(k) == '$')
					{
						CRCstate =2;
					}
					else if(CRCstate ==1 && msg.charAt(k) != '$')
					{
						CRC_value = CRC_value + msg.charAt(k);
					}
					else if(CRCstate ==2 )
					{
						sample =sample + msg.charAt(k);
					}
				}
				//System.out.println(CRC_value.equals(sample.hashCode()));
				CDC_value2 = Integer.parseInt(CRC_value);
				if(CDC_value2 == sample.hashCode())
				{
					//Logger.log("CRC_value: "+CRC_value);
					//Logger.log("sample.hashCode(): "+sample.hashCode());
					CRCcheck=1;
				}
				else
				{
					//Logger.log("CRC_value: "+CRC_value);
					//Logger.log("sample.hashCode(): "+sample.hashCode());
					//Logger.log("package error!!!! ");
					break;
				}
				
			}
			else
			{
				temp[numofword] = temp2;
				numofword++;
			}
			
		}
		
	}
	private static void callDOM(int type)
	{
		if(type ==1)
		{
			//Logger.log("call_updatePlayer : " + " clientno : "+ clientno +" x : "+ x +" y : "+ y +" direction : "+ direction +" assertIndex : "+ assetIndex );
			DOM.getInstance().updatePlayer(clientno, x, y, direction, assetIndex);
			//Logger.log("updatePlayer_SUCCESS");
			//Logger.log("call_updatePlayerInfo : " + " clientno : "+ clientno +" health : "+ health +" maxHealth : "+ maxHealth +" score : " + score);
			DOM.getInstance().updatePlayerInfo( clientno, health, maxHealth, score);
			//Logger.log("updatePlayerInfo_SUCCESS");
        }
		else if(type ==2)
		{
			//analyzedirection();
			//Logger.log("call_updateMonster : " + " id : " + id + " x : "+ x +" y : "+ y +" direction : "+ direction +" assertIndex : "+assetIndex);
			//DOM.getInstance().updateMonster(id, x, y, direction, assetIndex);
			DOM.getInstance().updateMonster( id,x,y,direction,assetIndex);
		    //Logger.log("updateMonster_SUCCESS");
		}
		else if(type ==3)
		{
			//analyzedirection();
			//Logger.log("call_updateProjector : " + " id : "+ id +" x : "+ x +" y : "+ y +" directionX : "+ direction2.x+" directionY : "+ direction2.y +" assertIndex : "+ assetIndex);
			DOM.getInstance().updateProjector(id, x, y, direction2.x,  direction2.y, assetIndex);
			//Logger.log("updateProjector_SUCCESS");
		}
		else if(type ==4)
		{
			//Logger.log("call_updateItem : " + " id : "+ id +" x : "+ x +" y : "+ y +" direction : "+ direction +" assertIndex : " + assetIndex);
			DOM.getInstance().updateItem(id, x, y, assetIndex);
			//Logger.log("updateItem_SUCCESS");
		}
		/*else if(type ==5)
		{
			DOM.getInstance().updatePlayerInfo( clientno, health, maxHealth, score);
		}*/
		/*DOM.getInstance().updatePlayer(clientno, player);
		DOM.getInstance().updatePlayer(clientno, x, y, direction, assetIndex);
		DOM.getInstance().updateMonster(id, monster);
		DOM.getInstance().updateMonster(id, x, y, direction, assetIndex);
		DOM.getInstance().updateProjector( id, projector);
		DOM.getInstance().updateProjector(id, x, y, direction, assetIndex);
		DOM.getInstance().updateItem( id, item);
		DOM.getInstance().updateItem(id, x, y, direction, assetIndex);
		DOM.getInstance().updatePlayerInfo( clientno, health, maxHealth, score);*/
	}
	private static void analyzedirection()
	{
		if(direction2.x ==0 && direction2.y >=0 )
		{
			direction = DynamicObject.DIRECTION.DOWN;
		}
		else if(direction2.x ==0 && direction2.y <0 )
		{
			direction = DynamicObject.DIRECTION.UP;
		}
		else if(direction2.x >0 && direction2.y ==0 )
		{
			direction = DynamicObject.DIRECTION.RIGHT;
		}
		else if(direction2.x <0 && direction2.y ==0 )
		{
			direction = DynamicObject.DIRECTION.LEFT;
		}
	}

}
