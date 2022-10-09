package net.larsmans.infinitybuttons;

import net.larsmans.infinitybuttons.block.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class InfinityButtonsClient {
    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TORCH_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TORCH_LEVER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOUL_TORCH_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SOUL_TORCH_LEVER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.REDSTONE_TORCH_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.REDSTONE_WALL_TORCH_BUTTON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.REDSTONE_TORCH_LEVER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.REDSTONE_WALL_TORCH_LEVER.get(), RenderType.cutout());
        });
    }
}

