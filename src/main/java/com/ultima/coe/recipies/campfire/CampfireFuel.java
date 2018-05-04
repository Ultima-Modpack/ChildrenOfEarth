package com.ultima.coe.recipies.campfire;

import net.minecraft.item.ItemStack;

public class CampfireFuel {
	
	private ItemStack fuel;
	private int fuelval;
	
	public CampfireFuel(ItemStack fuel, int fuelval) {
		this.fuel = fuel;
		this.fuelval = fuelval;
	}
	
	public boolean matches(ItemStack other) {
		return fuel.isItemEqual(other);
	}
	
	public int getFuelVal() {
		return this.fuelval;
	}

}
