package entity;

import java.awt.Point;

import pem.PEM;

public class DirectlyEmitter extends Emitter {
	
	public DirectlyEmitter(Long as, Point dir, Point pos, Projector op) {
		super(as, dir, pos, op);
	}

	@Override
	protected void attack() {
		// TODO Auto-generated method stub
		if ( canAttack() ) {
			Projector newInstance = _ori_projector.clone();
			// TODO PEM's function to add new projector
			PEM.getInstance().addTempProjector(newInstance);
		}
	}

	@Override
	public String getType() {
		return "DirectlyEmitter";
	}

	@Override
	public Emitter clone() {
		Projector p = _ori_projector.clone();
		Emitter newInstance = new DirectlyEmitter(_attack_speed, p.getDirection(), p.getPosition(), p);
		return newInstance;
	}

}
