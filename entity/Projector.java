package entity;

import java.awt.Point;

public abstract class Projector {
	
	protected Point _pos, _dir;
	protected Long _speed;
	protected Long _last_move_time;
	protected int _attacker_ID;
	
	protected Collider _collider;
	
	public Projector(Point pos, Point dir, Collider collider, Long speed, int aID) {
		Init(pos, dir, collider, speed, aID);
	}
	
	public Projector(Point dir, Collider collider, Long speed, int aID) {
		Init(collider.getPosition(), dir, collider, speed, aID);
	}
	
	private void Init(Point pos, Point dir, Collider collider, Long speed, int aID) {
		assert aID >= 0 : "Invalid Index.";
		_pos = pos;
		_dir = dir;
		_last_move_time = System.currentTimeMillis();
		_collider = collider;
		_speed = speed;
		_attacker_ID = aID;
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
	
	public void setDirection(Point p) {
		assert p != null : "Null Object.";
		_dir = p;
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
	
	public void Print() {
		System.out.println("Projector : ");
		System.out.println("Position : " + _pos.x + " " + _pos.y);
		System.out.println("======================");
	}
}
