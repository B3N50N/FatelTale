package entity;
import java.awt.Point;
import java.util.Vector;
public class Item 
{
	private final static int Maxx=100,Maxy=100;
	private int attack,attackspeed,defense,movespeed,health;
	private int asset_index;
	private Point location;
    @SuppressWarnings("rawtypes")
	public Item(Point point,int type,Vector attribute)
	{
		assert point.getX()>=0&&point.getX()<Maxx&&point.getY()>=0&&point.getY()<Maxy:"Location is wrong!!";
		location=point;
		health=(int)attribute.get(0);
		attack=(int)attribute.get(1);
		attackspeed=(int)attribute.get(2);
		defense=(int)attribute.get(3);
		movespeed=(int)attribute.get(4);
	}
	public int getAttack(){return attack;}
	public int getAttackspeed(){return attackspeed;}
	public int getDefense(){return defense;}
	public int getMoveSpeed(){return movespeed;}
	public int getHealth(){return health;}
	public Point getLocation(){return location;}
	public String toString()
	{
		String str="";
		str+=String.valueOf(asset_index);
		str+=" ";
		str+=String.valueOf(location.getX());
		str+=" ";
		str+=String.valueOf(location.getY());
		str+=" ";
		return str;
	}
}
