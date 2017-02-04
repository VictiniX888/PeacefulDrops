package victinix.peacefuldrops.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import victinix.peacefuldrops.events.EventBlazeRod;
import victinix.peacefuldrops.events.EventRottenFlesh;
import victinix.peacefuldrops.lib.Recipes;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {

        Recipes.init();

        MinecraftForge.EVENT_BUS.register(new EventRottenFlesh());
        MinecraftForge.EVENT_BUS.register(new EventBlazeRod());
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
