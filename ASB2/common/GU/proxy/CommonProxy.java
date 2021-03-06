package GU.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.GUTickHandler;
import GU.blocks.containers.BlockCreationTable.ContainerCreationTable;
import GU.blocks.containers.BlockCreationTable.TileCreationTable;
import GU.blocks.containers.BlockGeothermalGenerator.ContainerGeothermalGenerator;
import GU.blocks.containers.BlockGeothermalGenerator.TileGeothermalGenerator;
import GU.blocks.containers.BlockSpeedyFurnace.ContainerSpeedyFurnace;
import GU.blocks.containers.BlockSpeedyFurnace.TileSpeedyFurnace;
import GU.info.Gui;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler {

    public void register() {

        TickRegistry.registerTickHandler(new GUTickHandler(), Side.SERVER);        
    }

    public int addArmor(String string) {

        return 0;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);
        
        switch(ID) {

            case Gui.CREATION_TABLE: return new ContainerCreationTable(player.inventory, (TileCreationTable)tile);
            case Gui.SPEEDY_FURNACE: return new ContainerSpeedyFurnace(player.inventory, (TileSpeedyFurnace)tile);          
            case Gui.GEOTHERMAL_GENERATOR: return new ContainerGeothermalGenerator(player.inventory, (TileGeothermalGenerator)tile);  
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }
}
