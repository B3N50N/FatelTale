package tcp;

import java.io.*;
import java.net.*;
import java.lang.Thread;
import cdc.CDC;
import logger.Logger;

public class ConnectionHandler extends Thread {
    private Socket sock;
    private InputStream is;
    private OutputStream os;
    private Integer id;
    public SocketAddress getAddress() {
        return sock.getRemoteSocketAddress();
    }
    public ConnectionHandler(Socket _sock, int _id) {
        sock = _sock;
        id = new Integer(_id);
        try {
            sock.setKeepAlive(true);
        } catch(SocketException e) {
            Logger.log("An error occur while setting keepalive on socket : " + e);
            System.exit(1);
        }
        try {
            is = sock.getInputStream();
            os = sock.getOutputStream();
        } catch(IOException e) {
            System.err.println("An error occur whlie getting IO stream" + e);
            System.exit(1);
        }
    }
    public void modifyObject(int action, int objid, int type) {
        try {
            os.write(action);
            os.write(objid);
            os.write(type);
        } catch(IOException e) {
            Logger.log("Connection closed : " + sock);
            try {
                TCPServer.getServer().removeConnection(id);
            } catch(NullPointerException ee){};
        }
    }
    public void run() {
        Logger.log("[" + id + "]" + " Thread starts");
        // TODO: set player type here
        CDC.getInstance().addPlayer(id, 0);
        try {
            os.write("S".getBytes());
        } catch(IOException e) {
            Logger.log("An error occur whlie sending synchronize message" + e);
            System.exit(1);
        }
        for(;;) {
            try {
                // prase the request message
                int code = is.read();
                int key = 0;
                switch(code) {
                case codes.KEYDOWN:
                    key = is.read();
                    CDC.getInstance().keyDown(id, key);
                    break;
                case codes.KEYRELEASE:
                    key = is.read();
                    CDC.getInstance().keyRelease(id, key);
                    break;
                case -1:
                    throw new IOException();
                default:
                    Logger.log("Unrecognized code <" + code + "> ignored");
                }
            } catch(IOException e) {
                Logger.log("Connection closed : " + sock);
                try {
                    TCPServer.getServer().removeConnection(id);
                } catch(NullPointerException ee){};
                break;
            }
        }
        try {
            sock.close();
        } catch(IOException e) {}
    }
}

