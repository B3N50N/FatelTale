package entity;

import java.awt.Point;

import sdm.SDM;

public class Monster {
	
	private int _max_health;
	private int _health;
	private Point _pos, _dir;
	private Collider _collider;
	
	private int _asset_index;
	
	private Emitter _emitter;
	private Long _last_move_time, _speed;
	
	public Monster(int h, Point pos, Point dir, int index, Emitter emitter, Long speed, SphereCollider collider) {
		_health = _max_health = h;
		_pos = pos;
		_dir = dir;
		_asset_index = index;
		_emitter = emitter;
		_speed = speed;
		_last_move_time = System.currentTimeMillis();
		_collider = collider;
	}
	
	private boolean canMove() {
		if ( System.currentTimeMillis() - _last_move_time >= _speed ) {
			_last_move_time = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	public void nextPosition() {
		if ( canMove() ) {
			changePosition(new Point(_pos.x + _dir.x, _pos.y + _dir.y));
		}
	}
	
	public void attack() {
		_emitter.attack();
	}
	
	public void changeHealth(int delta) {
		_health += delta;
		if ( _health < 0 ) _health = 0;
		if ( _health > _max_health ) _health = _max_health;
	}
	
	private void changePosition(Point pos) {
		if ( SDM.getInstance().isWalkable(pos.x, pos.y) ) {
			_pos = pos; 
		}
	}
	
	public Collider getCollider() {
		return _collider;
	}
	
	public void Print() {
		System.out.println("Monster : ");
		System.out.print("Position : ");
		System.out.println(_pos.x + " " + _pos.y);
	}
}
