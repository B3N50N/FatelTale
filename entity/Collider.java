package entity;

import java.awt.Point;

public abstract class Collider {
	
	protected Point _pos;
	
	public Collider(Point pos) {
		_pos = pos;
	}
	
	public abstract String getType();
	public abstract boolean isCollide(Collider c);
	public abstract void setDirection(Point d);
	
	public void setPosition(Point p) {
		_pos = p;
	}
	
	public abstract Collider clone();
	public abstract void Print();
	
	public Point getPosition() {
		return _pos;
	}
}
