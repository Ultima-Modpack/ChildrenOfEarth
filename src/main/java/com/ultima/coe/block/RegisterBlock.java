package com.ultima.coe.block;

import com.ultima.coe.block.basic.campfire.CampfireBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class RegisterBlock {
	
	public static CampfireBlock campfire = new CampfireBlock();
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				campfire
		);
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
				campfire.createItemBlock()
		);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModels() {
		campfire.registerItemModel(Item.getItemFromBlock(campfire));
	}
	
}
