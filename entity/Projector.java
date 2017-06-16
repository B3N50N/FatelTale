package entity;

public abstract class Projector {
	
	protected int _id;
	protected int _x, _y;
	protected int _dir_x, _dir_y;
	protected Long _speed;
	protected Long _last_move_time;
	
	public Projector(int ID, int x, int y, int dx, int dy) {
		_id = ID;
		_x = x;
		_y = y;
		_dir_x = dx;
		_dir_y = dy;
		_last_move_time = System.currentTimeMillis();
	}
	
	protected abstract boolean canMove();
	public abstract void nextPosition();
	public abstract Projector clone();
	
	public int getID() {
		return _id;
	}
}
