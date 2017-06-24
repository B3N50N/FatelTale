package entity;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import sdm.SDM;
import tcp.codes;
public class Player 
{
	private int health,attack,direction,defense,maxhealth;
	private Long movespeed,attackspeed;
	private Point location;
	private boolean moving=false,attacking=false;
	private int score,id;
	private int asset_index;
	private Long lastmovetime;
	//private collider;
	//static final int west=0,north=1,east=2,south=3;
	//private boolean active;
	private static Map<Integer,Point> dirtovector;
	public Player(int clientno,int type,Point point,Vector attribute)
	{
		//active=true;
		id=clientno;
		asset_index=type;
		location=point;
		health=(int)attribute.get(0);
		maxhealth=health;
		attack=(int)attribute.get(1);
		attackspeed=(Long)attribute.get(2);
		defense=(int)attribute.get(3);
		movespeed=(Long)attribute.get(4);
		if(dirtovector==null)
		{
			dirtovector=new HashMap<>();
			dirtovector.put(codes.MOVELEFT,new Point(-10,0));
			dirtovector.put(codes.MOVEUP,new Point(0,-10));
			dirtovector.put(codes.MOVERIGHT,new Point(10,0));
			dirtovector.put(codes.MOVEDOWN,new Point(0,10));
		}
		lastmovetime=System.currentTimeMillis();
		//active=true;
	}
	public int getAttack() {return attack;}
	public void playerAttack(){attacking=true;}
	public void playerMove(int newdir)
	{
		assert newdir!=codes.MOVEDOWN&&newdir!=codes.MOVELEFT&&newdir!=codes.MOVERIGHT&&newdir!=codes.MOVEUP:"The new direction is invalid";
		moving=true;
		direction=newdir;
	}
	public void movingEnd(){moving=false;}
	public void attackingEnd(){attacking=false;}
	
	public void changeHealth(int dif)
	{
		if(health+dif<0)
			health=0;
		else if(health+dif>=maxhealth)
			health=maxhealth;
		else
			health+=dif;
	}
	public void changePos() 
	{
		if ( moving && canMove()) 
		{
			if ( SDM.getInstance().isWalkable(location.x + dirtovector.get(direction).x, location.y + dirtovector.get(direction).y) )
			{
				location.x += dirtovector.get(direction).x;
				location.y += dirtovector.get(direction).y;
			}
		}
	}
	public void changeMoveSpeed(int dif)
	{
		if(movespeed+dif<0)
			movespeed=0L;
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
			attackspeed=0L;
		else
			attackspeed+=dif;
	}
	public void changeScore(int dif)
	{
		if(score+dif<0)
			score=0;
		else
			score+=dif;
	}
	
	private boolean canMove() 
	{
		if ( System.currentTimeMillis() - lastmovetime >= movespeed ) 
		{
			lastmovetime = System.currentTimeMillis();
			return true;
		}
		return false;
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
	
	public String toString()
	{
		String str="";
		str+="Player ";
		str+=String.valueOf(id);
		str+=" ";
		str+=String.valueOf(asset_index);
		str+=" ";
		str+=String.valueOf(health);
		str+=" ";
		str+=String.valueOf(maxhealth);
		str+=" ";
		str+=dirvaluetoString(direction);
		str+=" ";
		str+=String.valueOf(score);
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
