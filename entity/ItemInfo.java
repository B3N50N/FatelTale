package entity;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
public class ItemInfo 
{
	private static ItemInfo uniqueinstance;
	private int totaltype;
	private int[] diff_attack=new int[totaltype];
	private int[] diff_attackspeed=new int[totaltype];
	private int[] diff_defense=new int[totaltype];
	private int[] diff_movespeed=new int[totaltype];
	private int[] diff_health=new int[totaltype];
	private Point[] location;
	private String[] ItemFilePath;
	private ItemInfo()
	{
		try
		{
			FileReader fin=new FileReader("./resource/ItemInfo/ItemInfo.txt");
			BufferedReader buff=new BufferedReader(fin);
			String str;
			str=buff.readLine();
			totaltype=Integer.parseInt(str);
			ItemFilePath=new String[totaltype];
			diff_health=new int[totaltype];
			diff_attack=new int[totaltype];
			diff_attackspeed=new int[totaltype];
			diff_defense=new int[totaltype];
			diff_movespeed=new int[totaltype];
			for(int i=0;i<totaltype;i+=1)
				ItemFilePath[i]=buff.readLine();
			buff.close();
			fin.close();
			for(int i=0;i<totaltype;i+=1)
			{
				fin=new FileReader("./resource/ItemInfo/"+ItemFilePath[i]);
				buff=new BufferedReader(fin);
				str=buff.readLine();
				diff_health[i]=Integer.parseInt(str);
				str=buff.readLine();
				diff_attack[i]=Integer.parseInt(str);
				str=buff.readLine();
				diff_attackspeed[i]=Integer.parseInt(str);
				str=buff.readLine();
				diff_defense[i]=Integer.parseInt(str);
				str=buff.readLine();
				diff_movespeed[i]=Integer.parseInt(str);
				fin.close();
				buff.close();
			}
		}
		catch(IOException e)
		{
			System.out.println("Cannot find the file");
		}
	}
	public static synchronized ItemInfo getInstance()
	{
		if(uniqueinstance==null)
			uniqueinstance=new ItemInfo();
		return uniqueinstance;
	}
	public Vector getTypeInfo(int type)
	{
		assert type>=0&&type<=totaltype:"The type number is invalid!!";
		Vector<Integer> v=new Vector<Integer>();
		v.add(0,diff_health[type]);
		v.add(1,diff_attack[type]);
		v.add(2,diff_attackspeed[type]);
		v.add(3,diff_defense[type]);
		v.add(4,diff_movespeed[type]);
		return v;
	}
}
