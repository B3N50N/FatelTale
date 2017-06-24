package entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Vector;
public class PlayerInfo 
{
	private static PlayerInfo uniqueinstance;
	private int totaltype;
	private int health[],attack[],attackspeed[];
	private int defense[],movespeed[];
	private String PlayerInfofilePath=System.getProperty("user.dir")+"\\resource\\PlayerInfo.txt";
	private String[] PlayerFilePath;
	private PlayerInfo()
	{
		try
		{
			FileReader fin=new FileReader("./resource/PlayerInfo.txt");
			BufferedReader buff=new BufferedReader(fin);
			String str;
			str=buff.readLine();
			totaltype=Integer.parseInt(str);
			PlayerFilePath=new String[totaltype];
			health=new int[totaltype];
			attack=new int[totaltype];
			attackspeed=new int[totaltype];
			defense=new int[totaltype];
			movespeed=new int[totaltype];
			for(int i=0;i<totaltype;i+=1)
				PlayerFilePath[i]=buff.readLine();
			buff.close();
			fin.close();
			for(int i=0;i<totaltype;i+=1)
			{
				fin=new FileReader("./resource/"+PlayerFilePath[i]);
				buff=new BufferedReader(fin);
				str=buff.readLine();
				health[i]=Integer.parseInt(str);
				str=buff.readLine();
				attackspeed[i]=Integer.parseInt(str);
				str=buff.readLine();
				defense[i]=Integer.parseInt(str);
				str=buff.readLine();
				movespeed[i]=Integer.parseInt(str);
				fin.close();
				buff.close();
			}
		}
		catch(IOException e)
		{
			System.out.println("Cannot find the file");
		}
	}
	public Vector getTypeInfo(int type)
	{
		Vector<Integer> v=new Vector<Integer>();
		v.add(0,health[type]);
		v.add(1,attack[type]);
		v.add(2,attackspeed[type]);
		v.add(3,defense[type]);
		v.add(4,movespeed[type]);
		return v;
	}
	public static synchronized PlayerInfo getInstance()
	{
		if(uniqueinstance==null)
			uniqueinstance=new PlayerInfo();
		return uniqueinstance;
	}
	/*public static void main(String[] args)
	{
		PlayerInfo playerinfo;
		playerinfo=PlayerInfo.getInstance();
	}*/
}
