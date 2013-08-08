package GU.blocks.containers.BlockTestTank;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import GU.blocks.containers.ContainerBase;
import GU.info.Reference;
import GU.utils.UtilInventory;
import GU.utils.UtilRender;

public class BlockTestTank extends ContainerBase {

    private Icon[] icons = new Icon[16];
    private String folder = ":testConnectedTexture";

    public BlockTestTank(int id, Material material) {
        super(id, material);

        this.registerTile(TileTestTank.class);
        useStandardRendering = false;
    }

    public int getRenderType() {

        return TestTankRenderer.tankModelID;
    }

    @Override
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int par5) {

        return UtilRender.renderConnectedTexture(blockAccess, icons, this.blockID, x, y, z, par5);
    }


    public Icon getIcon(int side, int metadata) {

        return icons[0];
    }

    @Override
    public void registerIcons (IconRegister iconRegistry) {

        icons[0] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tankRegular");
        icons[1] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_1_d");
        icons[2] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_1_u");
        icons[3] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_1_l");
        icons[4] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_1_r");
        icons[5] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_h");
        icons[6] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_v");
        icons[7] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_dl");
        icons[8] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_dr");
        icons[9] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_ul");
        icons[10] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_2_ur");
        icons[11] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_3_d");
        icons[12] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_3_u");
        icons[13] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_3_l");
        icons[14] = iconRegistry.registerIcon(Reference.MODDID + folder + "/tank_3_r");
        icons[15] = iconRegistry.registerIcon(Reference.MODDID + folder + "/blank");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) 
    {
        ItemStack current = entityplayer.inventory.getCurrentItem();

        if (current != null) {

            FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(current);
            TileTestTank tank = (TileTestTank) world.getBlockTileEntity(x, y, z);

            if (fluid != null) {

                int amount = tank.fill(fluid, true);

                if (amount != 0 && !entityplayer.capabilities.isCreativeMode) {

                    return UtilInventory.consumeItemStack(entityplayer.inventory, current, 1);
                }
                return true;
            } 
            else {

                if(FluidContainerRegistry.isEmptyContainer(current)) {

                    if(tank.getFluid() != null) {

                        ItemStack filled = FluidContainerRegistry.fillFluidContainer(tank.getFluid(), current);

                        if(FluidContainerRegistry.getFluidForFilledItem(filled).amount <= tank.getFluidAmount()) {

                            if(!entityplayer.capabilities.isCreativeMode) {
                                
                                if(UtilInventory.addItemStackToInventoryAndSpawnExcess(world, entityplayer.inventory, filled, x, y, z) && UtilInventory.consumeItemStack(entityplayer.inventory, current, 1)) {

                                    tank.drain(FluidContainerRegistry.getFluidForFilledItem(filled).amount, true);
                                }
                            }
                            else {
                                
                                tank.drain(FluidContainerRegistry.getFluidForFilledItem(filled).amount, true);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileTestTank();
    }
}
