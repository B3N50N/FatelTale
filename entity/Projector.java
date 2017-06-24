package entity;
import entity.Collider;
import java.awt.Point;

public abstract class Projector {
	
	protected Point _pos, _dir;
	protected Long _speed;
	protected Long _last_move_time;
	
	private Collider _collider;
	
	public Projector(Point pos, Point dir, Collider collider, Long speed) {
		_pos = pos;
		_dir = dir;
		_last_move_time = System.currentTimeMillis();
		_collider = collider;
		_speed = speed;
	}
	
	protected abstract boolean canMove();
	public abstract void nextPosition();
	public abstract Projector clone();
	
	public Collider getCollider() {
		return _collider;
	}
	
	public Long getSpeed() {
		return _speed;
	}
	
	public void Print() {
		System.out.println("Projector : ");
		System.out.println("Position : " + _pos.x + " " + _pos.y);
		System.out.println("======================");
	}
}