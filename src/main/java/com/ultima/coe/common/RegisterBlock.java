package com.ultima.coe.common;

import com.ultima.coe.block.basic.campfire.CampfireBlock;
import com.ultima.coe.block.basic.world.BranchBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class RegisterBlock {
	
	public static CampfireBlock campfire = new CampfireBlock();
	public static BranchBlock branch = new BranchBlock();
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				campfire,
				branch
		);
		
		GameRegistry.registerTileEntity(campfire.getTileEntityClass(), campfire.getRegistryName().toString());
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
				campfire.createItemBlock(),
				branch.createItemBlock()
		);
	}
	
	public static void registerModels() {
		campfire.registerItemModel(Item.getItemFromBlock(campfire));
		branch.registerItemModel(Item.getItemFromBlock(branch));
	}
	
}
