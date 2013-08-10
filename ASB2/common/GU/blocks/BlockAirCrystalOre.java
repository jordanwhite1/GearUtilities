package GU.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import GU.ItemRegistry;

public class BlockAirCrystalOre extends BlockBase {

    public BlockAirCrystalOre(int id, Material material) {
        super(id, material);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {

        return ItemRegistry.ItemAirCrystalShard.itemID;
    }

    @Override
    public int quantityDropped(Random par1Random) {

        return par1Random.nextInt(5) + 1;
    }
}
