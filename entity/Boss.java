package entity;

import logger.Logger;
import tcp.TCPServer;

public class Boss extends Monster {

	public Boss(int health, int attack, int defense, int index, Emitter[] emitter, Long speed, Collider collider) {
		super(health, attack, defense, index, emitter, speed, collider);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isDead() {
		if ( _health <= 0 ) {
			//TCPServer.getServer().
			// TODO call TCP function to end game
			Logger.log("Game End");
			return true;
		}
		return false;
	}
	
	public Monster clone() {
		Collider c = _collider.clone();
		Emitter[] e = new Emitter[ _emitter.length ];
		for (int i=0;i<e.length;i++) {
			e[i] = _emitter[i].clone();
		}
		Monster newInstance = new Boss(_health, _attack, _defense, _asset_index, e, _speed, c);
		return newInstance;	
	}
}
