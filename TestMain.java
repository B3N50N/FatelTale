import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import adm.ADM;
import entity.*;
import pem.PEM;
import sdm.SDM;

public class TestMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SDM.getInstance().readMap("./resource/Map/Map001.txt");
		/*
		PEMThread t = new PEMThread();
		t.start();
		*/
		Item item = new Item(new Point(0, 0), 0, ItemInfo.getInstance().getTypeInfo(0), ItemInfo.getInstance().getCollider(0));
		
		item.setPosition(new Point(100, 100));
		item.Print();
		
		Item newItem = item.clone();
		newItem.setPosition(new Point(200, 100));
		
		item.Print();
		newItem.Print();
		//PEM.getInstance().PrintState();
	}

}

class PEMThread implements Runnable {

	private Thread _thread;
	private boolean isRunning = false;

	public PEMThread() {
		init();
	}
	
	private void init() {
		MonsterInfo.getInstance().loadMonsterData("./resource/Data/Monster/Mode1/");
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
				//PEM.getInstance().PrintState();
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