package at.survivalcraft.events;

import at.survivalcraft.gui.TestGUI;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEvents {

    private boolean showTestGui = true;

    @SubscribeEvent
    public void onGuiOpened(GuiOpenEvent event) {
        if (showTestGui && event.getGui() instanceof GuiMainMenu) {
            event.setGui(new TestGUI());
            showTestGui = false;
        }
    }
}
