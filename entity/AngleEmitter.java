package entity;

import java.awt.Point;

public class AngleEmitter extends Emitter {

	public AngleEmitter(Long as, Point dir, Point pos, Projector op) {
		super(as, dir, pos, op);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return "AngleEmitter";
	}

	@Override
	public Emitter clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
