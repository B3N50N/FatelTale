package entity;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import sdm.SDM;
import tcp.codes;
import logger.Logger;
public class Player 
{
	private int health,attack,direction,defense, maxhealth;
	private Long attackspeed,movespeed;
	private Point location;
	//private boolean active;
	private boolean moving=false,attacking=false;
	private int asset_index;
	private int score,id;
	private Emitter _emitter;
	private Collider _collider;
	private Point _dir = new Point();
	
	private Long _last_move_time;
	
	static final Point[] DIRECTION = new Point[] {
		new Point(-10, 0  ), 
		new Point(10 , 0  ),
		new Point(0  , 10 ),
		new Point(0  , -10)
	};
	
    @SuppressWarnings("rawtypes")
	public Player(int type,Point point,Vector attribute)
	{
		//active=true;
		asset_index=type;
		location=point;
		health= new Integer((int)attribute.get(0));
		attack= new Integer((int)attribute.get(1));
		attackspeed= new Long((Long)attribute.get(2));
		defense= new Integer((int)attribute.get(3));
		movespeed=new Long((Long)attribute.get(4));
		//active=true;
		
		_last_move_time = System.currentTimeMillis();
	}
	
    @SuppressWarnings("rawtypes")
	public Player(int clientno, int type,Point point,Vector attribute, Emitter emitter, Collider collider)
	{
		//active=true;
		id=clientno;
		asset_index=type;
		location=point;
		health= new Integer((int)attribute.get(0));
		maxhealth = health;
		attack= new Integer((int)attribute.get(1));
		attackspeed= new Long((Long)attribute.get(2));
		defense= new Integer((int)attribute.get(3));
		movespeed=new Long((Long)attribute.get(4));
		//active=true;
		
		_last_move_time = System.currentTimeMillis();
		_emitter = emitter;
		_collider = collider;
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
	
	public int getAttack() {return attack;}
	public void playerAttack(){attacking=true;}
	public void playerMove(int newdir)
	{
		assert newdir == codes.MOVEDOWN
               || newdir == codes.MOVELEFT
               || newdir == codes.MOVERIGHT
               || newdir == codes.MOVEUP : "The new direction is invalid";
        assert _dir != null : "_dir is null";
        direction = newdir;
        moving=true;
		_dir.x = DIRECTION[direction].x;
        _dir.y = DIRECTION[direction].y;
	}
	
	public void movingEnd(){moving=false;}
	public void attackingEnd(){attacking=false;}
	
	public void changemove_speed(int dif)
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
	
	public void changeAttackSpeed(Long dif)
	{
		_emitter.changeAttackSpeed(dif);
	}
	
	public void changeScore(int dif)
	{
		if(score+dif<0)
			score=0;
		else
			score+=dif;
	}
	
	public void changePos(int newx,int newy)
	{
		assert newx>=0&&newy>=0:"The position is invalid";
		location.setLocation(newx,newy);
	}
	
	public void beAttacked(int damage) {
		int _damage = damage - defense;
		if ( _damage < 0 ) _damage = 0;
		changeHealth(-_damage);
		changeScore(-damage);
	}
	
	public void setPosition(Point p) {
		assert p != null : "Null Object.";
		location = p;
		_emitter.setPosition(p);
		_collider.setPosition(p);
	}
	
	public void setDirection(Point d) {
		assert d != null : "Null Object.";
		_dir = d;
		_emitter.setDirection(_dir);
		_collider.setDirection(d);
	}
	
	private boolean canMove() {
		if ( System.currentTimeMillis() - _last_move_time >= movespeed ) {
			_last_move_time = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	public void attack() {
		if ( attacking ) {
			// TODO attack...
			_emitter.attack(attack);
		}
	}
	
	public void move() {
		if ( moving && canMove()) {
			if ( SDM.getInstance().isWalkable(location.x + DIRECTION[ direction ].x, location.y + DIRECTION[ direction ].y) ){
				location.x += DIRECTION[ direction ].x;
				location.y += DIRECTION[ direction ].y;
				_dir.x = DIRECTION[ direction ].x;
				_dir.y = DIRECTION[ direction ].y;
			}
		}
	}
	
	public Point getPosition() {
		return location;
	}
	
	public Collider getColiider() {
		return _collider;
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
	// useless comment
}
