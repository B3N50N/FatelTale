package entity;
import java.util.Vector;
public class ItemInfo 
{
	private static ItemInfo uniqueinstance;
	private int totaltype=4;
	private int diff_attack[]=new int[totaltype];
	private int diff_attackspeed[]=new int[totaltype];
	private int diff_defense[]=new int[totaltype];
	private int diff_movespeed[]=new int[totaltype];
	private int diff_health[]=new int[totaltype];
	private ItemInfo()
	{
		//ÅªÀÉ
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
