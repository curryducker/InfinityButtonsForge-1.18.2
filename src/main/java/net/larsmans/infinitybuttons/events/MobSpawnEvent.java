package net.larsmans.infinitybuttons.events;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.item.custom.SafeEmergencyButtonItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

import static net.larsmans.infinitybuttons.InfinityButtonsUtil.SAFETY_BUTTONS;

public class MobSpawnEvent {

    @SubscribeEvent
    public static void onMobSpawn(LivingSpawnEvent.SpecialSpawn event) {
        InfinityButtonsUtil.buildSafety();
        Mob entity = (Mob) event.getEntity();

        if (entity instanceof AbstractSkeleton && !(entity instanceof WitherSkeleton) || entity instanceof Zombie && !(entity instanceof ZombifiedPiglin || entity instanceof Drowned)) {
            Random random = event.getWorld().getRandom();
            double chance = 0.001;

            if (random.nextDouble() < chance) {
                entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(SAFETY_BUTTONS.get(random.nextInt(SAFETY_BUTTONS.size()))));
            }
        }
    }

    @SubscribeEvent
    public static void onDeathSpecialEvent(LivingDropsEvent event) {
        LivingEntity entity = (LivingEntity) event.getEntity();
        BlockPos pos = entity.blockPosition();

        if (!(entity instanceof Zombie || entity instanceof Skeleton)) {
            return;
        }

        ItemStack stack = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (stack.getItem() instanceof SafeEmergencyButtonItem) {
            ItemEntity itemEntity1 = new ItemEntity(entity.level, pos.getX(), pos.getY(), pos.getZ(), stack);
            // Remove chance
            event.getDrops().removeIf(itemEntity -> itemEntity.getItem().getItem() instanceof SafeEmergencyButtonItem);
            // Drop guaranteed
            event.getDrops().add(itemEntity1);
        }
    }
}
