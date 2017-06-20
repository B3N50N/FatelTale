package pem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
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
		
		_monster = new ConcurrentHashMap<>();
		_projector = new ConcurrentHashMap<>();
	}
	
	public static synchronized PEM getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new PEM();
		}
		return uniqueInstance;
	}
	
	public void tick() {
		_tmp_monster.clear();
		_tmp_projector.clear();
		
		_delete_monster.clear();
		_delete_projector.clear();
		
		nextPosition();
		checkCollision();
		attacking();
		
		updateData();
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
				if ( _delete_projector.contains( projector.getKey() ) || _delete_monster.contains( monster.getKey() ) ) {
					continue;
				}
				if ( projector.getValue().getAttackerID() < 4 && monster.getValue().getCollider().isCollide( projector.getValue().getCollider() ) ) {
					System.out.println("Collision!");
					// TODO Notice PEM to Delete Projector and change Monster's Health
					addDeleteProjector( projector.getKey() );
				}
			}
		}
	}
	
	public void attacking() {
		for ( Map.Entry<Integer, Monster> e : _monster.entrySet() ) {
			e.getValue().attack();
		}
	}
	
	private void updateData() {
		_monster.putAll(_tmp_monster);
		_projector.putAll(_tmp_projector);
		
		for ( Integer index : _delete_monster ) {
			_monster.remove(index);
		}
		for ( Integer index : _delete_projector ) {
			_projector.remove(index);
		}
	}
	
	public void addTempMonster(Monster m) {
		// TODO get CDC get new Monster ID
		_tmp_monster.put(_monster.size(), m);
	}
	
	public void addTempProjector(Projector p) {
		// TODO get CDC get new Projector ID
		int ID = 0;
		_tmp_projector.put(_projector.size(), p);
	}
	
	private void addDeleteMonster(Integer ID) {
		_delete_monster.add(ID);
	}
	
	private void addDeleteProjector(Integer ID) {
		_delete_projector.add(ID);
	}
	
	public void PrintState() {
		System.out.println("Monster : ");
		for ( Map.Entry<Integer, Monster> m : _monster.entrySet() ) {
			System.out.println("ID : " + m.getKey() );
			m.getValue().Print();
		}
		
		System.out.println("Projector : ");
		for ( Map.Entry<Integer, Projector> p : _projector.entrySet() ) {
			System.out.println("ID : " + p.getKey() );
			p.getValue().Print();
		}
	}
	
	public void putMonster_Test(Monster m) {
		// TODO Will Delete After Complete
		_monster.put(0, m);
	}
}
