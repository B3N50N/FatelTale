package entity;
import java.util.TreeMap;
import java.util.Vector;
import entity.Player;
import entity.Treasure;
public class CDC
{
	private final static int Maxplayerno=4;
	private Player[] player=new Player[Maxplayerno];
	public CDC()
	{
		for(int i=0;i<Maxplayerno;i+=1)
			player[i]=new Player();
	}
	public Vector getUpdateInfo()
	{
		Vector v=new Vector();
		for(int i=0;i<Maxplayerno;i+=1)
		{
			if(player[i].isActive()==true)
			{
				String tmp=new String();
				tmp=player[i].toString();
				System.out.println(tmp);
				v.add(tmp);
			}
		}
		assert v.size()>0:"There is no player!!";
		return v;
	}
	public static void main(String[] args)
	{
		CDC cdc=new CDC();
		System.out.println(cdc.getUpdateInfo());
	}
}
