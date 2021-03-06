package GU.blocks.containers;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import GU.GUItemBlock;
import GU.GearUtilities;
import GU.api.IWrenchable;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ContainerBase extends BlockContainer {

    protected boolean useStandardRendering = true;
    protected boolean useDefaultTexture = false;  
    Icon texture;
    String blockName = "";

    public ContainerBase(int id, Material material) {
        super(id, material);

        MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 2);        
        this.setCreativeTab(GearUtilities.tabGUBlocks);
        setHardness(100f);
        setResistance(100F);
    }

    public boolean rotate(ItemStack itemStack, World world, int x, int y, int z, boolean shifting, int side) {

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if(tile != null && tile instanceof IWrenchable) {

            if(itemStack.getItem() == Item.stick) {

                ((IWrenchable)tile).triggerBlock(world, shifting, itemStack, x, y, z, side);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        return !rotate(player.getCurrentEquippedItem(), world, x, y, z, player.isSneaking(), side);
    }
    
    @Override
    public boolean renderAsNormalBlock() {

        return useStandardRendering;
    }

    @Override
    public boolean isOpaqueCube() {

        return useStandardRendering;
    }

    @Override
    public int getRenderType() {

        if(!useStandardRendering)
            return -1;

        return 0;
    }

    public boolean canCreatureSpawn() {

        return false;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {

        return sideHit;
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData) {

        //TileEntity tile = world.getBlockTileEntity(x, y, z);


        this.dropItems(world, x, y, z);
        super.onBlockDestroyedByPlayer(world, x, y, z, metaData);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

        //TileEntity tile = world.getBlockTileEntity(x, y, z);

        this.dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropItems(World world, int x, int y, int z) {

        Random prng = new Random();

        if(world.getBlockTileEntity(x, y, z) instanceof IInventory) {

            IInventory tileEntity = (IInventory)world.getBlockTileEntity(x, y, z);

            if(tileEntity == null)
                return;

            for(int slot = 0; slot < tileEntity.getSizeInventory(); slot++)
            {
                ItemStack item = tileEntity.getStackInSlot(slot);

                if(item != null && item.stackSize > 0)
                {
                    float rx = prng.nextFloat() * 0.8f + 0.1f;
                    float ry = prng.nextFloat() * 0.8f + 0.1f;
                    float rz = prng.nextFloat() * 0.8f + 0.1f;

                    EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, item.copy());
                    world.spawnEntityInWorld(entityItem);
                    item.stackSize = 0;
                }
            }
        }
    }

    public void registerTile(Class<? extends TileEntity> tileClass) {

        GameRegistry.registerTileEntity(tileClass, tileClass.toString());
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister.registerIcon(Reference.MODDID + ":GearBlock");
        texture = iconRegister.registerIcon(Reference.MODDID + ":" + blockName);
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        if(useDefaultTexture || texture == null) 
            return this.blockIcon;

        return texture;
    }

    public void setBlockName(String texture) {

        this.blockName = texture;
        this.setUnlocalizedName(Reference.UNIQUE_ID + blockName);
        GameRegistry.registerBlock(this, GUItemBlock.class, this.getUnlocalizedName());
    }
}
