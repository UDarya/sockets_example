package com.home.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientPlayer implements Player {
    private int sendMsgCounter;
    private int receiveMsgCounter;
    private final int limit;
    private final int port;

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientPlayer(int limit, int port) {
        sendMsgCounter = 0;
        receiveMsgCounter = 0;
        this.limit = limit;
        this.port = port;

    }

    public void run() throws IOException {
        InetAddress host = InetAddress.getLocalHost();
        clientSocket = new Socket(host.getHostName(), port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out.println(++sendMsgCounter);
        while (sendMsgCounter <= limit || receiveMsgCounter <= limit) {
            String msg = in.readLine();
            receiveMsgCounter++;
            System.out.println("Client Msg:" + msg);
            out.println(msg + ++sendMsgCounter);
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
