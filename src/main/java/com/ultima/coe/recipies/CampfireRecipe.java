package com.ultima.coe.recipies;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

public class CampfireRecipe {
	
	private ItemStack input;
	private ItemStack output;
	
	public CampfireRecipe(ItemStack input, ItemStack output) {
		this.input = input;
		this.output = output;
	}
	
	@Nullable
	public boolean matches(ItemStack stack) {
		return stack.isItemEqual(input);
	}
	
	public ItemStack getOutput() {
		return output;
	}

}
