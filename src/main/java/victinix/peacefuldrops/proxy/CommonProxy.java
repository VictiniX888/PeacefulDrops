package victinix.peacefuldrops.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import victinix.peacefuldrops.events.Events;
import victinix.peacefuldrops.recipes.Recipes;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {

        Recipes.init();

        MinecraftForge.EVENT_BUS.register(new Events());
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
