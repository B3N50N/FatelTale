import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import adm.ADM;
import scenere.SCENERE;
import sdm.SDM;
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
		SDM.getInstance().readMap("resource/Map/Map001.txt"); 
		SDM.getInstance().printMap();
		UI.getinstance().startMenu(args[0]);

        // Wait until connection success or handle failed
        TCPClient.getClient().waitForReady();
        Logger.log("Game start");
		UI.getinstance().startGame();
		while ( UI.getinstance().getGraphics() == null );
		BufferStrategy bs = UI.getinstance().getBufferStrategy();
		Graphics g = UI.getinstance().getGraphics();
		assert bs != null && g != null;
		g.clearRect(0, 0, UI.getinstance().getCanvasWidth(), UI.getinstance().getCanvasHeight());
		SCENERE.getInstance().render(g);
		bs.show();
		g.dispose();
	}
}
