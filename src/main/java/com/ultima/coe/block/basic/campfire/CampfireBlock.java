package com.ultima.coe.block.basic.campfire;

import com.ultima.coe.block.BlockBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class CampfireBlock extends BlockBase {
	
	public CampfireBlock() {
		super(Material.FIRE, "campfire");
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
