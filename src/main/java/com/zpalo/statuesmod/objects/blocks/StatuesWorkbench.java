package com.zpalo.statuesmod.objects.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class StatuesWorkbench extends Block {
	
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	private static final VoxelShape SHAPE_N = Stream.of(
			Block.makeCuboidShape(1, 0, 15, 15, 1, 16),
			Block.makeCuboidShape(0, 0, 1, 1, 1, 15),
			Block.makeCuboidShape(1, 0, 0, 15, 1, 1),
			Block.makeCuboidShape(15, 0, 1, 16, 1, 15),
			Block.makeCuboidShape(15, 0, 0, 16, 13, 1),
			Block.makeCuboidShape(0, 0, 15, 1, 13, 16),
			Block.makeCuboidShape(0, 0, 0, 1, 13, 1),
			Block.makeCuboidShape(15, 0, 15, 16, 13, 16),
			Block.makeCuboidShape(1, 12, 15, 15, 13, 16),
			Block.makeCuboidShape(0, 12, 1, 1, 13, 15),
			Block.makeCuboidShape(1, 12, 0, 15, 13, 1),
			Block.makeCuboidShape(15, 12, 1, 16, 13, 15),
			Block.makeCuboidShape(1, 0, 1, 15, 13, 15),
			Block.makeCuboidShape(3.200000000000001, 5, 0.40000000000000036, 3.8000000000000007, 8, 0.8000000000000007),
			Block.makeCuboidShape(3, 8, 0.1999999999999993, 4, 11, 1),
			Block.makeCuboidShape(8, 13, 4, 12.5, 13.3, 6.5),
			Block.makeCuboidShape(9.5, 13, 3, 13, 13.3, 4),
			Block.makeCuboidShape(8.5, 13, 3.5, 9.5, 13.3, 4),
			Block.makeCuboidShape(9, 13, 7, 9.5, 13.3, 7.5),
			Block.makeCuboidShape(7, 13, 5, 7.5, 13.3, 5.5),
			Block.makeCuboidShape(10.5, 13, 7, 11, 13.3, 7.5),
			Block.makeCuboidShape(12, 13, 2.5, 12.5, 13.3, 3),
			Block.makeCuboidShape(13, 13, 3.5, 13.5, 13.3, 4),
			Block.makeCuboidShape(9.5, 13, 6.5, 10.5, 13.3, 8),
			Block.makeCuboidShape(11, 13, 2, 12, 13.3, 3),
			Block.makeCuboidShape(10, 13, 2.5, 11, 13.3, 3),
			Block.makeCuboidShape(12.5, 13, 5.5, 13.5, 13.3, 6),
			Block.makeCuboidShape(10.5, 13, 6.5, 12, 13.3, 7),
			Block.makeCuboidShape(14, 13, 4.5, 14.5, 13.3, 5),
			Block.makeCuboidShape(8.5, 13, 6.5, 9.5, 13.3, 7),
			Block.makeCuboidShape(7.5, 13, 4.5, 8, 13.3, 6),
			Block.makeCuboidShape(12.5, 13, 4, 14, 13.3, 5.5),
			Block.makeCuboidShape(7, 13, 14, 10, 16, 17),
			Block.makeCuboidShape(3, 13, 9, 5, 15, 11),
			Block.makeCuboidShape(2, 13, 10, 3, 14, 11),
			Block.makeCuboidShape(7, 5, 0.1999999999999993, 8, 11, 1),
			Block.makeCuboidShape(6, 4, 0, 9, 6, 1),
			Block.makeCuboidShape(2.9000000000000004, 9, 0, 4.1, 10, 1),
			Block.makeCuboidShape(6.9, 9, 0, 8.1, 10, 1)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	private static final VoxelShape SHAPE_E = Stream.of(
			Block.makeCuboidShape(0, 0, 1, 1, 1, 15),
			Block.makeCuboidShape(1, 0, 0, 15, 1, 1),
			Block.makeCuboidShape(15, 0, 1, 16, 1, 15),
			Block.makeCuboidShape(1, 0, 15, 15, 1, 16),
			Block.makeCuboidShape(15, 0, 15, 16, 13, 16),
			Block.makeCuboidShape(0, 0, 0, 1, 13, 1),
			Block.makeCuboidShape(15, 0, 0, 16, 13, 1),
			Block.makeCuboidShape(0, 0, 15, 1, 13, 16),
			Block.makeCuboidShape(0, 12, 1, 1, 13, 15),
			Block.makeCuboidShape(1, 12, 0, 15, 13, 1),
			Block.makeCuboidShape(15, 12, 1, 16, 13, 15),
			Block.makeCuboidShape(1, 12, 15, 15, 13, 16),
			Block.makeCuboidShape(1, 0, 1, 15, 13, 15),
			Block.makeCuboidShape(15.2, 5, 3.200000000000001, 15.6, 8, 3.8000000000000007),
			Block.makeCuboidShape(15, 8, 3, 15.8, 11, 4),
			Block.makeCuboidShape(9.5, 13, 8, 12, 13.3, 12.5),
			Block.makeCuboidShape(12, 13, 9.5, 13, 13.3, 13),
			Block.makeCuboidShape(12, 13, 8.5, 12.5, 13.3, 9.5),
			Block.makeCuboidShape(8.5, 13, 9, 9, 13.3, 9.5),
			Block.makeCuboidShape(10.5, 13, 7, 11, 13.3, 7.5),
			Block.makeCuboidShape(8.5, 13, 10.5, 9, 13.3, 11),
			Block.makeCuboidShape(13, 13, 12, 13.5, 13.3, 12.5),
			Block.makeCuboidShape(12, 13, 13, 12.5, 13.3, 13.5),
			Block.makeCuboidShape(8, 13, 9.5, 9.5, 13.3, 10.5),
			Block.makeCuboidShape(13, 13, 11, 14, 13.3, 12),
			Block.makeCuboidShape(13, 13, 10, 13.5, 13.3, 11),
			Block.makeCuboidShape(10, 13, 12.5, 10.5, 13.3, 13.5),
			Block.makeCuboidShape(9, 13, 10.5, 9.5, 13.3, 12),
			Block.makeCuboidShape(11, 13, 14, 11.5, 13.3, 14.5),
			Block.makeCuboidShape(9, 13, 8.5, 9.5, 13.3, 9.5),
			Block.makeCuboidShape(10, 13, 7.5, 11.5, 13.3, 8),
			Block.makeCuboidShape(10.5, 13, 12.5, 12, 13.3, 14),
			Block.makeCuboidShape(-1, 13, 7, 2, 16, 10),
			Block.makeCuboidShape(5, 13, 3, 7, 15, 5),
			Block.makeCuboidShape(5, 13, 2, 6, 14, 3),
			Block.makeCuboidShape(15, 5, 7, 15.8, 11, 8),
			Block.makeCuboidShape(15, 4, 6, 16, 6, 9),
			Block.makeCuboidShape(15, 9, 2.9000000000000004, 16, 10, 4.1),
			Block.makeCuboidShape(15, 9, 6.9, 16, 10, 8.1)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
			
	private static final VoxelShape SHAPE_W = Stream.of(
			Block.makeCuboidShape(15, 0, 1, 16, 1, 15),
			Block.makeCuboidShape(1, 0, 15, 15, 1, 16),
			Block.makeCuboidShape(0, 0, 1, 1, 1, 15),
			Block.makeCuboidShape(1, 0, 0, 15, 1, 1),
			Block.makeCuboidShape(0, 0, 0, 1, 13, 1),
			Block.makeCuboidShape(15, 0, 15, 16, 13, 16),
			Block.makeCuboidShape(0, 0, 15, 1, 13, 16),
			Block.makeCuboidShape(15, 0, 0, 16, 13, 1),
			Block.makeCuboidShape(15, 12, 1, 16, 13, 15),
			Block.makeCuboidShape(1, 12, 15, 15, 13, 16),
			Block.makeCuboidShape(0, 12, 1, 1, 13, 15),
			Block.makeCuboidShape(1, 12, 0, 15, 13, 1),
			Block.makeCuboidShape(1, 0, 1, 15, 13, 15),
			Block.makeCuboidShape(0.40000000000000036, 5, 12.2, 0.8000000000000007, 8, 12.799999999999999),
			Block.makeCuboidShape(0.1999999999999993, 8, 12, 1, 11, 13),
			Block.makeCuboidShape(4, 13, 3.5, 6.5, 13.3, 8),
			Block.makeCuboidShape(3, 13, 3, 4, 13.3, 6.5),
			Block.makeCuboidShape(3.5, 13, 6.5, 4, 13.3, 7.5),
			Block.makeCuboidShape(7, 13, 6.5, 7.5, 13.3, 7),
			Block.makeCuboidShape(5, 13, 8.5, 5.5, 13.3, 9),
			Block.makeCuboidShape(7, 13, 5, 7.5, 13.3, 5.5),
			Block.makeCuboidShape(2.5, 13, 3.5, 3, 13.3, 4),
			Block.makeCuboidShape(3.5, 13, 2.5, 4, 13.3, 3),
			Block.makeCuboidShape(6.5, 13, 5.5, 8, 13.3, 6.5),
			Block.makeCuboidShape(2, 13, 4, 3, 13.3, 5),
			Block.makeCuboidShape(2.5, 13, 5, 3, 13.3, 6),
			Block.makeCuboidShape(5.5, 13, 2.5, 6, 13.3, 3.5),
			Block.makeCuboidShape(6.5, 13, 4, 7, 13.3, 5.5),
			Block.makeCuboidShape(4.5, 13, 1.5, 5, 13.3, 2),
			Block.makeCuboidShape(6.5, 13, 6.5, 7, 13.3, 7.5),
			Block.makeCuboidShape(4.5, 13, 8, 6, 13.3, 8.5),
			Block.makeCuboidShape(4, 13, 2, 5.5, 13.3, 3.5),
			Block.makeCuboidShape(14, 13, 6, 17, 16, 9),
			Block.makeCuboidShape(9, 13, 11, 11, 15, 13),
			Block.makeCuboidShape(10, 13, 13, 11, 14, 14),
			Block.makeCuboidShape(0.1999999999999993, 5, 8, 1, 11, 9),
			Block.makeCuboidShape(0, 4, 7, 1, 6, 10),
			Block.makeCuboidShape(0, 9, 11.9, 1, 10, 13.1),
			Block.makeCuboidShape(0, 9, 7.9, 1, 10, 9.1)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	private static final VoxelShape SHAPE_S = Stream.of(
			Block.makeCuboidShape(1, 0, 0, 15, 1, 1),
			Block.makeCuboidShape(15, 0, 1, 16, 1, 15),
			Block.makeCuboidShape(1, 0, 15, 15, 1, 16),
			Block.makeCuboidShape(0, 0, 1, 1, 1, 15),
			Block.makeCuboidShape(0, 0, 15, 1, 13, 16),
			Block.makeCuboidShape(15, 0, 0, 16, 13, 1),
			Block.makeCuboidShape(15, 0, 15, 16, 13, 16),
			Block.makeCuboidShape(0, 0, 0, 1, 13, 1),
			Block.makeCuboidShape(1, 12, 0, 15, 13, 1),
			Block.makeCuboidShape(15, 12, 1, 16, 13, 15),
			Block.makeCuboidShape(1, 12, 15, 15, 13, 16),
			Block.makeCuboidShape(0, 12, 1, 1, 13, 15),
			Block.makeCuboidShape(1, 0, 1, 15, 13, 15),
			Block.makeCuboidShape(12.2, 5, 15.2, 12.799999999999999, 8, 15.6),
			Block.makeCuboidShape(12, 8, 15, 13, 11, 15.8),
			Block.makeCuboidShape(3.5, 13, 9.5, 8, 13.3, 12),
			Block.makeCuboidShape(3, 13, 12, 6.5, 13.3, 13),
			Block.makeCuboidShape(6.5, 13, 12, 7.5, 13.3, 12.5),
			Block.makeCuboidShape(6.5, 13, 8.5, 7, 13.3, 9),
			Block.makeCuboidShape(8.5, 13, 10.5, 9, 13.3, 11),
			Block.makeCuboidShape(5, 13, 8.5, 5.5, 13.3, 9),
			Block.makeCuboidShape(3.5, 13, 13, 4, 13.3, 13.5),
			Block.makeCuboidShape(2.5, 13, 12, 3, 13.3, 12.5),
			Block.makeCuboidShape(5.5, 13, 8, 6.5, 13.3, 9.5),
			Block.makeCuboidShape(4, 13, 13, 5, 13.3, 14),
			Block.makeCuboidShape(5, 13, 13, 6, 13.3, 13.5),
			Block.makeCuboidShape(2.5, 13, 10, 3.5, 13.3, 10.5),
			Block.makeCuboidShape(4, 13, 9, 5.5, 13.3, 9.5),
			Block.makeCuboidShape(1.5, 13, 11, 2, 13.3, 11.5),
			Block.makeCuboidShape(6.5, 13, 9, 7.5, 13.3, 9.5),
			Block.makeCuboidShape(8, 13, 10, 8.5, 13.3, 11.5),
			Block.makeCuboidShape(2, 13, 10.5, 3.5, 13.3, 12),
			Block.makeCuboidShape(6, 13, -1, 9, 16, 2),
			Block.makeCuboidShape(11, 13, 5, 13, 15, 7),
			Block.makeCuboidShape(13, 13, 5, 14, 14, 6),
			Block.makeCuboidShape(8, 5, 15, 9, 11, 15.8),
			Block.makeCuboidShape(7, 4, 15, 10, 6, 16),
			Block.makeCuboidShape(11.9, 9, 15, 13.1, 10, 16),
			Block.makeCuboidShape(7.9, 9, 15, 9.1, 10, 16)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	public StatuesWorkbench(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
		case NORTH:
			return SHAPE_N;
		case SOUTH:
			return SHAPE_S;
		case EAST:
			return SHAPE_E;
		case WEST:
			return SHAPE_W;
		default:
			return SHAPE_N;
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 * 
	 * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever
	 *             possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 * 
	 * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever
	 *             possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult result) {
		if (!worldIn.isRemote()) {
			ServerWorld serverWorld = (ServerWorld) worldIn;
			LightningBoltEntity entity = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
			serverWorld.addLightningBolt(entity);
		}
		return ActionResultType.SUCCESS;
	}
}
