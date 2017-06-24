package udp;

import java.io.*;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import dom.*;

public class UDPUS {
	
	static int clientno;
    static Player player;
    static int x;
    static int y;
    static int direction;
    static int assetIndex;
    //static int frame;
    static int id;
    static Monster monster ;
    static Projector projector;
    static Item item;
    static int health;
    static int maxHealth;
    static int score;
    static char[] temp = new char[50];
    static char temp2;
    static String temp3;
	
	public static void transfer() throws Exception {
        byte[] buffer = new byte[65507];
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        DatagramSocket ds = new DatagramSocket(80); // Set Server Port
        System.out.println("伺服器啟動於 : "
                + InetAddress.getLocalHost().getHostAddress() + ":" + ds.getLocalPort());
        String msg = "No Message...";
        while (true) {
            ds.receive(dp);
            msg = new String(dp.getData(), 0, dp.getLength());
            System.out.println("傳來的訊息 : " + msg);
            decode(msg);
        }
    }
	private static void decode(String msg)
	{
		int type = 0;
		int numofword=0;
		int numofelement = 0;
		
		temp3 ="";
		temp2 = ' ';
		
		for(int i = 0;i< msg.length();i++)
		{
			temp2 = msg.charAt(i);//取出字元
			if(temp2 == ' ')
			{
				for(int j= 0;j<numofword;j++)
				{
					temp3 = temp3 + Character.toString(temp[j]);
				}
				//System.out.println(temp3);
				
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
						//System.out.println("axHealth = "+axHealth);
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
					  
				  }
				  else if(type ==3)
				  {
					  
				  }
				  else if(type ==4)
				  {
					  
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
				clientno = Integer.parseInt(temp3);//暫時猜測
				id = Integer.parseInt(temp3);//暫時猜測
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
			DOM.getInstance().updatePlayer(clientno, x, y, direction, assetIndex);
			DOM.getInstance().updatePlayerInfo( clientno, health, maxHealth, score);
		}
		else if(type ==2)
		{
			DOM.getInstance().updateMonster(id, x, y, direction, assetIndex);
		}
		else if(type ==3)
		{
			DOM.getInstance().updateProjector(id, x, y, direction, assetIndex);
		}
		else if(type ==4)
		{
			DOM.getInstance().updateItem(id, x, y, direction, assetIndex);
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

}
