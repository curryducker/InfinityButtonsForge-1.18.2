package net.larsmans.infinitybuttons;

import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
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
            ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.SAFE_EMERGENCY_BUTTON.get(), RenderType.cutout());
            if (ModList.get().isLoaded("nethersdelight")) {
                ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.PROPELPLANT_TORCH_BUTTON.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.PROPELPLANT_WALL_TORCH_BUTTON.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.PROPELPLANT_TORCH_LEVER.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(InfinityButtonsBlocks.PROPELPLANT_WALL_TORCH_LEVER.get(), RenderType.cutout());
            }
        });
    }
}

