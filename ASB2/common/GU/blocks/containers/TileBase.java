package GU.blocks.containers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidTank;
import GU.api.IDirectionSpecific;
import GU.api.color.EnumColor;
import GU.api.color.IColorable;
import GU.api.power.PowerProvider;
import GU.api.wait.IWaitTrigger;
import GU.api.wait.Wait;
import GU.utils.UtilDirection;

public abstract class TileBase extends TileEntity implements IColorable, IDirectionSpecific, IWaitTrigger {

    protected PowerProvider powerProvider;
    protected ForgeDirection orientation;    
    protected EnumColor color;
    protected ItemStack[] tileItemStacks = new ItemStack[0];
    public FluidTank fluidTank;
    protected Wait waitTimer;

    public TileBase() {

        if(color == null)
            color = EnumColor.NONE;

        if(orientation == null)
            orientation = ForgeDirection.DOWN;

        fluidTank = new FluidTank(0);
    }

    @Override
    public ForgeDirection getOrientation() {

        if(!(orientation == UtilDirection.translateNumberToDirection(getBlockMetadata()))) {

            this.orientation = UtilDirection.translateNumberToDirection(getBlockMetadata());
        }

        if(orientation == ForgeDirection.SOUTH) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        if(orientation == ForgeDirection.NORTH) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        if(orientation == ForgeDirection.UP) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        if(orientation == ForgeDirection.DOWN) {
            return UtilDirection.translateDirectionToOpposite(orientation);
        }
        return orientation;
    }

    @Override
    public EnumColor getColorEnum() {

        return color;
    }

    @Override
    public void setColor(EnumColor color) {

        this.color = color;
    }

    

    @Override
    public void trigger(int id) {

    }

    @Override
    public boolean shouldTick(int id) {

        return !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }

    

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        fluidTank.readFromNBT(tag);

        if(color == EnumColor.NONE || color == null)
            color = EnumColor.translateNumberToColor(tag.getInteger("color"));

        if(this.powerProvider != null)
            this.powerProvider.readFromNBT(tag);

        NBTTagList nbttaglist = tag.getTagList("Items");

        tileItemStacks = new ItemStack[tileItemStacks.length];

        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound.getByte("Slot");

            if (byte0 >= 0 && byte0 < tileItemStacks.length)
            {
                tileItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag); 

        fluidTank.writeToNBT(tag);

        if(this.getColorEnum() != EnumColor.NONE)
            tag.setInteger("color", EnumColor.translateColorToNumber(this.getColorEnum()));

        if(this.powerProvider != null)
            this.powerProvider.writeToNBT(tag);

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < tileItemStacks.length; i++)
        {
            if (tileItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                tileItemStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        tag.setTag("Items", nbttaglist);
    }
}
