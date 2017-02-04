package victinix.peacefuldrops.lib;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {

    public static void init() {

        GameRegistry.addShapelessRecipe(new ItemStack(Items.BLAZE_ROD),
                new ItemStack(Items.STICK),
                new ItemStack(Items.LAVA_BUCKET));
    }
}
