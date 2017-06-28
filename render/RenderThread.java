package render;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import sdm.SDM;
import scenere.SCENERE;
import logger.Logger;
import spritere.SPRITERE;
import ui.UI;

public class RenderThread implements Runnable {

	static private Thread _thread;
	static private boolean isRunning = false;

	public RenderThread() {
		init();
	}
	
	private void init() {
		//SDM.getInstance().readMap("resource/Map/Map001.txt"); 
	}
	
	private void render() {
		BufferStrategy bs = UI.getInstance().getBufferStrategy();
		Graphics g = UI.getInstance().getGraphics();
		g.clearRect(0, 0, UI.getInstance().getCanvasWidth(), UI.getInstance().getCanvasHeight());
		if ( bs != null && g != null ) {
			UI.getInstance().showScore();
			SCENERE.getInstance().render(g);
			SPRITERE.getInstance().render(g);
			bs.show();
			g.dispose();
            UI.getInstance().showScore();
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		
		int fps = 30;
		double TimePerTick = 1000000000 / fps;
		double delta = 0;
		long nowTime;
		long preTime = System.nanoTime();
		
		while (isRunning) {
			nowTime = System.nanoTime();
			delta += ( nowTime - preTime ) / TimePerTick;
			preTime = nowTime;
			
			while ( delta >= 1 ) {
				render();
				delta -= 1;
			}
		}
	}
	
	public synchronized void start() {
		if ( isRunning ) 
			return;
		
		isRunning = true;
		_thread = new Thread(this);
		_thread.start();
	}
	
	static public synchronized void stop() {
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
