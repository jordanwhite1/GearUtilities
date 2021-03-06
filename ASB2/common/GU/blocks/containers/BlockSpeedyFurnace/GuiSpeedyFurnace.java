package GU.blocks.containers.BlockSpeedyFurnace;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.gui.GuiBase;

public class GuiSpeedyFurnace extends GuiBase {

    @SuppressWarnings("unused")
    private TileSpeedyFurnace tileEntity;

    public GuiSpeedyFurnace(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerSpeedyFurnace(inventory, (TileSpeedyFurnace)tile));

        tileEntity = (TileSpeedyFurnace) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
        this.renderBigSlot(posX + 111, posY + 27);
        this.renderRightArrow(posX + 79, posY + 32);
    }
}