package net.larsmans.infinitybuttons.block.custom.emergencybutton;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.advancement.InfinityButtonsTriggers;
import net.larsmans.infinitybuttons.block.custom.button.AbstractButton;
import net.larsmans.infinitybuttons.config.AlarmEnum;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmergencyButton extends AbstractButton {
    
    private static final VoxelShape STONE_DOWN  = Block.box(4, 0, 4, 12, 1, 12);
    private static final VoxelShape STONE_UP    = Block.box(4, 15, 4, 12, 16, 12);
    private static final VoxelShape STONE_NORTH = Block.box(4, 4, 15, 12, 12, 16);
    private static final VoxelShape STONE_EAST  = Block.box(0, 4, 4, 1, 12, 12);
    private static final VoxelShape STONE_SOUTH = Block.box(4, 4, 0, 12, 12, 1);
    private static final VoxelShape STONE_WEST  = Block.box(15, 4, 4, 16, 12, 12);

    private static final VoxelShape FLOOR_SHAPE = Shapes.or(
            Block.box(5, 1, 5, 11, 5, 11), STONE_DOWN).optimize();
    private static final VoxelShape FLOOR_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 1, 5, 11, 3, 11), STONE_DOWN).optimize();
    private static final VoxelShape NORTH_SHAPE = Shapes.or(
            Block.box(5, 5, 11, 11, 11, 15), STONE_NORTH).optimize();
    private static final VoxelShape NORTH_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 5, 13, 11, 11, 15), STONE_NORTH).optimize();
    private static final VoxelShape EAST_SHAPE = Shapes.or(
            Block.box(1, 5, 5, 5, 11, 11), STONE_EAST).optimize();
    private static final VoxelShape EAST_PRESSED_SHAPE = Shapes.or(
            Block.box(1, 5, 5, 3, 11, 11), STONE_EAST).optimize();
    private static final VoxelShape SOUTH_SHAPE = Shapes.or(
            Block.box(5, 5, 1, 11, 11, 5), STONE_SOUTH).optimize();
    private static final VoxelShape SOUTH_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 5, 1, 11, 11, 3), STONE_SOUTH).optimize();
    private static final VoxelShape WEST_SHAPE = Shapes.or(
            Block.box(11, 5, 5, 15, 11, 11), STONE_WEST).optimize();
    private static final VoxelShape WEST_PRESSED_SHAPE = Shapes.or(
            Block.box(13, 5, 5, 15, 11, 11), STONE_WEST).optimize();
    private static final VoxelShape CEILING_SHAPE = Shapes.or(
            Block.box(5, 11, 5, 11, 15, 11), STONE_UP).optimize();
    private static final VoxelShape CEILING_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 13, 5, 11, 15, 11), STONE_UP).optimize();

    public EmergencyButton(Properties properties) {
        super(false, properties);
    }

    @Override
    public int getPressDuration() {
        return 10;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean flag = state.getValue(PRESSED);
        switch(state.getValue(FACE)) {
            case FLOOR -> {
                return flag ? FLOOR_PRESSED_SHAPE : FLOOR_SHAPE;
            }
            case WALL -> {
                switch (direction) {
                    case EAST -> {
                        return flag ? EAST_PRESSED_SHAPE : EAST_SHAPE;
                    }
                    case WEST -> {
                        return flag ? WEST_PRESSED_SHAPE : WEST_SHAPE;
                    }
                    case SOUTH -> {
                        return flag ? SOUTH_PRESSED_SHAPE : SOUTH_SHAPE;
                    }
                }
                return flag ? NORTH_PRESSED_SHAPE : NORTH_SHAPE;
            }
        }
        return flag ? CEILING_PRESSED_SHAPE : CEILING_SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(PRESSED)) {
            return InteractionResult.CONSUME;
        }
        this.powerBlock(state, worldIn, pos);
        this.playSound(player, worldIn, pos, true);
        if (config.alarmSoundType != AlarmEnum.OFF) {
            emergencySound(worldIn, pos, player);
        }
        if (player instanceof ServerPlayer) {
            InfinityButtonsTriggers.EMERGENCY_TRIGGER.trigger((ServerPlayer) player);
        }
        if (!worldIn.isClientSide && config.alarmVillagerPanic) {
            List<LivingEntity> villagers = worldIn.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(config.alarmSoundRange), entity -> entity.getType() == EntityType.VILLAGER);
            for (LivingEntity villager : villagers) {
                if (villager instanceof Villager villagerEntity) {
                    villagerEntity.getBrain().setMemory(MemoryModuleType.HEARD_BELL_TIME, worldIn.getGameTime());
                }
            }
        }
        worldIn.gameEvent(player, GameEvent.BLOCK_PRESS, pos);
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    @Override
    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean pressed) {
        worldIn.playSound(pressed ? playerIn : null, pos, this.getSoundEvent(pressed), SoundSource.BLOCKS, 1, pressed ? 0.6f : 0.5f);
    }

    @Override
    protected SoundEvent getSoundEvent(boolean pressed) {
        return SoundEvents.BONE_BLOCK_BREAK;
    }

    public static void emergencySound(Level level, BlockPos pos, Player player) {
        if (config.alarmSoundType == AlarmEnum.GLOBAL) {
            InfinityButtonsUtil.playGlobalSound(level, pos, InfinityButtonsSounds.ALARM.get(), SoundSource.BLOCKS);
        } else {
            level.playSound(player, pos, InfinityButtonsSounds.ALARM.get(), SoundSource.BLOCKS, (float)config.alarmSoundRange / 16.0F, 1);
        }
    }
}