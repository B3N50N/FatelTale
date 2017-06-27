package entity;

import java.awt.Point;

import cdc.CDC;
import pem.PEM;

public class DirectlyEmitter extends Emitter {
	
	public DirectlyEmitter(Long as, Point dir, Point pos, Projector op) {
		super(as, dir, pos, op);
	}

	@Override
	protected void attack(int damage) {
		// TODO Auto-generated method stub
		if ( canAttack() ) {
			Projector newInstance = _ori_projector.clone();
			newInstance.setAttacker(_ori_projector.getAttackerID());
			newInstance.setDamaage(damage);
			// TODO PEM's function to add new projector
			//PEM.getInstance().addTempProjector(newInstance);
			CDC.getInstance().addProjector(newInstance);
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
