package at.survivalcraft.gui;

import at.survivalcraft.audio.AudioPlayer;
import at.survivalcraft.proxy.ClientProxy;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;


public class TestGUI extends GuiScreen {
    private GuiButton mButtonClose;
    private GuiLabel mLabelTest;

    private boolean playerRunning = false;
    private int currentPlayerID;

    @Override
    public void initGui() {
        int i = 1;
        for (String st : ClientProxy.nameToID.keySet()) {
            System.out.println(i + " " + st);
            this.buttonList.add(new GuiButton(i, this.width / 2 - 100, this.height - (this.height / i) + 10, st));
            i++;
        }
        this.buttonList.add(mButtonClose = new GuiButton(0, this.width / 2 - 100, this.height - (this.height / 4) + 10, "close"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (!playerRunning) {
            if (button == mButtonClose) {
                mc.displayGuiScreen(new GuiMainMenu());
            } else {
                int ID = ClientProxy.nameToID.get(button.displayString);
                AudioPlayer.startPlayer(ID);
                playerRunning = true;
                currentPlayerID = ID;
            }
        } else {
            AudioPlayer.stopPlayer(currentPlayerID);
            if (button == mButtonClose) {
                mc.displayGuiScreen(new GuiMainMenu());
                playerRunning = false;
            } else {
                int ID = ClientProxy.nameToID.get(button.displayString);
                AudioPlayer.startPlayer(ID);
                playerRunning = true;
                currentPlayerID = ID;
            }
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
