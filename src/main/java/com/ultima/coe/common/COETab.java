package com.ultima.coe.common;

import com.ultima.coe.Reference;

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
