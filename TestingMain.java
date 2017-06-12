import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import adm.ADM;
import scenere.SCENERE;
import sdm.SDM;
import ui.UI;

public class TestingMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//assert 1 == 2 : "HI";
		SDM.getInstance().readMap("./resource/Map/Map001.txt");
		SDM.getInstance().printMap();
		UI.getinstance().startMenu();
		UI.getinstance().startGame();
		while ( UI.getinstance().getGraphics() == null );
		UI.getinstance().getGraphics();
		System.out.println("FINAL");
		/*RenderThread rt = new RenderThread();
		rt.start();*/
	}

}

class RenderThread implements Runnable {
	private JFrame _scene;
	private Canvas _canvas;
	private Thread _thread;
	
	private boolean isRunning = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public RenderThread() {
		
	}
	
	private void init() {
		_scene = new JFrame("Test");
		_scene.setSize(500,300);
		_scene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_scene.setLocationRelativeTo(null);
		_scene.setResizable(false);
		_scene.setVisible(true);
		
		_canvas = new Canvas();
		_canvas.setPreferredSize(new Dimension(500,300));
		_canvas.setFocusable(false);
		
		_scene.add(_canvas);
	}
	
	private void Render(int x, int y) {
		bs = _canvas.getBufferStrategy();
		if ( bs == null ) {
			_canvas.createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, 500, 300);
		
		SCENERE.getInstance().testRender(g);
		
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		int SleepTime = 1000/20;
		
		int x = 0, y = 0;
		
		while (true) {
			try {
				Render(x,y);
				x++;
				y++;
				Thread.sleep(SleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
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