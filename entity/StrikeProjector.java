package entity;

import java.awt.Point;

import sdm.SDM;

public class StrikeProjector extends Projector {


	public StrikeProjector(Point pos, Point dir, Collider collider, Long speed, int aID, int asset_index) {
		super(pos, dir, collider, speed, aID, asset_index);
		// TODO Auto-generated constructor stub
	}
	
	public StrikeProjector(Point dir, Collider collider, Long speed, int aID, int asset_index) {
		super(dir, collider, speed, aID, asset_index);
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
	
	public void move() {
		if ( canMove() ) {
			_pos.x += _dir.x;
			_pos.y += _dir.y;
			
			_collider.getPosition().setLocation(_pos.x, _pos.y);
		}
	}

	@Override
	public Projector clone() {
		// TODO Auto-generated method stub
		Collider c = _collider.clone();
		Projector newInstance = new StrikeProjector(c._pos, new Point(_dir), c, _speed, _attacker_ID, _asset_index);
		newInstance.setDirection(new Point(_dir));
		newInstance.setPosition(new Point(_pos));
		System.out.println("Born In " + newInstance.getPosition().x + " + " +  newInstance.getPosition().y + " " + _dir);
		return newInstance;
	}

	@Override
	public String getType() {
		return "StrikeProjector";
	}
}
