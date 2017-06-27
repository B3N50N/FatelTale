package entity;

import java.awt.Point;
import logger.Logger;

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
	
	public void setAttacker(int aID) {
		_ori_projector.setAttacker(aID);
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
		if ( System.currentTimeMillis() - _last_attack_time >= 1000 ) {
			_last_attack_time = System.currentTimeMillis();
			Logger.log("Can Attack.");
			return true;
		}
		return false;
	}
	
	protected abstract void attack(int damage);
	public abstract String getType();
	public abstract Emitter clone();
	
	public void Print() {
		Logger.log("Emitter :");
		Logger.log("Position : " + _pos);
		Logger.log("Direction : " + _dir);
		Logger.log("AS : " + _attack_speed);
		_ori_projector.Print();
		Logger.log("============");
	}
}
