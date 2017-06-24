package entity;

import java.awt.Point;
import java.util.Map;

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
	private Long _last_move_time, _speed, _last_direction_change;
	
	public Monster(int health, int attack, int defense, Point pos, Point dir, int index, Emitter emitter, Long speed, Collider collider) {
		Init(health, attack, defense, pos, dir, index, emitter, speed, collider);
	}
	
	public Monster(int health, int attack, int defense, int index, Emitter emitter, Long speed, Collider collider) {
		Init(health, attack, defense, collider.getPosition(), emitter.getDirection(), index, emitter, speed, collider);
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
		_last_direction_change = _last_move_time = System.currentTimeMillis();
		_collider = collider;
	}
	
	private boolean canMove() {
		if ( System.currentTimeMillis() - _last_move_time >= _speed ) {
			_last_move_time = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	private boolean canChangeDirection() {
		if ( System.currentTimeMillis() - _last_direction_change >= 100 ) {
			_last_direction_change = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	public void move(Map<Integer, Player> player) {
		if ( canMove() ) {
			changePosition(new Point(_pos.x + _dir.x, _pos.y + _dir.y));
			if ( canChangeDirection() ) {
				Point tmpPoint = null;
				for ( Map.Entry<Integer, Player> p : player.entrySet() ) {
					if ( tmpPoint == null ) {
						tmpPoint = p.getValue().getPosition();
					}
					if ( tmpPoint.distanceSq(_pos) > p.getValue().getPosition().distanceSq(_pos) ) {
						tmpPoint = p.getValue().getPosition();
					}
				}
				
				if ( tmpPoint != null ) {
					Point nextDirection = new Point(tmpPoint.x - _pos.x, tmpPoint.y - _pos.y);
					double dis = nextDirection.distance(0, 0);
					_dir.x = (int) (( nextDirection.getX() / dis ) * 10.0);
					_dir.y = (int) (( nextDirection.getY() / dis ) * 10.0);
				}
			}
		}
	}
	
	public void attack() {
		_emitter.attack();
	}
	
	public void beAttacked(int attack) {
		int damage = attack - _defense;
		changeHealth(-damage);
	}
	
	public void changeHealth(int delta) {
		_health += delta;
		if ( _health < 0 ) _health = 0;
		if ( _health > _max_health ) _health = _max_health;
	}
	
	private void changePosition(Point pos) {
		if ( SDM.getInstance().isWalkable(pos.x, pos.y) ) {
			System.out.println("HI");
			_pos.x = pos.x;
			_pos.y = pos.y;
		}
	}
	
	public void setPosition(Point p) {
		assert p != null : "Null Object.";
		_pos = p;
		_emitter.setPosition(p);
		_collider.setPosition(p);
	}
	
	public void setDirection(Point d) {
		assert d != null : "Null Object.";
		_pos = d;
		_emitter.setPosition(d);
		_collider.setPosition(d);
	}
	
	public Collider getCollider() {
		return _collider;
	}
	
	public void Print() {
		System.out.println("Monster : ");
		System.out.print("Position : ");
		System.out.println(_pos.x + " " + _pos.y);
		System.out.println("Direction : " + _dir);
		System.out.println("Monster's Collider : ");
		_collider.Print();
		_emitter.Print();
		System.out.println("==============");
	}
	
	public String toString() {
		return String.valueOf(_pos.x) + " " + String.valueOf(_pos.y) + " " + String.valueOf(_dir.x) + " " + String.valueOf(_dir.y);
	}
}
