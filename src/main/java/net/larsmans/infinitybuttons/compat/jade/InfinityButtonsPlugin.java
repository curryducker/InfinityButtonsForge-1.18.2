package net.larsmans.infinitybuttons.compat.jade;

import mcp.mobius.waila.api.*;
import mcp.mobius.waila.api.event.WailaRayTraceEvent;
import mcp.mobius.waila.impl.config.PluginConfig;
import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.block.custom.HoglinMountButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.AbstractSecretButton;
import net.larsmans.infinitybuttons.block.custom.torch.RedstoneTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.TorchButton;
import net.larsmans.infinitybuttons.network.IBClientPacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.ForgeRegistries;

@WailaPlugin
public class InfinityButtonsPlugin implements IWailaPlugin {

    private boolean hidden(ResourceLocation config) {
        if (IBClientPacketHandler.getForceHidden())
            return true;
        else
            return PluginConfig.INSTANCE.get(config);
    }

    static final ResourceLocation CONFIG_HIDE_SECRET_BUTTONS = new ResourceLocation(InfinityButtons.MOD_ID, "hide_secret_buttons");
    static final ResourceLocation CONFIG_HIDE_TORCH_BUTTONS = new ResourceLocation(InfinityButtons.MOD_ID, "hide_torch_buttons");

    private static Block HOGLIN_MOUNT = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("nethersdelight", "hoglin_mount"));

    private static IWailaClientRegistration client;

    public InfinityButtonsPlugin() {
        MinecraftForge.EVENT_BUS.addListener(this::overrideSecrets);
    }

    public void overrideSecrets(WailaRayTraceEvent event) {
        Accessor<?> accessor = event.getAccessor();
        if (accessor instanceof BlockAccessor blockAccessor) {
            if (hidden(CONFIG_HIDE_SECRET_BUTTONS) && blockAccessor.getBlock() instanceof AbstractSecretButton secretButton) {
                accessor = client.createBlockAccessor(
                        secretButton.jadeBlock.defaultBlockState(),
                        null,
                        accessor.getLevel(),
                        accessor.getPlayer(),
                        null,
                        blockAccessor.getHitResult(),
                        accessor.isServerConnected()
                );
                event.setAccessor(accessor);
            }

            if (hidden(CONFIG_HIDE_SECRET_BUTTONS) && blockAccessor.getBlock() instanceof HoglinMountButton) {
                accessor = client.createBlockAccessor(
                        HOGLIN_MOUNT.defaultBlockState(),
                        null,
                        accessor.getLevel(),
                        accessor.getPlayer(),
                        null,
                        blockAccessor.getHitResult(),
                        accessor.isServerConnected()
                );
                event.setAccessor(accessor);
            }

            if (hidden(CONFIG_HIDE_TORCH_BUTTONS) && blockAccessor.getBlock() instanceof TorchButton torchButton) {
                accessor = client.createBlockAccessor(
                        torchButton.jadeBlock.defaultBlockState(),
                        null,
                        accessor.getLevel(),
                        accessor.getPlayer(),
                        null,
                        blockAccessor.getHitResult(),
                        accessor.isServerConnected()
                );
                event.setAccessor(accessor);
            }

            if (hidden(CONFIG_HIDE_TORCH_BUTTONS) && blockAccessor.getBlock() instanceof RedstoneTorchButton redstoneTorchButton) {
                accessor = client.createBlockAccessor(
                        redstoneTorchButton.jadeBlock.defaultBlockState(),
                        null,
                        accessor.getLevel(),
                        accessor.getPlayer(),
                        null,
                        blockAccessor.getHitResult(),
                        accessor.isServerConnected()
                );
                event.setAccessor(accessor);
            }
        }
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        client = registration;
    }

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.addSyncedConfig(CONFIG_HIDE_SECRET_BUTTONS, true);
        registration.addSyncedConfig(CONFIG_HIDE_TORCH_BUTTONS, true);
    }
}
