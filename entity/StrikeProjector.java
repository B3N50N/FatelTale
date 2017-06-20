package entity;

import java.awt.Point;

import sdm.SDM;

public class StrikeProjector extends Projector {

	public StrikeProjector(Point pos, Point dir, Collider collider, Long speed) {
		super(pos, dir, collider, speed);
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
			_pos.x += _dir.x;
			_pos.y += _dir.y;
		}
		
		if ( SDM.getInstance().isOutofBound(_pos.x, _pos.y) ) {
			// TODO Delete this Projector, will call CDC's Delete Function
		}
	}

	@Override
	public Projector clone() {
		// TODO Auto-generated method stub
		Projector newInstance = new StrikeProjector(new Point(_pos), new Point(_dir), super.getCollider().clone(), super.getSpeed() );
		return newInstance;
	}
}
