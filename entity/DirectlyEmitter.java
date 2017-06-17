package entity;

import java.awt.Point;

public class DirectlyEmitter extends Emitter {
	
	public DirectlyEmitter(Long as, Point dir, Point pos, Projector op) {
		super(as, dir, pos, op);
	}

	@Override
	protected void attack() {
		// TODO Auto-generated method stub
		if ( canAttack() ) {
			Projector newInstance = _ori_projector.clone();
			// TODO CDC's function to add new projector
			
		}
	}
}
