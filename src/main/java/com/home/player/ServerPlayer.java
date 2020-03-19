package com.home.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPlayer implements Player {
    private int sendMsgCounter;
    private int receiveMsgCounter;
    private final int limit;
    private final int port;

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ServerPlayer(int limit, int port) {
        sendMsgCounter = 0;
        receiveMsgCounter = 0;
        this.limit = limit;
        this.port = port;
    }

    public void run() throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while (sendMsgCounter <= limit || receiveMsgCounter <= limit) {
            String msg = in.readLine();
            receiveMsgCounter++;
            System.out.println("Server Msg:" + msg);
            out.println(msg + ++sendMsgCounter);
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
