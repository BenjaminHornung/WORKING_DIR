package at.survivalcraft.proxy;

import at.survivalcraft.events.ClientEvents;
import at.survivalcraft.gui.TestGUI;
import javazoom.jlgui.basicplayer.BasicPlayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import static at.survivalcraft.audio.AudioPlayer.addPlayer;
import static at.survivalcraft.download.RemoteFileHandler.getSong;

public class ClientProxy extends CommonProxy {
    public static BasicPlayer player = new BasicPlayer();
    public static HashMap<String, Integer> nameToID = new HashMap<>();
    public static ArrayList<Integer> IDs = new ArrayList<Integer>();
    public static Path musicDir = Paths.get(Paths.get("").toAbsolutePath().toString(), "music");

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        IDs.add(1);
        IDs.add(2);
        for (int ID : IDs) {
            System.out.println("Downloading song: " + ID);
            String s = getSong(ID, musicDir);
            nameToID.put(s, ID);
            Path tmp = Paths.get(musicDir.toString(), s);
            addPlayer(ID, tmp.toFile());
        }
    }

    @Override
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());

    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        Minecraft.getMinecraft().displayGuiScreen(new TestGUI());
    }
}
