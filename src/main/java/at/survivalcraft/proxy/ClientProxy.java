package at.survivalcraft.proxy;

import at.survivalcraft.events.ClientEvents;
import at.survivalcraft.gui.TestGUI;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.net.MalformedURLException;
import java.net.URL;

public class ClientProxy extends CommonProxy {
    public static BasicPlayer player = new BasicPlayer();

    @Override
    public void preInit(FMLPreInitializationEvent e) {

        String songName = "Blue_Monday.mp3";
        String pathToMp3 = "A:\\Downloads\\" + songName;
        try {
            player.open(new URL("file:///" + pathToMp3));
            player.play();
        } catch (BasicPlayerException | MalformedURLException ex) {
            ex.printStackTrace();
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
