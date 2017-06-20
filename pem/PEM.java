package pem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import entity.*;

public class PEM {
	
	private static PEM uniqueInstance;
	
	private Map<Integer, Monster> _tmp_monster;
	private Map<Integer, Projector> _tmp_projector;
	
	private Set<Integer> _delete_monster;
	private Set<Integer> _delete_projector;
	
	public Map<Integer, Monster> _monster; // Only For Test
	public Map<Integer, Projector> _projector; // Only For Test
	
	private PEM() {
		_tmp_monster = new HashMap<>();
		_tmp_projector = new HashMap<>();
		
		_delete_monster = new HashSet<>();
		_delete_projector = new HashSet<>();
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
		}
		
		for ( Map.Entry<Integer, Projector> e : _projector.entrySet() ) {
			e.getValue().nextPosition();
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
	
	public void attacking() {
		for ( Map.Entry<Integer, Monster> e : _monster.entrySet() ) {
			e.getValue().attack();
		}
	}
	
	private void addTempMonster(Monster m) {
		// TODO get CDC get new Monster ID
		int ID = 0;
		_tmp_monster.put(ID, m);
	}
	
	private void addTempProjector(Projector p) {
		// TODO get CDC get new Projector ID
		int ID = 0;
		_tmp_projector.put(ID, p);
	}
	
	private void addDeleteMonster(Integer ID) {
		_delete_monster.add(ID);
	}
	
	private void addDeleteProjector(Integer ID) {
		_delete_projector.add(ID);
	}
}
