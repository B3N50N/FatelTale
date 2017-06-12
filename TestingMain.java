import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import adm.ADM;
import scenere.SCENERE;
import sdm.SDM;
import ui.UI;

public class TestingMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//assert 1 == 2 : "HI";
		SDM.getInstance().readMap("./resource/Map/Map001.txt"); // Ū��
		SDM.getInstance().printMap();
		UI.getinstance().startMenu();
		UI.getinstance().startGame();
		while ( UI.getinstance().getGraphics() == null );
		BufferStrategy bs = UI.getinstance().getBufferStrategy();
		Graphics g = UI.getinstance().getGraphics();
		assert bs != null && g != null;
		g.clearRect(0, 0, UI.getinstance().getCanvasWidth(), UI.getinstance().getCanvasHeight());
		SCENERE.getInstance().render(g);
		bs.show();
		g.dispose(); 
		/*RenderThread rt = new RenderThread();
		rt.start();*/
		
		/*
		 Thread �����g�k
		 
		 BufferStrategy bs = UI.getinstance().getBufferStrategy();
		 Graphics g = UI.getinstance().getGraphics();
		 if ( bs != null && g !- null ) { <- �]���|�@�� LOOP �ҥH�ֵe�@���S���Y
		 	g.clearRect(0, 0, UI.getinstance().getCanvasWidth(), UI.getinstance().getCanvasHeight());
			SCENERE.getInstance().render(g);
			bs.show();
			g.dispose();
		 }
		  
		 
		 */
	}
}