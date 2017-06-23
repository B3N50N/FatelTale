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
		_pos = p;
	}
	
	public void setDirection(Point d) {
		_dir = d;
	}
	
	public Point getPosition() {
		return _pos;
	}
	
	public Point getDirection() {
		return _dir;
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
}
