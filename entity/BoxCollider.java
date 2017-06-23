package entity;

import java.awt.Point;

public class BoxCollider extends Collider{
	
	private int _width, _height;
	private Point _dir;
	
	private double[][] _vector;
	
	public BoxCollider(Point pos, Point dir, int width, int height) {
		super(pos);
		// TODO Auto-generated constructor stub
		_dir = dir;
		_width = width;
		_height = height;
	}
	
	public Point getDirection() {
		return _dir;
	}
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public double getAngle() {
		if ( _dir.x == 0 ) {
			return 0.5;
		}
		return Math.atan(_dir.y / _dir.x);
	}

	@Override
	public String getType() {
		return "Box";
	}

	@Override
	public boolean isCollide(Collider c) {
		// TODO Auto-generated method stub
		if ( c.getType() != getType() ) {
			return isCollide((SphereCollider)c);
		}
		return isCollide((BoxCollider)c);
	}
	
	private boolean isCollide(SphereCollider sc) {
		
		double _circle_x = sc._pos.x, _circle_y = sc._pos.y;
		double _rotation = getAngle();
		
		double _x, _y;
		_x = Math.cos(_rotation) * ( _circle_x - _pos.x ) - Math.sin(_rotation) * ( _circle_y - _pos.y );
		_y = Math.sin(_rotation) * ( _circle_x - _pos.x ) + Math.cos(_rotation) * ( _circle_y - _pos.y );
		
		double cX, cY;
		double hH = _height / 2, hW = _width / 2;
		
		if ( _x < -hW ) {
			cX = -hW;
		}
		else if ( _x > hW ) {
			cX = hW;
		}
		else {
			cX = _x;
		}
		
		if ( _y < -hH ) {
			cY = -hH;
		}
		else if ( _y > hH ) {
			cY = hH;
		}
		else {
			cY = _y;
		}
		
		if ( ( cX * cX + cY * cY ) < sc.getRadius() ) {
			return true;
		}
		
		return false;
	}
	
	private boolean isCollide(BoxCollider bc) {
		double[] distanceVector = new double[] { 
			_pos.x - bc._pos.x, 
			_pos.y - bc._pos.y
		};
		
		setVector();
		bc.setVector();
		
		for (int i=0;i<2;i++) {
			if ( getProjectRadius(_vector[i]) + bc.getProjectRadius(_vector[i]) <= dot(distanceVector, _vector[i]) ) {
				return false;
			}
			if ( getProjectRadius(bc._vector[i]) + bc.getProjectRadius(bc._vector[i]) <= dot(distanceVector, bc._vector[i]) ) {
				return false;
			}
		}
		
		return true;
	}
	
	private double dot(double[] v1, double[] v2) {
		return Math.abs( v1[0] * v2[0] + v1[1] * v2[1] );
	}
	
	private void setVector() {
		double _rotation = getAngle();
		double _cos = Math.cos(_rotation), _sin = Math.sin(_rotation);
		_vector = new double[][] {
			{ _cos, _sin }, 
			{ -_sin, _cos }
		};
	}
	
	private double getProjectRadius(double[] vector) {
		return ( _width * dot(_vector[0], vector) / 2 + _height * dot(_vector[1], vector ) / 2 );
	}

	@Override
	public Collider clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
