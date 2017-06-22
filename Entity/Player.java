package entity;
import java.awt.Point;
import java.util.Vector;

import tcp.codes;
public class Player 
{
	private int health,attack,direction,defense;
	private int attackspeed,movespeed;
	private Point location;
	//private boolean active;
	private boolean moving=false,attacking=false;
	private int asset_index;
	//private collider;
	//static final int west=0,north=1,east=2,south=3;
	public Player(int type,Point point,Vector attribute)
	{
		//active=true;
		asset_index=type;
		location=point;
		health=(int)attribute.get(0);
		attack=(int)attribute.get(1);
		attackspeed=(int)attribute.get(2);
		defense=(int)attribute.get(3);
		movespeed=(int)attribute.get(4);
		//active=true;
	}
	public String dirvaluetoString(int dir)
	{
		if(dir==codes.MOVELEFT) return "west";
		else if(dir==codes.MOVEUP) return "north";
		else if(dir==codes.MOVERIGHT) return "east";
		else return "south";
	}
	public boolean isDead()
	{
		if(health<=0)
			return true;
		else
			return false;
	}
	public void changeHealth(int dif)
	{
		if(health+dif<0)
			health=0;
		else
			health+=dif;
	}
	public void playerMove(int newdir)
	{
		assert newdir!=codes.MOVEDOWN&&newdir!=codes.MOVELEFT&&newdir!=codes.MOVERIGHT&&newdir!=codes.MOVEUP:"The new direction is invalid";
		moving=true;
		direction=newdir;
	}
	public void playerAttack()
	{
		attacking=true;
	}
	public void movingEnd(){moving=false;}
	public void attackingEnd(){attacking=false;}
	public void changemove_speed(int dif)
	{
		if(movespeed+dif<0)
			movespeed=0;
		else
			movespeed+=dif;
	}
	public void changeDefense(int dif)
	{
		if(defense+dif<0)
			defense=0;
		else
			defense+=dif;
	}
	public void changeAttack(int dif)
	{
		if(attack+dif<0)
			attack=0;
		else
			attack+=dif;
	}
	public void changeAttackSpeed(int dif)
	{
		if(attackspeed+dif<0)
			attackspeed=0;
		else
			attackspeed+=dif;
	}
	public void changePos(int newx,int newy)
	{
		assert newx>=0&&newy>=0:"The position is invalid";
		location.setLocation(newx,newy);
	}
	public String toString()
	{
		String str="";
		str+=String.valueOf(asset_index);
		str+=" ";
		str+=String.valueOf(health);
		str+=" ";
		str+=dirvaluetoString(direction);
		str+=" ";
		str+=String.valueOf(attack);
		str+=" ";
		str+=String.valueOf(attackspeed);
		str+=" ";
		str+=String.valueOf(movespeed);
		str+=" ";
		str+=String.valueOf(defense);
		str+=" ";
		str+=String.valueOf(location.getX());
		str+=" ";
		str+=String.valueOf(location.getY());
		str+=" ";
		return str;
	}
	/*public boolean isActive()
	{
		return active;
	}*/
}
