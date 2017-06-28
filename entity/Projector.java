package entity;

import java.awt.Point;
import logger.Logger;

public abstract class Projector {
	
	protected Point _pos, _dir;
	protected Long _speed;
	protected Long _last_move_time, _generation_time, _max_life_time;
	protected int _attacker_ID;
	protected int _damage;
	protected int _asset_index;
	
	protected Collider _collider;
	
	public Projector(Point pos, Point dir, Collider collider, Long speed, int aID, int asset_index) {
		Init(pos, dir, collider, speed, aID, asset_index);
	}
	
	public Projector(Point dir, Collider collider, Long speed, int aID, int asset_index) {
		Init(collider.getPosition(), dir, collider, speed, aID, asset_index);
	}
	
	private void Init(Point pos, Point dir, Collider collider, Long speed, int aID, int asset_index) {
		_pos = pos;
		_dir = dir;
		_last_move_time = System.currentTimeMillis();
		_generation_time = System.currentTimeMillis();
		_max_life_time = 2000L;
		_collider = collider;
		_speed = speed;
		_attacker_ID = aID;
		_asset_index = asset_index;
	}
	
	protected abstract boolean canMove();
	public abstract void move();
	public abstract Projector clone();
	public abstract String getType();
	
	public void setCollider(Collider c) {
		assert c != null : "Null Object.";
		_collider = c;
		_pos = _collider.getPosition();
	}
	
	public void setDirection(Point d) {
		assert d != null : "Null Object.";
		_dir = d;
	}
	
	public void setPosition(Point p) {
		assert p != null : "Null Object.";
		_pos = p;
	}
	
	public void setSpeed(Long speed) {
		_speed = speed;
	}
	public void setAttacker(int aID) {
		_attacker_ID = aID;
	}
	
	public void setDamaage(int damage) {
		_damage = damage;
	}
	
	public Collider getCollider() {
		return _collider;
	}
	
	public Long getSpeed() {
		return _speed;
	}
	
	public int getAttackerID() {
		return _attacker_ID;
	}
	
	public Point getPosition() {
		return _pos;
	}
	
	public Point getDirection() {
		return _dir;
	}
	
	public int getDamage() {
		return _damage;
	}
	
	public boolean isAlive() {
		return ( System.currentTimeMillis() - _generation_time < _max_life_time );
	}
	
	public void Print() {
		Logger.log("Projector : ");
		Logger.log("Position : " + _pos.x + " " + _pos.y);
		Logger.log("======================");
	}
	
	public String toString() {
		return String.valueOf(_pos.x) + " " + String.valueOf(_pos.y) + " " + String.valueOf(_dir.x) + " " + String.valueOf(_dir.y) + " " +
			   String.valueOf(_asset_index) + " ";
	}
}
