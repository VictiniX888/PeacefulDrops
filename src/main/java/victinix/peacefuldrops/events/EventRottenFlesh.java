package victinix.peacefuldrops.events;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventRottenFlesh {

    @SubscribeEvent
    public static void eventRottenFlesh(ItemTossEvent event) {

        EntityItem item = event.getEntityItem();
        if(item.getEntityItem().isItemEqual(new ItemStack(Items.PORKCHOP))) {
            item.setEntityItemStack(new ItemStack(Items.ROTTEN_FLESH));
        }
    }
}
