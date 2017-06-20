package entity;

import java.awt.Point;

public abstract class Collider {
	
	protected Point _pos;
	
	public Collider(Point pos) {
		_pos = pos;
	}
	
	public abstract String getType();
	public abstract boolean isCollide(Collider c);
	
	public abstract Collider clone();
	
	public Point getPosition() {
		return _pos;
	}
}
