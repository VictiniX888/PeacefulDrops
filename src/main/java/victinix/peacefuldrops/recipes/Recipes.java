package victinix.peacefuldrops.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {

    public static void init() {

        GameRegistry.addShapelessRecipe(new ItemStack(Items.BLAZE_ROD),
                new ItemStack(Items.STICK),
                new ItemStack(Items.LAVA_BUCKET));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.SLIME_BALL),
                new ItemStack(Items.ENDER_PEARL),
                new ItemStack(Items.GHAST_TEAR));

        GameRegistry.addShapedRecipe(new ItemStack(Blocks.TNT),
                "ABA",
                "BAB",
                "ABA",
                'A', new ItemStack(Blocks.NETHERRACK),
                'B', new ItemStack(Blocks.SAND));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.GHAST_TEAR),
                new ItemStack(Items.BLAZE_POWDER),
                new ItemStack(Items.WATER_BUCKET));

        GameRegistry.addShapelessRecipe(new ItemStack(Items.SPIDER_EYE),
                new ItemStack(Items.ENDER_EYE),
                new ItemStack(Items.GHAST_TEAR));
    }
}
