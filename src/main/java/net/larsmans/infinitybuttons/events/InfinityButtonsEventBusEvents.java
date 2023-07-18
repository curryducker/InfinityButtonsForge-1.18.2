package net.larsmans.infinitybuttons.events;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.particle.InfinityButtonsParticleTypes;
import net.larsmans.infinitybuttons.particle.custom.DiamondSparkleParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfinityButtons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InfinityButtonsEventBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event ) {
        Minecraft.getInstance().particleEngine.register(InfinityButtonsParticleTypes.DIAMOND_SPARKLE.get(), DiamondSparkleParticle.DiamondSparkleProvider::new);
    }
}
