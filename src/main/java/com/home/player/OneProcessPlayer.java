package com.home.player;

import java.util.concurrent.BlockingQueue;

public class OneProcessPlayer extends Thread {
    private int sendMsgCounter;
    private int receiveMsgCounter;
    private final int limit;
    private final boolean isInitiator;

    private BlockingQueue<String> inputMsgQueue;
    private BlockingQueue<String> outputMsgQueue;

    public OneProcessPlayer(int limit, BlockingQueue inputMsgQueue, BlockingQueue outputMsgQueue, boolean isInitiator) {
        sendMsgCounter = 0;
        receiveMsgCounter = 0;
        this.limit = limit;
        this.inputMsgQueue = inputMsgQueue;
        this.outputMsgQueue = outputMsgQueue;
        this.isInitiator = isInitiator;
    }

    public void run() {
        try {
            if (isInitiator) {
                sendAnswer(String.valueOf(++sendMsgCounter));
            }

            while (sendMsgCounter <= limit || receiveMsgCounter <= limit) {
                String msg = inputMsgQueue.take();
                System.out.println("Msg: " + msg);
                receiveMsgCounter++;
                sendAnswer(msg + sendMsgCounter++);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void sendAnswer(String msg) {
        outputMsgQueue.add(msg);
    }
}
