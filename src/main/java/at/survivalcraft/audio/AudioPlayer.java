package at.survivalcraft.audio;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import java.io.File;
import java.util.HashMap;

public class AudioPlayer {
    private static HashMap<Integer, BasicPlayer> playerList = new HashMap<>();

    public static void addPlayer(int id, File f) {
        BasicPlayer p = new BasicPlayer();
        try {
            p.open(f);
            playerList.put(id, p);
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    public static boolean removePlayer(int id) {
        if (playerList.containsKey(id)) {
            playerList.remove(id);
            return true;
        }
        return false;
    }

    public static boolean startPlayer(int id) {
        if (playerList.containsKey(id)) {
            BasicPlayer p = playerList.get(id);
            try {
                p.play();
            } catch (BasicPlayerException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean stopPlayer(int id) {
        if (playerList.containsKey(id)) {
            BasicPlayer p = playerList.get(id);
            try {
                p.stop();
            } catch (BasicPlayerException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean pausePlayer(int id) {
        if (playerList.containsKey(id)) {
            BasicPlayer p = playerList.get(id);
            try {
                p.pause();
            } catch (BasicPlayerException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }
}
