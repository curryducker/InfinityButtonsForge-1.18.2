package net.larsmans.infinitybuttons.block.custom.largebutton;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class StoneLargeButton extends AbstractLargeButton {
    public StoneLargeButton(BlockBehaviour.Properties properties) {
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
}
