package entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
public class PlayerInfo 
{
	private static PlayerInfo uniqueinstance;
	private int totaltype;
	private int health[],attack[],attackspeed[];
	private int defense[],movespeed[];
	private String PlayerInfofilePath="/PlayerInfo.txt";
	private String[] PlayerFilePath;
	private PlayerInfo()
	{
		try
		{
			FileReader fin=new FileReader(PlayerInfofilePath);
			BufferedReader buff=new BufferedReader(fin);
			totaltype=buff.read();
			PlayerFilePath=new String[totaltype];
			for(int i=0;i<totaltype;i+=1)
				PlayerFilePath[i]=buff.readLine();
			buff.close();
			fin.close();
			for(int i=0;i<totaltype;i+=1)
			{
				fin=new FileReader(PlayerFilePath[i]);
				buff=new BufferedReader(fin);
				health[i]=buff.read();
				attack[i]=buff.read();
				attackspeed[i]=buff.read();
				defense[i]=buff.read();
				movespeed[i]=buff.read();
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
}
