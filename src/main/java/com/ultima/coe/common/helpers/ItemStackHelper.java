package com.ultima.coe.common.helpers;

import net.minecraft.item.ItemStack;

public class ItemStackHelper {

	
	ItemStack shrinkStack(ItemStack stack, int amount) {
		stack.setCount(stack.getCount()-amount);
		return stack;
	}
}
