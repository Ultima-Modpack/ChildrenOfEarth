package com.ultima.coe.block.basic.world;

import java.util.Random;

import com.ultima.coe.Reference;
import com.ultima.coe.block.BlockBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class RockClumpBlock extends BlockBase{

	public RockClumpBlock() {
		super(Material.ROCK, "rockclump");
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getByNameOrId(Reference.MOD_ID + ":pebble");
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
