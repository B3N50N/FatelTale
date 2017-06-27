package pem;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cdc.CDC;
import entity.*;
import tcp.TCPServer;
import tcp.codes;
import logger.Logger;

public class PEM {
	
	private static PEM uniqueInstance;
	
	private Map<Integer, Monster> _tmp_monster;
	private Map<Integer, Projector> _tmp_projector;
	
	private Set<Integer> _delete_monster;
	private Set<Integer> _delete_projector;
	
	public Map<Integer, Player> _player;
	public Map<Integer, Monster> _monster; // Only For Test
	public Map<Integer, Projector> _projector; // Only For Test
	public Map<Integer, Item> _item;
	
	private PEM() {
		_tmp_monster = new HashMap<>();
		_tmp_projector = new HashMap<>();
		
		_delete_monster = new HashSet<>();
		_delete_projector = new HashSet<>();
		
		_player = CDC.getInstance().getPlayer();
		_monster = CDC.getInstance().getMonster();
		_projector = CDC.getInstance().getProjector();
		_item = CDC.getInstance().getItem();
		
//		MonsterInfo.getInstance().loadMonsterData("./resource/Data/Monster/Mode1/");
//		Monster m = MonsterInfo.getInstance().getRandomMonster();
		
//		m.setPosition(new Point(50, 50));
//		m.setDirection(new Point(10, 0));
//		m.Print();
//		_monster.put(CDC.getInstance().getMonsterNewId(), m);
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
		
		_player = CDC.getInstance().getPlayer();
		_monster = CDC.getInstance().getMonster();
		_projector = CDC.getInstance().getProjector();
		_item = CDC.getInstance().getItem();
		
		nextPosition();
		checkCollision();
		//attacking();
		
		updateData();
	}
	
	public void nextPosition() {
		
		for ( Map.Entry<Integer, Player> e : _player.entrySet() ) {
			e.getValue().move();
		}
		
		for ( Map.Entry<Integer, Monster> e : _monster.entrySet() ) {
			e.getValue().move(_player);
		}
		
		for ( Map.Entry<Integer, Projector> e : _projector.entrySet() ) {
			e.getValue().move();
		}
	}
	
	public void checkCollision() {

		for ( Map.Entry<Integer, Player> player : _player.entrySet() ) {
			for ( Map.Entry<Integer, Projector> projector : _projector.entrySet() ) {
				if ( projector.getValue().getAttackerID() < 4 ) {
					if ( projector.getValue().getCollider().isCollide( player.getValue().getColiider() ) ) {
						player.getValue().beAttacked( projector.getValue().getDamage() );
						// TODO remove projector
						deleteProjector( projector.getKey() );
					}
				}
			}
			
			for ( Map.Entry<Integer, Monster> monster : _monster.entrySet() ) {
				if ( monster.getValue().getCollider().isCollide( player.getValue().getColiider() ) ) {
					player.getValue().beAttacked( monster.getValue().getAttack() );
				}
			}
		}
		
		for ( Map.Entry<Integer, Monster> monster : _monster.entrySet() ) {
			for ( Map.Entry<Integer, Projector> projector : _projector.entrySet() ) {
				if ( projector.getValue().getAttackerID() < 4 && monster.getValue().getCollider().isCollide( projector.getValue().getCollider() ) ) {
				    Logger.log("Collision!");
					// TODO Notice PEM to Delete Projector and change Monster's Health
					monster.getValue().beAttacked( projector.getValue().getDamage() );
					_player.get( projector.getValue().getAttackerID() ).changeScore( projector.getValue().getDamage() );
					deleteProjector( projector.getKey() );
					
					if ( !monster.getValue().isDead() ) {
						_player.get( projector.getValue().getAttackerID() ).changeScore( 30 );
						deleteMonster( monster.getKey() );
						break;
					}
				}
			}
		}
	}
	
	public void attacking() {
		for ( Map.Entry<Integer, Player> e : _player.entrySet() ) {
			e.getValue().attack();
		}
		for ( Map.Entry<Integer, Monster> e : _monster.entrySet() ) {
			e.getValue().attack();
		}
	}
	
	private void updateData() {
		
		for ( Map.Entry<Integer, Projector> e : _tmp_projector.entrySet() ) {
			TCPServer.getServer().createObject(e.getKey(), codes.PROJECTOR);
		}
		for ( Map.Entry<Integer, Monster> e : _tmp_monster.entrySet() ) {
			TCPServer.getServer().createObject(e.getKey(), codes.MONSTER);
		}
		_monster.putAll(_tmp_monster);
		_projector.putAll(_tmp_projector);
		
		/*
		for ( Integer index : _delete_monster ) {
			_monster.remove(index);
		}
		for ( Integer index : _delete_projector ) {
			_projector.remove(index);
		}
		*/
	}
	
	public void addTempMonster(Monster m) {
		// TODO get CDC get new Monster ID
		int ID = CDC.getInstance().getMonsterNewId();
		_tmp_monster.put(ID, m);
		TCPServer.getServer().createObject(ID, codes.MONSTER);
	}
	
	public void addTempProjector(Projector p) {
		// TODO get CDC get new Projector ID
		int ID = CDC.getInstance().getProjectorId();
		_tmp_projector.put(ID, p);
		// TODO call TCP add() function
		TCPServer.getServer().createObject(ID, codes.PROJECTOR);
	}
	
	private void deleteMonster(Integer ID) {
		// TODO call TCP delete() function
		_monster.remove(ID);
		TCPServer.getServer().deleteObject(ID, codes.MONSTER);
	}
	
	private void deleteProjector(Integer ID) {
		// TODO call TCP delete() function
		_projector.remove(ID);
		TCPServer.getServer().deleteObject(ID, codes.PROJECTOR);
	}
	
	public void PrintState() {
		
		for ( Map.Entry<Integer, Player> p : _player.entrySet() ) {
			Logger.log(p.getValue().toString());
		}
		Logger.log("Monster : ");
		for ( Map.Entry<Integer, Monster> m : _monster.entrySet() ) {
			Logger.log(m.getKey() + " : " + m.getValue().toString() );
			//m.getValue().Print();
		}
		/*
		Logger.log("Projector : ");
		for ( Map.Entry<Integer, Projector> p : _projector.entrySet() ) {
			Logger.log("ID : " + p.getKey() );
			p.getValue().Print();
		}
		*/
	}
	
	public void putMonster_Test(Monster m) {
		// TODO Will Delete After Complete
		_monster.put(0, m);
	}
}
