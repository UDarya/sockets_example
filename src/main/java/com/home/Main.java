package com.home;


import com.home.player.ClientPlayer;
import com.home.player.Player;
import com.home.player.ServerPlayer;
import com.home.player.PairPlayer;

import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static final int PORT = 8666;
    public static final int LIMIT = 10;

    private static HashMap<String, Player> commands = new HashMap<String, Player>();

    public static void init() {
        commands.put("-one-process", new PairPlayer(LIMIT));
        commands.put("-server", new ServerPlayer(LIMIT, PORT));
        commands.put("-client", new ClientPlayer(LIMIT, PORT));
    }


    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            System.out.println("Empty arguments!");
            return;
        }
        init();
        Player player = commands.get(args[0]);

        if (player == null) {
            System.out.println("Invalid command!");
            return;
        }

        player.run();
        player.stop();
    }

}
