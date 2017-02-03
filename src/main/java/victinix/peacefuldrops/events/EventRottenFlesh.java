package victinix.peacefuldrops.events;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class EventRottenFlesh {

    private List<Item> meat = Arrays.asList(Items.BEEF, Items.PORKCHOP, Items.CHICKEN, Items.MUTTON, Items.RABBIT);

    @SubscribeEvent
    public void eventRottenFlesh(ItemExpireEvent event) {

        EntityItem item = event.getEntityItem();
        int stack = item.getEntityItem().stackSize;
        if(meat.contains(item.getEntityItem().getItem())) {
            event.setCanceled(true);
            item.setEntityItemStack(new ItemStack(Items.ROTTEN_FLESH, stack));
        }
    }
}
