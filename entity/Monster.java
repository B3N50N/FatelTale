package entity;

import java.awt.Point;

import sdm.SDM;

public class Monster {
	
	private int _max_health;
	private int _health;
	private int _attack;
	private int _defense;
	private Point _pos, _dir;
	private Collider _collider;
	
	private int _asset_index;
	
	private Emitter _emitter;
	private Long _last_move_time, _speed;
	
	public Monster(int health, int attack, int defense, Point pos, Point dir, int index, Emitter emitter, Long speed, Collider collider) {
		Init(health, attack, defense, pos, dir, index, emitter, speed, collider);
	}
	
	public Monster(int health, int attack, int defense, int index, Emitter emitter, Long speed, Collider collider) {
		Init(health, attack, defense, emitter.getPosition(), emitter.getDirection(), index, emitter, speed, collider);
	}
	
	private void Init(int health, int attack, int defense, Point pos, Point dir, int index, Emitter emitter, Long speed, Collider collider) {
		_health = _max_health = health;
		_attack = attack;
		_defense = defense;
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
	
	public void beAttacked(int attack) {
		int damage = attack - _defense;
		
	}
	
	public void changeHealth(int delta) {
		_health += delta;
		if ( _health < 0 ) _health = 0;
		if ( _health > _max_health ) _health = _max_health;
	}
	
	private void changePosition(Point pos) {
		if ( SDM.getInstance().isWalkable(pos.x, pos.y) ) {
			System.out.println("HI");
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
