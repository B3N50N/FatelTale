package pem;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import entity.*;

public class PEM {
	
	private static PEM uniqueInstance;
	
	private Map<Integer, Monster> _tmp_monster;
	private Map<Integer, Projector> _tmp_projector;
	
	public Map<Integer, Monster> _monster; // Only For Test
	public Map<Integer, Projector> _projector; // Only For Test
	
	private PEM() {
		_tmp_monster = new HashMap<>();
		_tmp_projector = new HashMap<>();
	}
	
	public static synchronized PEM getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new PEM();
		}
		return uniqueInstance;
	}
	
	public void nextPosition() {
		for ( Map.Entry<Integer, Monster> e : _monster.entrySet() ) {
			e.getValue().nextPosition();
			e.getValue().Print();
		}
	}
	
	public void checkCollision() {
		for ( Map.Entry<Integer, Monster> monster : _monster.entrySet() ) {
			for ( Map.Entry<Integer, Projector> projector : _projector.entrySet() ) {
				if ( monster.getValue().getCollider().isCollide( projector.getValue().getCollider() ) ) {
					System.out.println("Collision!");
					// TODO Notice PEM to Delete Projector and change Monster's Health
				}
			}
		}
	}
	
	private boolean isCollision(SphereCollider lhs, SphereCollider rhs) {
		if ( lhs.getPosition().distanceSq(rhs.getPosition()) <= ( lhs.getRadius() * lhs.getRadius() + rhs.getRadius() * rhs.getRadius() ) ) {
			return true;
		}
		return false;
	}
}
