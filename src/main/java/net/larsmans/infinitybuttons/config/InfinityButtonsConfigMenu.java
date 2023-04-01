package net.larsmans.infinitybuttons.config;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.ModLoadingContext;

@OnlyIn(Dist.CLIENT)
public class InfinityButtonsConfigMenu {
    public static void registerConfigMenu() {
        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory((client, parent) -> AutoConfig.getConfigScreen(InfinityButtonsConfig.class, parent).get()));
    }
}
