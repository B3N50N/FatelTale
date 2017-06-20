package tcp;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.lang.Thread;
import java.util.concurrent.CyclicBarrier;

import logger.Logger;

public class TCPClient {
    private InputStream is = null;
    private OutputStream os = null;
    private Socket sock = null;
    private CyclicBarrier ready_barrier = new CyclicBarrier(2);
    private static TCPClient client = null;
    public static final int DEFAULT_PORT = 8888;
    private TCPClient() {}
    public static TCPClient getClient() {
        if(client == null)
            client = new TCPClient();
        return client;
    }
    public boolean connectServer(String addr) {
        InetAddress srv = null;
        try {
            srv = InetAddress.getByName(addr);
        } catch (UnknownHostException e) {
            Logger.log("Cannot connect to server");
            return false;
        }
        return connectServer(srv);
    }
    public boolean connectServer(InetAddress srv) {
        if(client == null)
            client = new TCPClient();
        try {
            sock = new Socket(srv, DEFAULT_PORT);
            sock.setKeepAlive(true);
        } catch(IllegalArgumentException e) {
            Logger.log("Invalid server address");
            return false;
        } catch(IOException e) {
            Logger.log("Connection failed.");
            return false;
        }
        try {
            is = sock.getInputStream();
            os = sock.getOutputStream();
        } catch(IOException e) {
            Logger.log("An error occur while getting IO stream : " + e);
            return false;
        }


        Logger.log("Connected to server : " + sock);
        Logger.log("Waiting for other clients...");
        byte[] buf = new byte[16];
        try {
            is.read(buf);
        } catch(IOException e) {
            Logger.log("An error occure while reading from socket : " + sock);
            return false;
        }
        String syn = new String(buf).trim();
        if(!syn.equals("S")) {
            Logger.log("Receive an invalid synchronize message : " + sock);
            return false;
        }
        try {
            ready_barrier.await();
        } catch(Exception e) {
            Logger.log("Ready_barrier await failed : " + e);
            return false;
        }
        Logger.log("All clients connected.");
        return true;
    }
    public void keyDown(int Code) {
        try {
            if(os == null) return;
            os.write(MoveCode);
        } catch(IOException e) {
            Logger.log("An error occur while sending to server : " + e);
        }
    }
    public void keyRelease(int Code) {
        try {
            if(os == null) return;
            os.write(MoveCode);
        } catch(IOException e) {
            Logger.log("An error occur while sending to server : " + e);
        }
    }
    public void waitForReady() {
        try {
            ready_barrier.await();
        } catch(Exception e) {
        }
    }
}

