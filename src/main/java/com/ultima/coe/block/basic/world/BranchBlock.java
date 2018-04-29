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
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		ItemStack stack = new ItemStack(Item.getByNameOrId("minecraft:stick"));
		world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack));
	}
	
	@Override
    public int quantityDropped(Random random)
    {
        return 0;
    }
}