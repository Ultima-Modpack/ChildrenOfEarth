package com.ultima.coe.recipies.campfire;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

public class CampfireRecipe {
	
	private ItemStack input;
	private ItemStack output;
	private int amt;
	
	public CampfireRecipe(ItemStack input, ItemStack output) {
		this(input, output, 1);
	}
	
	public CampfireRecipe(ItemStack input, ItemStack output, int amt) {
		this.input = input;
		this.output = output;
		this.amt = amt;
	}
	
	@Nullable
	public boolean matches(ItemStack stack) {
		return stack.isItemEqual(input);
	}
	
	public ItemStack getOutput() {
		return output;
	}
	
	public int getAmt() {
		return amt;
	}

}
