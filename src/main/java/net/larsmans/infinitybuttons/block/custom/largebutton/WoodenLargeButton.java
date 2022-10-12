package net.larsmans.infinitybuttons.block.custom.largebutton;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class WoodenLargeButton extends AbstractLargeButton {
    public WoodenLargeButton(BlockBehaviour.Properties properties) {
        super(true, properties);
    }

    @Override
    public int getPressDuration() {
        return 30;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return isOn ? SoundEvents.WOODEN_BUTTON_CLICK_ON : SoundEvents.WOODEN_BUTTON_CLICK_OFF;
    }
}
