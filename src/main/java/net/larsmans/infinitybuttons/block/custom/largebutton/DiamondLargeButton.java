package net.larsmans.infinitybuttons.block.custom.largebutton;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.InfinityButtonsConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;

public class DiamondLargeButton extends AbstractLargeButton{
    InfinityButtonsConfig config = AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig();

    public DiamondLargeButton(Properties properties) {
        super(false, properties);
    }

    @Override
    public int getPressDuration() {
        return 20;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return isOn ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(3) == 0) {
            switch (stateIn.getValue(FACE)) {
                case FLOOR -> worldIn.addParticle(ParticleTypes.SCRAPE,
                        (double) pos.getX() + 0.1875 + (double) rand.nextFloat() * 0.625,
                        (double) pos.getY() + 0.125 + (double) rand.nextFloat() * 0.0625,
                        (double) pos.getZ() + 0.1875 + (double) rand.nextFloat() * 0.625,
                        0, 0, 0);
                case WALL -> {
                    switch (stateIn.getValue(FACING)) {
                        case NORTH -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                (double) pos.getX() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                (double) pos.getY() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                (double) pos.getZ() + 0.8125 + (double) rand.nextFloat() * 0.0625,
                                0, 0, 0);

                        case EAST -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                (double) pos.getX() + 0.125 + (double) rand.nextFloat() * 0.0625,
                                (double) pos.getY() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                (double) pos.getZ() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                0, 0, 0);

                        case SOUTH -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                (double) pos.getX() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                (double) pos.getY() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                (double) pos.getZ() + 0.125 + (double) rand.nextFloat() * 0.0625,
                                0, 0, 0);

                        case WEST -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                (double) pos.getX() + 0.8125 + (double) rand.nextFloat() * 0.0625,
                                (double) pos.getY() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                (double) pos.getZ() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                0, 0, 0);
                    }
                }
                case CEILING -> worldIn.addParticle(ParticleTypes.SCRAPE,
                        (double) pos.getX() + 0.1875 + (double) rand.nextFloat() * 0.625,
                        (double) pos.getY() + 0.8125 + (double) rand.nextFloat() * 0.0625,
                        (double) pos.getZ() + 0.1875 + (double) rand.nextFloat() * 0.625,
                        0, 0, 0);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if (config.tooltips) {
            if(Screen.hasShiftDown()) {
                pTooltip.add(new TranslatableComponent("infinitybuttons.tooltip.diamond_button"));
            } else {
                pTooltip.add(new TranslatableComponent("infinitybuttons.tooltip.hold_shift"));
            }
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}