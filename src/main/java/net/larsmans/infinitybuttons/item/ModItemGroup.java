package net.larsmans.infinitybuttons.item;

import net.larsmans.infinitybuttons.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup {

    public static final CreativeModeTab INFINITYBUTTONS = new CreativeModeTab("infinityButtonsTab")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModBlocks.OAK_LARGE_BUTTON.get());
        }
    };

    public static final CreativeModeTab INFINITYBUTTONSCOMPAT = new CreativeModeTab("infinityButtonsCompatTab")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModBlocks.RED_EMERGENCY_BUTTON.get());
        }
    };

}
