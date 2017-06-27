package entity;

import tcp.TCPServer;

public class Boss extends Monster {

	public Boss(int health, int attack, int defense, int index, Emitter[] emitter, Long speed, Collider collider) {
		super(health, attack, defense, index, emitter, speed, collider);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isDead() {
		if ( _health <= 0 ) {
			//TCPServer.getServer().
			return true;
		}
		return false;
	}
}
