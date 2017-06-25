package entity;
import java.awt.Point;
import java.util.Vector;
public class Item 
{
	private int attack,attackspeed,defense,movespeed,health;
	private int asset_index;
	private Point _pos;
	private Collider _collider;
	public Item(Point point,int type,Vector attribute)
	{
		_pos=point;
		health=(int)attribute.get(0);
		attack=(int)attribute.get(1);
		attackspeed=(int)attribute.get(2);
		defense=(int)attribute.get(3);
		movespeed=(int)attribute.get(4);
	}
	
	public void setPosition(Point p) {
		_pos = p;
		_collider.setPosition(p);
	}
	
	public int getAttack(){return attack;}
	public int getAttackspeed(){return attackspeed;}
	public int getDefense(){return defense;}
	public int getMoveSpeed(){return movespeed;}
	public int getHealth(){return health;}
	public Point getPosition(){return _pos;}
	public String toString()
	{
		String str="";
		str+=String.valueOf(asset_index);
		str+=" ";
		str+=String.valueOf(_pos.getX());
		str+=" ";
		str+=String.valueOf(_pos.getY());
		str+=" ";
		return str;
	}
}
