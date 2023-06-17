package net.larsmans.infinitybuttons;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.compat.*;
import net.larsmans.infinitybuttons.config.InfinityButtonsConfig;
import net.larsmans.infinitybuttons.config.InfinityButtonsConfigMenu;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.larsmans.infinitybuttons.network.IBPacketHandler;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(InfinityButtons.MOD_ID)
public class InfinityButtons
{
    public static final String MOD_ID = "infinitybuttons";

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public InfinityButtons() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InfinityButtonsItems.register(eventBus);
        InfinityButtonsBlocks.register(eventBus);
        InfinityButtonsSounds.register(eventBus);

        if (ModList.get().isLoaded("nethersdelight")) {
            NethersDelightItems.registerCompatItems();
            NethersDelightBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("quark")){
            QuarkBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("carpenter")){
            CarpenterBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("woodworks")){
            WoodworksBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("clayworks")){
            ClayworksBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("environmental")){
            EnvironmentalBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("autumnity")){
            AutumnityBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("atmospheric")){
            AtmosphericBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("upgrade_aquatic")){
            UpgradeAquaticBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("buzzier_bees")){
            BuzzierBeesBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("neapolitan")){
            NeapolitanBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("savage_and_ravage")){
            SavageAndRavageBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("create")){
            CreateBlocks.registerCompatBlocks();
        }

        AutoConfig.register(InfinityButtonsConfig.class, Toml4jConfigSerializer::new);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InfinityButtonsConfigMenu::registerConfigMenu);

        // Register the setup method for modloading
        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        IBPacketHandler.register();
    }
}
