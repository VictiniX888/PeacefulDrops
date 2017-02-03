package victinix.peacefuldrops;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import victinix.peacefuldrops.lib.Data;
import victinix.peacefuldrops.proxy.CommonProxy;

@Mod(modid = Data.MODID, name = Data.NAME, version = Data.VERSION)

public class PeacefulDrops {

    @SidedProxy(clientSide = "victinix.peacefuldrops.proxy.ClientProxy", serverSide = "victinix.peacefuldrops.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static PeacefulDrops instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        proxy.postInit(event);
    }
}
