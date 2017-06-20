package entity;

import java.awt.Point;

import sdm.SDM;

public class StrikeProjector extends Projector {


	public StrikeProjector(Point pos, Point dir, Collider collider, Long speed, int aID) {
		super(pos, dir, collider, speed, aID);
		// TODO Auto-generated constructor stub
	}
	
	public StrikeProjector(Point dir, Collider collider, Long speed, int aID) {
		super(dir, collider, speed, aID);
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
		Collider c = _collider.clone();
		Projector newInstance = new StrikeProjector(c.getPosition(), (Point)_dir.clone(), c, _speed, _attacker_ID);
		return newInstance;
	}
}
