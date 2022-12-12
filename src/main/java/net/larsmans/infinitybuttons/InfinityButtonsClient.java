package net.larsmans.infinitybuttons;

import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.compat.NethersDelightBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.TORCH_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.WALL_TORCH_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.TORCH_LEVER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.WALL_TORCH_LEVER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.SOUL_TORCH_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.SOUL_WALL_TORCH_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.SOUL_TORCH_LEVER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.SOUL_WALL_TORCH_LEVER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.REDSTONE_WALL_TORCH_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.REDSTONE_TORCH_LEVER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.REDSTONE_WALL_TORCH_LEVER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.RED_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.ORANGE_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.YELLOW_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.LIME_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.GREEN_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.CYAN_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.LIGHT_BLUE_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.BLUE_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.PURPLE_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.MAGENTA_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.PINK_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.BROWN_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.WHITE_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.LIGHT_GRAY_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.GRAY_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.BLACK_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.FANCY_SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            if (ModList.get().isLoaded("nethersdelight")) {
                ItemBlockRenderTypes.setRenderLayer(NethersDelightBlocks.PROPELPLANT_TORCH_BUTTON.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(NethersDelightBlocks.PROPELPLANT_WALL_TORCH_BUTTON.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(NethersDelightBlocks.PROPELPLANT_TORCH_LEVER.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(NethersDelightBlocks.PROPELPLANT_WALL_TORCH_LEVER.get(), RenderType.cutout());
            }
        });
    }
}

