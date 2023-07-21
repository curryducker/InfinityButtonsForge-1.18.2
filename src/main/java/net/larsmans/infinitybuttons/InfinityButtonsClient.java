package net.larsmans.infinitybuttons;

import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.compat.NethersDelightBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class InfinityButtonsClient {
    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            cutout(InfinityButtonsBlocks.TORCH_BUTTON.get());
            cutout(InfinityButtonsBlocks.WALL_TORCH_BUTTON.get());
            cutout(InfinityButtonsBlocks.TORCH_LEVER.get());
            cutout(InfinityButtonsBlocks.WALL_TORCH_LEVER.get());
            cutout(InfinityButtonsBlocks.SOUL_TORCH_BUTTON.get());
            cutout(InfinityButtonsBlocks.SOUL_WALL_TORCH_BUTTON.get());
            cutout(InfinityButtonsBlocks.SOUL_TORCH_LEVER.get());
            cutout(InfinityButtonsBlocks.SOUL_WALL_TORCH_LEVER.get());
            cutout(InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON.get());
            cutout(InfinityButtonsBlocks.REDSTONE_WALL_TORCH_BUTTON.get());
            cutout(InfinityButtonsBlocks.REDSTONE_TORCH_LEVER.get());
            cutout(InfinityButtonsBlocks.REDSTONE_WALL_TORCH_LEVER.get());
            cutout(InfinityButtonsBlocks.RED_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.ORANGE_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.YELLOW_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.LIME_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.GREEN_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.CYAN_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.LIGHT_BLUE_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.BLUE_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.PURPLE_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.MAGENTA_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.PINK_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.BROWN_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.WHITE_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.LIGHT_GRAY_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.GRAY_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.BLACK_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.FANCY_SAFE_EMERGENCY_BUTTON.get());
            cutout(InfinityButtonsBlocks.LETTER_BUTTON.get());
            cutout(InfinityButtonsBlocks.LETTER_LEVER.get());
            cutout(InfinityButtonsBlocks.LANTERN_BUTTON.get());
            cutout(InfinityButtonsBlocks.LANTERN_LEVER.get());
            cutout(InfinityButtonsBlocks.SOUL_LANTERN_BUTTON.get());
            cutout(InfinityButtonsBlocks.SOUL_LANTERN_BUTTON.get());
            cutout(InfinityButtonsBlocks.SMALL_CONSOLE_BUTTON.get());
            cutout(InfinityButtonsBlocks.SMALL_CONSOLE_LEVER.get());
            cutout(InfinityButtonsBlocks.CONSOLE_BUTTON.get());
            cutout(InfinityButtonsBlocks.CONSOLE_LEVER.get());
            cutout(InfinityButtonsBlocks.LARGE_CONSOLE_BUTTON.get());
            cutout(InfinityButtonsBlocks.LARGE_CONSOLE_LEVER.get());
            cutout(InfinityButtonsBlocks.BIG_CONSOLE_BUTTON.get());
            cutout(InfinityButtonsBlocks.BIG_CONSOLE_LEVER.get());
            if (ModList.get().isLoaded("nethersdelight")) {
                cutout(NethersDelightBlocks.PROPELPLANT_TORCH_BUTTON.get());
                cutout(NethersDelightBlocks.PROPELPLANT_WALL_TORCH_BUTTON.get());
                cutout(NethersDelightBlocks.PROPELPLANT_TORCH_LEVER.get());
                cutout(NethersDelightBlocks.PROPELPLANT_WALL_TORCH_LEVER.get());
            }
        });
    }
    
    private static void cutout(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
    }
}

