import java.awt.Point;

import entity.*;
import sdm.SDM;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SDM.getInstance().readMap("./resource/Map/Map001.txt");
		Point pos = new Point(10, 10);
		Point dir = new Point(0, 1);
		Collider c = new SphereCollider(pos, 10);
		Projector p = new StrikeProjector(pos, dir, c, 1000L);
		Emitter e = new DirectlyEmitter(1000L, dir, pos, p);
		Monster m = new Monster(10, 50, 100, pos, dir, 0, e, 2000L, c);
		m.Print();
		while ( true ) {
			m.nextPosition();
			m.attack();
		}
	}

}