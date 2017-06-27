package entity;

import java.awt.Point;

import pem.PEM;

public class AngleEmitter extends Emitter {
	
	private double[] _angle;
	private double _end_angle, _start_angle;
	
	public AngleEmitter(Long as, Point dir, Point pos, Projector op, double start_angle, double end_angle, int time) {
		super(as, dir, pos, op);
		// TODO Auto-generated constructor stub
		_end_angle = end_angle;
		_start_angle = start_angle;
		if ( _end_angle < _start_angle ) {
			double tmp = _start_angle;
			_start_angle = _end_angle;
			_end_angle = tmp;
		}
		
		double _total_angle = _end_angle - _start_angle;
		double _delta = _total_angle / ( time - 1 );
		_angle = new double[time];
		
		double _unit_angle = _start_angle;
		for (int i=0;i<time;i++) {
			_angle[i] = _unit_angle;
			_unit_angle += _delta;
		}
	}

	@Override
	public String getType() {
		return "AngleEmitter";
	}

	@Override
	public Emitter clone() {
		// TODO Auto-generated method stub
		assert _angle != null : "Null Object.";
		Projector p = _ori_projector.clone();
		Emitter newInstance = new AngleEmitter(_attack_speed, p.getDirection(), p.getPosition(), p, _start_angle, _end_angle, _angle.length);
		return newInstance;
	}

	@Override
	protected void attack(int damage) {
		// TODO Auto-generated method stub
		if ( canAttack() ) {
			assert _angle != null : "Null Object.";
			double _main_angle = getAngle(_dir);
			
			for (int i=0;i<_angle.length;i++) {
				double _now_angle = _main_angle + _angle[i];
				double x = 10 * Math.cos( Math.toRadians(_now_angle) ), y = 10 * Math.sin( Math.toRadians(_now_angle) );
				Point _new_dir = new Point((int)x, (int)y);
				
				Projector newInstance = _ori_projector.clone();
				newInstance.setAttacker(_ori_projector.getAttackerID());
				newInstance.setDamaage(damage);
				_ori_projector.setDirection(_new_dir);
				PEM.getInstance().addTempProjector(newInstance);
			}
		}
	}
	
	private double getAngle(Point d) {
		if ( d.x == 0 ) {
			if ( d.y > 0 ) 
				return 90;
			return 270;
		}
		if ( d.y == 0 ) {
			if ( d.x > 0 ) {
				return 0;
			}
			return 180;
		}
		
		return Math.toDegrees( Math.atan( d.y / d.x ) );
	}
	
	public void Print() {
		System.out.println(_angle.length);
		for (int i=0;i<_angle.length;i++) {
			System.out.print(_angle[i] + " ");
		}
	}
}
