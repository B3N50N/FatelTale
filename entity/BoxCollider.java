package entity;

import java.awt.Point;

public class BoxCollider {
	
	private Point _pos, _dir;
	private int _width, _height;
	
	public BoxCollider(Point pos, Point dir, int width, int height) {
		_pos = pos;
		_dir = dir;
		_width = width;
		_height = height;
	}
	
	public Point getPosition() {
		return _pos;
	}
	
	public Point getDirection() {
		return _dir;
	}
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public double getAngle() {
		return Math.toDegrees(Math.atan(_dir.y / _dir.x));
	}
}
