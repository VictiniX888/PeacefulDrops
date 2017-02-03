package victinix.peacefuldrops.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import victinix.peacefuldrops.events.EventRottenFlesh;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new EventRottenFlesh());
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
