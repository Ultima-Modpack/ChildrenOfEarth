package com.ultima.coe.block.basic.world;

import java.util.Random;

import com.ultima.coe.block.BlockBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BranchBlock extends BlockBase {

	public BranchBlock() {
		super(Material.WOOD, "branch");
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getByNameOrId("minecraft:stick");
	}
}