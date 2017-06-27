package entity;

import java.awt.Point;
import logger.Logger;

public class SphereCollider extends Collider{
	
	private double _radius;
	
	public SphereCollider(Point pos, double r) {
		super(pos);
		_radius = r;
	}
	
	public Point getPosition() {
		return _pos;
	}
	
	public double getRadius() {
		return _radius;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Sphere";
	}
	
	public boolean isCollide(Collider c) {
		if ( c.getType() == getType() ) {
			return isCollide((SphereCollider) c);
		}
		return c.isCollide(this);
	}
	
	private boolean isCollide(SphereCollider sc) {
		return ( sc._pos.distanceSq(_pos) >= ( sc._radius * sc._radius + _radius * _radius ) );
	}

	@Override
	public Collider clone() {
		// TODO Auto-generated method stub
		Collider newInstance = new SphereCollider(new Point(_pos), _radius);
		return newInstance;
	}

	@Override
	public void setDirection(Point d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Print() {
		// TODO Auto-generated method stub
		Logger.log("Sphere Collider : ");
		Logger.log("Position : " + _pos);
		Logger.log("Size : " + _radius);
		Logger.log("==========");
	}
}
