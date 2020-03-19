package com.home.player;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class PairPlayer implements Player {
    private final int limit;

    public PairPlayer(int limit) {
        this.limit = limit;
    }

    public void run() {
        LinkedBlockingQueue inputMsgQueue = new LinkedBlockingQueue();
        LinkedBlockingQueue outputMsgQueue = new LinkedBlockingQueue();

        OneProcessPlayer initiator = new OneProcessPlayer(limit, inputMsgQueue, outputMsgQueue, true);
        OneProcessPlayer player = new OneProcessPlayer(limit, outputMsgQueue, inputMsgQueue, false);

        initiator.start();
        player.start();

        try {
            initiator.join();
            player.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void stop() throws IOException {

    }
}
