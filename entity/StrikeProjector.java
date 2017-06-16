package entity;

import sdm.SDM;

public class StrikeProjector extends Projector {

	
	public StrikeProjector(int ID, int x, int y, int dx, int dy) {
		super(ID, x, y, dx, dy);
		// TODO Auto-generated constructor stub
	}

	protected boolean canMove() {
		Long nowTime = System.currentTimeMillis();
		if ( nowTime - _last_move_time >= _speed ) {
			_last_move_time = nowTime;
			return true;
		}
		return false;
	}
	
	public void nextPosition() {
		if ( canMove() ) {
			_x += _dir_x;
			_y += _dir_y;
		}
		
		if ( SDM.getInstance().isOutofBound(_x, _y) ) {
			// TODO Delete this Projector, will call CDC's Delete Function
		}
	}

	@Override
	public Projector clone() {
		// TODO Auto-generated method stub
		Projector newInstance = new StrikeProjector(_id /*Will use CDC's Function*/, _x, _y, _dir_x, _dir_y);
		return newInstance;
	}
}
