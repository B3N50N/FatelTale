package tcp;

import java.io.*;
import java.net.*;
import java.lang.Thread;
import cdc.CDC;
import logger.Logger;
import java.nio.ByteBuffer;

public class ConnectionHandler extends Thread {
    private Socket sock;
    private InputStream is;
    private OutputStream os;
    private Integer id;
    public InetAddress getAddress() {
        return sock.getInetAddress();
    }
    public ConnectionHandler(Socket _sock, int _id) {
        sock = _sock;
        id = new Integer(_id);
        try {
            sock.setKeepAlive(true);
        } catch(SocketException e) {
            Logger.log("[" + id + "] An error occur while setting keepalive on socket : " + e);
            System.exit(1);
        }
        try {
            is = sock.getInputStream();
            os = sock.getOutputStream();
        } catch(IOException e) {
            Logger.log("[" + id + "] An error occur whlie getting IO stream" + e);
            System.exit(1);
        }
    }
    public void modifyObject(int action, int objid, int type) {
        try {
            os.write(action);
            byte[] bytes = ByteBuffer.allocate(4).putInt(objid).array();
            os.write(bytes);
            os.write(type);
            os.flush();
        } catch(IOException e) {
            Logger.log("[" + id + "] Connection closed : " + sock);
            try {
                TCPServer.getServer().removeConnection(id);
            } catch(NullPointerException ee){};
        }
    }
    public void setDead(int id) {
        try {
            os.write(codes.SETDEAD);
            os.write(id);
        } catch(IOException e) {
            Logger.log("[" + id + "] Connection closed : " + sock);
            try {
                TCPServer.getServer().removeConnection(id);
            } catch(NullPointerException ee){};
        }
    }
    public void readMap(String path) {
        try {
            os.write(codes.READMAP);
            os.write(path.getBytes().length);
            os.write(path.getBytes());
            os.flush();
        } catch(IOException e) {
            Logger.log("[" + id + "] Connection closed : " + sock);
            try {
                TCPServer.getServer().removeConnection(id);
            } catch(NullPointerException ee){};
        }
    }
    public void endGame() {
        try {
            os.write(codes.END);
            os.flush();
        } catch(IOException e) {
            Logger.log("[" + id + "] Connection closed : " + sock);
            try {
                TCPServer.getServer().removeConnection(id);
            } catch(NullPointerException ee){};
        }
    }
    public void run() {
        Logger.log("[" + id + "] Thread starts");
        try {
            os.write(codes.SYN);
            Logger.log("[" + id + "] Sending synchronize message");
            os.write(id);
            Logger.log("[" + id + "] Sending client ID");
            os.write(TCPServer.THREAD_NUM);
            Logger.log("[" + id + "] Sending player number");
            os.flush();
        } catch(IOException e) {
            Logger.log("An error occur whlie sending synchronize message" + e);
            System.exit(1);
        }
        try {
            TCPServer.getServer().getBarrier().await();
        } catch(Exception e) {
            Logger.log("Failed to wait on barrier");
        }
        // TODO: set player type here
        CDC.getInstance().addPlayer(id, 0);
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
                    Logger.log("[" + id + "] Unrecognized code <" + code + "> ignored");
                }
            } catch(IOException e) {
                Logger.log("[" + id + "] Connection closed : " + sock);
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

