import pem.PEM;
import tcp.TCPServer;
import udp.UDPBC;
import sdm.SDM;

public class Server {
	public static void main(String[] args) {
        SDM.getInstance().readMap("./resource/Map/Map001.txt");

        TCPServer.getServer().initTCPServer();
        UDPBC.getInstance().startUDPBroadCast();

        PEMThread pt = new PEMThread();
        pt.start();
	}
}

class PEMThread implements Runnable {

	private Thread _thread;
	private boolean isRunning = false;

	public PEMThread() {
		init();
	}
	
	private void init() {
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		int SleepTime = 1000/100;
		int Total = 3000;
		int Now = 0;
		
		int x = 0, y = 0;
		
		while ( true ) {
			PEM.getInstance().tick();
		}
		//PEM.getInstance().PrintState();
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
