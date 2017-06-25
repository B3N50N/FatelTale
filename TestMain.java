import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import cdc.CDC;
import entity.*;
import pem.PEM;
import sdm.SDM;

public class TestMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SDM.getInstance().readMap("./resource/Map/Map001.txt");
		Vector<String> v = CDC.getInstance().getUpdatInfo();
		for(String s : v) {
			System.out.println(s);
		}
		/*
		FileReader fr = new FileReader("./resource/Data/EmitterTest.txt");
		BufferedReader br = new BufferedReader(fr);
		Emitter e = EmitterInfo.getInstance().getEmitter(br);
		
		e.Print();
		e.setPosition(new Point(100, 2000));
		e.Print();
		*/
		/*
		MonsterInfo.getInstance().loadMonsterData("./resource/Data/Monster/Mode1/");
		Monster m = MonsterInfo.getInstance().getRandomMonster();
		m.Print();
		m.setPosition(new Point(100, 30));
		m.Print();
		*/
		/*
		FileReader fr = new FileReader("./resource/Data/TestCollider.txt");
		BufferedReader br = new BufferedReader(fr);
		Collider c1 = ColliderInfo.getInstance().getCollider(br);
		
		fr = new FileReader("./resource/Data/TestCollider_1.txt");
		br = new BufferedReader(fr);
		Collider c2 = ColliderInfo.getInstance().getCollider(br);
		
		c1.setDirection(new Point(1, 0));
		c1.setPosition(new Point(120, 50));
		c2.setDirection(new Point(0, -1));
		c2.setPosition(new Point(50,50));
		
		System.out.println(c1.isCollide(c2));
		*/
		/*
		PEMThread t = new PEMThread();
		t.start();
		*/
		//PEM.getInstance().PrintState();
	}

}

class PPP implements Runnable {

	private Thread _thread;
	private boolean isRunning = false;

	public PPP() {
		init();
	}
	
	private void init() {
		/*
		Point pos = new Point(10, 10);
		Point dir = new Point(0, 1);
		Collider c = new SphereCollider(pos, 10);
		Projector p = new StrikeProjector(pos, dir, c, 1000L, 0);
		Emitter e = new DirectlyEmitter(1000L, dir, pos, p);
		Monster m = new Monster(10, 50, 100, 0, e, 1000L, c);
		PEM.getInstance().putMonster_Test(m);
		*/
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		int SleepTime = 1000/100;
		int Total = 3000;
		int Now = 0;
		
		int x = 0, y = 0;
		
		while (Now <= Total) {
			try {
				PEM.getInstance().tick();
				Thread.sleep(SleepTime);
				Now += SleepTime;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
			}
		}
		PEM.getInstance().PrintState();
		
	}
	
	public synchronized void start() {
		if ( isRunning ) 
			return;
		
		isRunning = true;
		_thread = new Thread(this);
		_thread.start();
	}
	
	public synchronized void stop() {
		if ( !isRunning ) 
			return;
		
		isRunning = false;
		try {
			_thread.join();
		} catch (InterruptedException event) {
			// TODO Auto-generated catch block
			event.printStackTrace();
		}
	}
}