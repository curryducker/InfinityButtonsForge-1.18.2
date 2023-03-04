package net.larsmans.infinitybuttons.block.custom.button;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class StoneButton extends AbstractButton{
    public StoneButton(Properties properties, boolean large) {
        super(false, large, properties);
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
