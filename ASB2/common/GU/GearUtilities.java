package GU;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import GU.info.Reference;
import GU.info.Variables;
import GU.packets.GUPacketHandler;
import GU.proxy.CommonProxy;
import GU.worldGen.WorldGenBlockAirCrystalOre;
import GU.worldGen.WorldGenBlockBurningFlower;
import GU.worldGen.WorldGenBlockEarthCrystalOre;
import GU.worldGen.WorldGenBlockEnergyCrystalOre;
import GU.worldGen.WorldGenBlockFalseBlock;
import GU.worldGen.WorldGenBlockFireCrystalOre;
import GU.worldGen.WorldGenBlockFreezingFlower;
import GU.worldGen.WorldGenBlockGarnetOre;
import GU.worldGen.WorldGenBlockWaterCrystalOre;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODDID, name = Reference.NAME, version = Reference.VERSION, dependencies = "required-after:Forge@[7.7.1.829,)")

@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {Reference.MOD_CHANNEL}, packetHandler = GUPacketHandler.class)

public final class GearUtilities {

    @Instance(Reference.MODDID)

    public static GearUtilities instance;
    public static Logger logger = Logger.getLogger(Reference.MODDID);

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)

    public static CommonProxy proxy;

    public GearUtilities() {

        System.out.println("["+Reference.NAME + "] Beginning destruction of known world");
        if(Loader.isModLoaded("Natura") || Loader.isModLoaded("TConstruct")) {

            System.out.println("["+Reference.NAME + "] Joining mods in world desturction");
        }
    }

    public static CreativeTabs tabGUBlocks = new GUCreativeTab(CreativeTabs.getNextID(), Reference.NAME + ": Blocks");
    public static CreativeTabs tabGUItems = new GUCreativeTab(CreativeTabs.getNextID(), Reference.NAME + ": Items");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        logger.setParent(FMLLog.getLogger());
        logger.log(Level.INFO, "Hi");

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        Variables.updateVariables(config);
        ItemRegistry.init(config);
        BlockRegistry.init(config);
        
        MinecraftForge.EVENT_BUS.register(new FluidRegistry());
        FluidRegistry.initFluids();
        
        MinecraftForge.EVENT_BUS.register(new MiscRegistry());
        MiscRegistry.init(config);

        config.save();
        instance = this;
    }

    @EventHandler
    public void mainInit(FMLInitializationEvent event) {

        proxy.register();
        NetworkRegistry.instance().registerGuiHandler(this, GearUtilities.proxy);

        MinecraftForge.addGrassPlant(BlockRegistry.BlockBurningFlower, 0, 100);
        MinecraftForge.addGrassPlant(BlockRegistry.BlockFreezingFlower, 0, 100);

        GameRegistry.registerWorldGenerator(new WorldGenBlockAirCrystalOre());
        GameRegistry.registerWorldGenerator(new WorldGenBlockEarthCrystalOre());
        GameRegistry.registerWorldGenerator(new WorldGenBlockFireCrystalOre());
        GameRegistry.registerWorldGenerator(new WorldGenBlockWaterCrystalOre());
        GameRegistry.registerWorldGenerator(new WorldGenBlockFreezingFlower());
        GameRegistry.registerWorldGenerator(new WorldGenBlockBurningFlower());
        GameRegistry.registerWorldGenerator(new WorldGenBlockEnergyCrystalOre());
        GameRegistry.registerWorldGenerator(new WorldGenBlockGarnetOre());                 
        GameRegistry.registerWorldGenerator(new WorldGenBlockFalseBlock());

        //        OreDictionary.registerOre("bioMass", Item.seeds);
        //        GameRegistry.registerWorldGenerator(new WorldGenBlockAirCrystalOre());
        //        GameRegistry.registerPlayerTracker(new TechCraftPlayerTracker ());
        //        GameRegistry.registerPlayerTracker(new TechCraftPlayerTracker ());
        //        MinecraftForge.EVENT_BUS.register(new TechCraftForgeEvents());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        FluidRegistry.registerFluidContainers();
        CraftRegistry.init();
    }
}
