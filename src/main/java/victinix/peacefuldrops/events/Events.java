package victinix.peacefuldrops.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import victinix.peacefuldrops.data.Data;

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
    public void eventCrafting(PlayerEvent.ItemCraftedEvent event) {   //blaze rods & ghast tears

        IInventory matrix = event.craftMatrix;
        Item crafted = event.crafting.getItem();
        boolean hasStick = false, hasLava = false, hasWater = false, hasPowder = false;
        int filledSlots = 0;

        for (int i = 0; i < matrix.getSizeInventory(); i++) {
            if(matrix.getStackInSlot(i) != null) {
                if(matrix.getStackInSlot(i).getItem().equals(Items.STICK)) {
                    hasStick = true;
                }
                else if(matrix.getStackInSlot(i).getItem().equals(Items.LAVA_BUCKET)) {
                    hasLava = true;
                }
                else if(matrix.getStackInSlot(i).getItem().equals(Items.WATER_BUCKET)) {
                    hasWater = true;
                }
                else if(matrix.getStackInSlot(i).getItem().equals(Items.BLAZE_POWDER)) {
                    hasPowder = true;
                }
                filledSlots++;
            }
        }

        if(event.player.dimension != -1 && filledSlots == 2 && ((hasStick && hasLava && crafted.equals(Items.BLAZE_ROD)) || (hasWater && hasPowder && crafted.equals(Items.GHAST_TEAR)))) {
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

    /*
    @SubscribeEvent
    public void eventBone(LivingDropsEvent event) {

        Entity entity = event.getEntity();

        if(entity instanceof EntityChicken || entity instanceof EntityCow || entity instanceof EntityPig || entity instanceof EntityRabbit || entity instanceof EntitySheep || entity instanceof EntityVillager || entity instanceof EntityHorse || entity instanceof EntityWolf || entity instanceof EntityOcelot) {
            event.getDrops().add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.BONE)));
        }
    }
    */

    private List<ResourceLocation> entities = Arrays.asList(LootTableList.ENTITIES_CHICKEN, LootTableList.ENTITIES_COW, LootTableList.ENTITIES_PIG, LootTableList.ENTITIES_RABBIT, LootTableList.ENTITIES_SHEEP, LootTableList.ENTITIES_HORSE);

    @SubscribeEvent
    public void eventLootTable(LootTableLoadEvent event) {   //bone & slimeballs

        ResourceLocation name = event.getName();
        LootPool main = event.getTable().getPool("main");

        if(entities.contains(name)) {
            main.addEntry(new LootEntryItem(Items.BONE, 1, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(0, 2)), new LootingEnchantBonus(new LootCondition[0], new RandomValueRange(0, 1), 1)}, new LootCondition[0], Data.MODID + ":bone"));
        }
        else if(name.equals(LootTableList.GAMEPLAY_FISHING_JUNK)) {
            main.addEntry(new LootEntryItem(Items.SLIME_BALL, 500, 0, new LootFunction[0], new LootCondition[0], Data.MODID + ":slime_ball"));
        }
    }

    @SubscribeEvent
    public void eventExplosion(ExplosionEvent.Detonate event) {  //ender pearls, gunpowder & nether star

        World world = event.getWorld();
        Vec3d pos = event.getExplosion().getPosition();
        boolean hasEye = false, hasTear = false, hasMagma = false;

        world.spawnEntityInWorld(new EntityItem(world, pos.xCoord, pos.yCoord, pos.zCoord, new ItemStack(Items.GUNPOWDER, 2)));

        for(Entity e : event.getAffectedEntities()) {
            if(e instanceof EntityItem) {
                ItemStack itemStack = ((EntityItem) e).getEntityItem();
                int stack = itemStack.stackSize;

                if(itemStack.getItem().equals(Items.EMERALD)) {
                    world.spawnEntityInWorld(new EntityItem(world, pos.xCoord, pos.yCoord, pos.zCoord, new ItemStack(Items.ENDER_PEARL, stack)));
                }
                else if(itemStack.getItem().equals(Items.ENDER_EYE)) {
                    hasEye = true;
                }
                else if(itemStack.getItem().equals(Items.GHAST_TEAR)) {
                    hasTear = true;
                }
                else if(itemStack.getItem().equals(Items.MAGMA_CREAM)) {
                    hasMagma = true;
                }
            }
        }

        if(hasEye && hasTear && hasMagma && world.getBiome(new BlockPos(pos)).equals(Biomes.HELL)) {
            world.spawnEntityInWorld(new EntityItem(world, pos.xCoord, pos.yCoord, pos.zCoord, new ItemStack(Items.NETHER_STAR)));
        }
    }
}
