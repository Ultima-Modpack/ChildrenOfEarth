package com.ultima.coe.recipies.campfire;

import net.minecraft.item.ItemStack;

public class CampfireStarter {
	
	private final ItemStack starter;
	private final boolean infinite;
	
	public CampfireStarter(ItemStack starter, boolean infinite) {
		this.starter = starter;
		this.infinite = infinite;
	}
	
	public boolean isInfinite() {
		return infinite;
	}
	
	public boolean matches(ItemStack stack) {
		return stack.isItemEqual(starter);
	}

}
