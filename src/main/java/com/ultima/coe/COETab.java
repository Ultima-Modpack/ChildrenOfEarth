package com.ultima.coe;

import com.ultima.coe.block.RegisterBlock;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class COETab extends CreativeTabs {
	
	public COETab() {
		super(Reference.MOD_ID);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(RegisterBlock.campfire));
	}

}
