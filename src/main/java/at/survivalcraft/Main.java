package at.survivalcraft;

import at.survivalcraft.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main {
    public static final String MODID = "ms";
    public static final String NAME = "Minecraft Stories";
    public static final String VERSION = "0.0.1";
    public static final Logger LOGGER = LogManager.getLogger(Main.MODID);

    @SidedProxy(clientSide = "at.survivalcraft.proxy.ClientProxy", serverSide = "at.survivalcraft.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Main instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
        LOGGER.info("INIT");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

}
