package victinix.peacefuldrops.events;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.Arrays;
import java.util.List;

public class Events {

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

    @SubscribeEvent
    public void eventString(BlockEvent.BreakEvent event) {

        EntityPlayer player = event.getPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();

        if(player.getHeldItem(player.getActiveHand()) != null) {
            if(player.getHeldItem(player.getActiveHand()).getItem().equals(Items.SHEARS) && world.getBlockState(pos).getBlock().equals(Blocks.WOOL)) {
                event.setCanceled(true);
                world.setBlockToAir(pos);
                world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.STRING, 2)));
            }
        }
    }
}
