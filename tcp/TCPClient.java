package tcp;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.lang.Thread;

public class TCPClient {
    private static InputStream is = null;
    private static OutputStream os = null;
    private static TCPClient client = null;
    private static Socket sock = null;
    public static final int DEFAULT_PORT = 8888;
    private TCPClient() {}
    public static TCPClient getClient() {
        if(client == null)
            client = new TCPClient();
        return client;
    }
    public boolean connectServer(InetAddress srv) {
        if(client == null)
            client = new TCPClient();
        try {
            sock = new Socket(srv, DEFAULT_PORT);
            sock.setKeepAlive(true);
        } catch(IllegalArgumentException e) {
            System.err.println("Invalid server address");
            return false;
        } catch(IOException e) {
            System.err.println("Connection failed.");
            return false;
        }
        try {
            is = sock.getInputStream();
            os = sock.getOutputStream();
        } catch(IOException e) {
            System.err.println("An error occur while getting IO stream : " + e);
            return false;
        }

        byte[] buf = new byte[16];
        try {
            is.read(buf);
        } catch(IOException e) {
            System.err.println("An error occure while reading from socket : " + sock);
            return false;
        }
        String syn = new String(buf).trim();
        if(!syn.equals("S")) {
            System.err.println("Receive an invalid synchronize message : " + sock);
            return false;
        }
        return true;
    }
    public void inputMoves(int MoveCode) {
        try {
            if(os == null) return;
            os.write(MoveCode);
        } catch(IOException e) {
            System.err.println("An error occur while sending to server : " + e);
        }
    }
}

