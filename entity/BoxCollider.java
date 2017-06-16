package entity;

public class BoxCollider {
	
	private Integer _x, _y;
	private Float _dir_x, _dir_y;
	private int _width, _height;
	
	public BoxCollider(Integer x, Integer y, Float dx, Float dy) {
		_x = x;
		_y = y;
		_dir_x = dx;
		_dir_y = dy;
	}
	
	public int getX() {
		return _x;
	}
	
	public int getY() {
		return _y;
	}
	
	public float getAngle() {
		return (float) Math.toDegrees(Math.atan(_dir_y / _dir_x));
	}
}
