package net.larsmans.infinitybuttons.block.custom.torch;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;
import java.util.Random;

public class WallTorchButton extends TorchButton{
    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.5, 3.0, 11.0, 10.5, 13.0, 16.0), Direction.SOUTH, Block.box(5.5, 3.0, 0.0, 10.5, 13.0, 5.0), Direction.WEST, Block.box(11.0, 3.0, 5.5, 16.0, 13.0, 10.5), Direction.EAST, Block.box(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)));

    public WallTorchButton(Properties properties, ParticleOptions particleData) {
        super(properties, particleData);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH).setValue(PRESSED, false));
    }

    @Override
    public String getDescriptionId() {
        return this.asItem().getDescriptionId();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return WallTorchButton.getShape(state);
    }

    public static VoxelShape getShape(BlockState state) {
        return BOUNDING_SHAPES.get(state.getValue(FACING));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockPos = pos.relative(direction.getOpposite());
        BlockState blockState = worldIn.getBlockState(blockPos);
        return blockState.isFaceSturdy(worldIn, blockPos, direction);
    }

    // override because direction
    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (blockState.getValue(PRESSED) && blockState.getValue(FACING) == side) {
            return 15;
        }
        return 0;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        Direction direction = stateIn.getValue(FACING);
        if (stateIn.getValue(PRESSED)) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + 0.6D;
            double d2 = (double) pos.getZ() + 0.5D;
            Direction direction2 = direction.getOpposite();
            worldIn.addParticle(ParticleTypes.SMOKE,
                    d0 + 0.05D * (double) direction2.getStepX(),
                    d1 + 0.15D,
                    d2 + 0.05D * (double) direction2.getStepZ(),
                    0.0D, 0.0D, 0.0D);
            worldIn.addParticle(this.flameParticle,
                    d0 + 0.05D * (double) direction2.getStepX(),
                    d1 + 0.15D,
                    d2 + 0.05D * (double) direction2.getStepZ(),
                    0.0D, 0.0D, 0.0D);
        } else {
            // copy of vanilla wall torch
            double d = (double)pos.getX() + 0.5;
            double e = (double)pos.getY() + 0.7;
            double f = (double)pos.getZ() + 0.5;
            Direction direction2 = direction.getOpposite();
            worldIn.addParticle(ParticleTypes.SMOKE, d + 0.27 * (double)direction2.getStepX(), e + 0.22, f + 0.27 * (double)direction2.getStepZ(), 0.0, 0.0, 0.0);
            worldIn.addParticle(this.flameParticle, d + 0.27 * (double)direction2.getStepX(), e + 0.22, f + 0.27 * (double)direction2.getStepZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void updateNeighbors(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.updateNeighborsAt(pos, this);
        worldIn.updateNeighborsAt(pos.relative(state.getValue(FACING).getOpposite()), this);
    }
}
