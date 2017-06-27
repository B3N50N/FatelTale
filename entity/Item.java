package entity;
import java.awt.Point;
import java.util.Vector;
public class Item 
{
	private int attack,attackspeed,defense,movespeed,health;
	private int asset_index;

	private Point _pos;
	private Collider _collider;
	
	private Item(int atk, int as, int def, int ms, int h, int ai, Collider c) {
		attack = atk;
		attackspeed = as;
		defense = def;
		movespeed = ms;
		health = h;
		asset_index = ai;
		_collider = c;
		_pos = c.getPosition();
	}
	
    @SuppressWarnings("rawtypes")
	public Item(Point point,int type,Vector attribute, Collider c)
	{
		_pos=point;
		health=(int)attribute.get(0);
		attack=(int)attribute.get(1);
		attackspeed=(int)attribute.get(2);
		defense=(int)attribute.get(3);
		movespeed=(int)attribute.get(4);
		asset_index = type;
		_collider = c;
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
	public Collider getCollider() { return _collider; }
	
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
	
	public Item clone() {
		Item newInstance = new Item(attack, attackspeed, defense, movespeed, health, asset_index, _collider.clone());
		return newInstance;
	}
	
	public void Print() {
		System.out.println("Item : ");
		System.out.println("Position " + _pos);
		_collider.Print();
	}
}
