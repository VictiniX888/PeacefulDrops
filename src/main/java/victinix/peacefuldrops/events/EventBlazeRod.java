package victinix.peacefuldrops.events;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventBlazeRod {

    @SubscribeEvent
    public void eventBlazeRod(PlayerEvent.ItemCraftedEvent event) {

        IInventory matrix = event.craftMatrix;
        Item crafted = event.crafting.getItem();
        boolean hasStick = false, hasLava = false;
        int filledSlots = 0;

        for (int i = 0; i < matrix.getSizeInventory(); i++) {
            if(matrix.getStackInSlot(i) != null) {
                if(matrix.getStackInSlot(i).getItem().equals(Items.STICK)) {
                    hasStick = true;
                }
                else if(matrix.getStackInSlot(i).getItem().equals(Items.LAVA_BUCKET)) {
                    hasLava = true;
                }
                filledSlots++;
            }
        }

        if(event.player.dimension != -1 && hasStick && hasLava && filledSlots == 2 && crafted.equals(Items.BLAZE_ROD)) {
            event.crafting.stackSize--;
            event.player.setHealth(1f);
            event.player.setFire(5);
        }
    }
}
