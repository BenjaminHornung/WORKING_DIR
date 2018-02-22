package at.survivalcraft.gui;

import at.survivalcraft.proxy.ClientProxy;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

public class TestGUI extends GuiScreen {
    private GuiButton mButtonClose;
    private GuiLabel mLabelTest;

    @Override
    public void initGui() {
        this.buttonList.add(mButtonClose = new GuiButton(0, this.width / 2 - 100, this.height - (this.height / 4) + 10, "Close"));
        this.labelList.add(mLabelTest = new GuiLabel(mc.fontRenderer, 1, this.width / 2 - 20, this.height / 2 + 40, 300, 20, 0xFFFFFF));
        mLabelTest.addLine("Position: " + ClientProxy.player.getPrecision());
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        try {
            if (button == mButtonClose) {
                ClientProxy.player.stop();
                mc.displayGuiScreen(new GuiMainMenu());
            }
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
}
