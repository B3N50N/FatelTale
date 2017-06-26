package tcp;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Vector;
import java.lang.Thread;

import logger.Logger;

public class TCPServer {
    // default number of threads(clients)
    public static final int THREAD_NUM = 4;
    // default port that server listening on
    public static final int DEFAULT_PORT = 8888;
    private static HashMap<Integer, ConnectionHandler> clients = null;
    private static TCPServer server = null;
    private static ServerSocket srv = null;
    private TCPServer() {}
    // Get the server instance
    public static synchronized TCPServer getServer() {
        if(server == null)
            server = new TCPServer();
        return server;
    }
    // remove the client from table
    public synchronized void removeConnection(int id) {
        clients.remove(id);
    }
    public void createObject(int objid, int type) {
        for(ConnectionHandler conn : clients.values())
            conn.modifyObject(codes.CREATEOBJ, objid, type);
    }
    public void deleteObject(int objid, int type) {
        for(ConnectionHandler conn : clients.values())
            conn.modifyObject(codes.REMOVEOBJ, objid, type);
        
    }
    // initialize server with default port
    public void initTCPServer() {
        initTCPServer(DEFAULT_PORT);
    }
    // initialize server with specified port
    public void initTCPServer(int port) {
        // Initialize server socket
        try {
            srv = new ServerSocket(port);
        } catch(IOException e) {
            Logger.log("An error occur while creating a socket listen on port "
                        + port);
            return;
        }

        Logger.log("Waiting for connections...");

        // Accept connections
        Socket[] conn = new Socket[THREAD_NUM];
        for(int i = 0; i < THREAD_NUM; ++i) {
            try {
                conn[i] = srv.accept();
            } catch(IOException e) {
                Logger.log("An error occur while accepting an connection.\n" + e);
                System.exit(1);
            }
            Logger.log("Receive a connection : " + conn[i]);
        }

        clients = new HashMap<Integer, ConnectionHandler>();
        ConnectionHandler[] thrds = new ConnectionHandler[THREAD_NUM];
        // Spawn thread for each connection
        for(int i = 0; i < THREAD_NUM; ++i) {
            thrds[i] = new ConnectionHandler(conn[i], i);
            clients.put(i, thrds[i]);
            thrds[i].start();
        }
    }
    // get a vector of client addresses
    public Vector<InetAddress> getClientIPTable() {
        Vector<InetAddress> addrs = new Vector<InetAddress>();
        for(int i = 0; i < THREAD_NUM; ++i)
            addrs.add(clients.get(i).getAddress());
        return addrs;
    }
}

