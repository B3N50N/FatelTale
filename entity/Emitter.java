package entity;

import java.awt.Point;

public abstract class Emitter {
	
	protected Long _attack_speed;
	protected Point _dir, _pos;
	protected Projector _ori_projector;
	protected Long _last_attack_time;
	
	public Emitter(Long as, Point dir, Point pos, Projector op) {
		_attack_speed = as;
		_dir = dir;
		_pos = pos;
		_ori_projector = op;
		_last_attack_time = System.currentTimeMillis();
	}
	
	public void setPosition(Point p) {
		assert p != null : "Null Object.";
		_pos = p;
		_ori_projector.setPosition(p);
	}
	
	public void setDirection(Point d) {
		assert d != null : "Null Object.";
		_dir = d;
		_ori_projector.setDirection(d);
	}
	
	public Point getPosition() {
		return _pos;
	}
	
	public Point getDirection() {
		return _dir;
	}
	
	public void changeAttackSpeed(Long delta) {
		_attack_speed += delta;
		if ( _attack_speed < 10L ) {
			_attack_speed = 10L;
		}
	}
	
	protected boolean canAttack() {
		if ( System.currentTimeMillis() - _last_attack_time >= _attack_speed ) {
			_last_attack_time = System.currentTimeMillis();
			System.out.println("Can Attack.");
			return true;
		}
		return false;
	}
	
	protected abstract void attack();
	public abstract String getType();
	public abstract Emitter clone();
	
	public void Print() {
		System.out.println("Emitter :");
		System.out.println("Position : " + _pos);
		System.out.println("Direction : " + _dir);
		System.out.println("AS : " + _attack_speed);
		_ori_projector.Print();
		System.out.println("============");
	}
}
