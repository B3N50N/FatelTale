import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import scenere.SCENERE;
import sdm.SDM;
import spritere.SPRITERE;
import ui.UI;
import tcp.TCPClient;
import logger.Logger;

public class Client {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//assert 1 == 2 : "HI";

        if(args.length < 1) {
            System.err.println("Usage : java Client SERVER_ADDR");
            System.exit(1);
        }
		
		UI.getInstance().startMenu(args[0]);

        // Wait until connection success or handle failed
        TCPClient.getClient().waitForReady();
        TCPClient.getClient().start();

        Logger.log("Game start");
		UI.getInstance().startGame();
		RenderThread _render_thread = new RenderThread();
		_render_thread.start();
	}
}


class RenderThread implements Runnable {

	private Thread _thread;
	private boolean isRunning = false;

	public RenderThread() {
		init();
	}
	
	private void init() {
		SDM.getInstance().readMap("resource/Map/Map001.txt"); 
	}
	
	private void render() {
		BufferStrategy bs = UI.getInstance().getBufferStrategy();
		Graphics g = UI.getInstance().getGraphics();
		g.clearRect(0, 0, UI.getInstance().getCanvasWidth(), UI.getInstance().getCanvasHeight());
		if ( bs != null && g != null ) {
			SCENERE.getInstance().render(g);
			SPRITERE.getInstance().render(g);
            bs.show();
            g.dispose();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		
		int fps = 60;
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
