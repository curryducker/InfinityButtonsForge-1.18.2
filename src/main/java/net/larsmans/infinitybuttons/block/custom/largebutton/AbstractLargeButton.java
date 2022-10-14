package net.larsmans.infinitybuttons.block.custom.largebutton;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public abstract class AbstractLargeButton extends FaceAttachedHorizontalDirectionalBlock {
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");

    private static final VoxelShape FLOOR_SHAPE = Block.box(4, 0, 4, 12, 2, 12);
    private static final VoxelShape FLOOR_PRESSED_SHAPE = Block.box(4, 0, 4, 12, 1, 12);
    private static final VoxelShape NORTH_SHAPE = Block.box(4, 4, 14, 12, 12, 16);
    private static final VoxelShape NORTH_PRESSED_SHAPE = Block.box(4, 4, 15, 12, 12, 16);
    private static final VoxelShape EAST_SHAPE = Block.box(0, 4, 4, 2, 12, 12);
    private static final VoxelShape EAST_PRESSED_SHAPE = Block.box(0, 4, 4, 1, 12, 12);
    private static final VoxelShape SOUTH_SHAPE = Block.box(4, 4, 0, 12, 12, 2);
    private static final VoxelShape SOUTH_PRESSED_SHAPE = Block.box(4, 4, 0, 12, 12, 1);
    private static final VoxelShape WEST_SHAPE = Block.box(14, 4, 4, 16, 12, 12);
    private static final VoxelShape WEST_PRESSED_SHAPE = Block.box(15, 4, 4, 16, 12, 12);
    private static final VoxelShape CEILING_SHAPE = Block.box(4, 14, 4, 12, 16, 12);
    private static final VoxelShape CEILING_PRESSED_SHAPE = Block.box(4, 15, 4, 12, 16, 12);
    private final boolean wooden;

    protected AbstractLargeButton(boolean isWooden, BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PRESSED, false).setValue(FACE, AttachFace.FLOOR));
        this.wooden = isWooden;
    }

    public abstract int getPressDuration();

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean flag = state.getValue(PRESSED);
        switch((AttachFace)state.getValue(FACE)) {
            case FLOOR:
                return flag ? FLOOR_PRESSED_SHAPE : FLOOR_SHAPE;
            case WALL:
                switch(direction) {
                    case EAST:
                        return flag ? EAST_PRESSED_SHAPE : EAST_SHAPE;
                    case WEST:
                        return flag ? WEST_PRESSED_SHAPE : WEST_SHAPE;
                    case SOUTH:
                        return flag ? SOUTH_PRESSED_SHAPE : SOUTH_SHAPE;
                    case NORTH:
                    default:
                        return flag ? NORTH_PRESSED_SHAPE : NORTH_SHAPE;
                }
            case CEILING:
            default:
                return flag ? CEILING_PRESSED_SHAPE : CEILING_SHAPE;
        }
    }

    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(PRESSED)) {
            return InteractionResult.CONSUME;
        } else {
            this.powerBlock(state, worldIn, pos);
            this.playSound(player, worldIn, pos, true);
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }
    }

    public void powerBlock(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, true), 3);
        this.updateNeighbors(state, world, pos);
        world.scheduleTick(pos, this, this.getPressDuration());
    }

    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean hitByArrow) {
        worldIn.playSound(hitByArrow ? playerIn : null, pos, this.getSoundEvent(hitByArrow), SoundSource.BLOCKS, 0.3F, hitByArrow ? 0.6F : 0.5F);
    }

    protected abstract SoundEvent getSoundEvent(boolean isOn);

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!isMoving && !state.is(newState.getBlock())) {
            if (state.getValue(PRESSED)) {
                this.updateNeighbors(state, worldIn, pos);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(PRESSED) ? 15 : 0;
    }

    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(PRESSED) && getConnectedDirection(blockState) == side ? 15 : 0;
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        if (state.getValue(PRESSED)) {
            if (this.wooden) {
                this.checkPressed(state, worldIn, pos);
            } else {
                worldIn.setBlock(pos, state.setValue(PRESSED, Boolean.FALSE), 3);
                this.updateNeighbors(state, worldIn, pos);
                this.playSound((Player)null, worldIn, pos, false);
            }

        }
    }

    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isClientSide && this.wooden && !state.getValue(PRESSED)) {
            this.checkPressed(state, worldIn, pos);
        }
    }

    private void checkPressed(BlockState state, Level worldIn, BlockPos pos) {
        List<? extends Entity> list = worldIn.getEntitiesOfClass(AbstractArrow.class, state.getShape(worldIn, pos).bounds().move(pos));
        boolean flag = !list.isEmpty();
        boolean flag1 = state.getValue(PRESSED);
        if (flag != flag1) {
            worldIn.setBlock(pos, state.setValue(PRESSED, flag), 3);
            this.updateNeighbors(state, worldIn, pos);
            this.playSound((Player)null, worldIn, pos, flag);
        }

        if (flag) {
            worldIn.scheduleTick(new BlockPos(pos), this, this.getPressDuration());
        }

    }

    public void updateNeighbors(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.updateNeighborsAt(pos, this);
        worldIn.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, PRESSED, FACE);
    }
}