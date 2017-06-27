import ui.UI;
import tcp.TCPClient;
import udp.UDPUS;
import logger.Logger;
import render.RenderThread;

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

        Logger.log("Game starts");
		UI.getInstance().startGame();
        UDPUS.getInstance().initUDPServer();
		RenderThread _render_thread = new RenderThread();
		_render_thread.start();
	}
}
