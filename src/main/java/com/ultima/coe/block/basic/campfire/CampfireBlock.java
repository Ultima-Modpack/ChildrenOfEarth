package com.ultima.coe.block.basic.campfire;

import javax.annotation.Nullable;

import com.ultima.coe.block.BlockTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CampfireBlock extends BlockTileEntity<CampfireTileEntity> {
	
	public CampfireBlock() {
		super(Material.FIRE, "campfire");
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(!world.isRemote) {
			CampfireTileEntity tile = getTileEntity(world, pos);
			// TODO process the tileEntity
			tile.equals(tile);
		}
		
		return true;
		
	}
	
	@Override
	public Class<CampfireTileEntity> getTileEntityClass() {
		return CampfireTileEntity.class;
	}
	
	@Nullable
	@Override
	public CampfireTileEntity createTileEntity(World world, IBlockState state) {
		return new CampfireTileEntity();
	}
	
	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}
