package com.ultima.coe.api;

import java.util.ArrayList;
import java.util.List;

import com.ultima.coe.recipies.campfire.CampfireRecipe;
import com.ultima.coe.recipies.campfire.CampfireStarter;

import net.minecraft.item.ItemStack;

public class ChildrenOfEarthAPI {
	
	public static final List<ItemStack> campfireFuels = new ArrayList<ItemStack>();
	public static final List<CampfireStarter> campfireStarters = new ArrayList<CampfireStarter>();
	public static final List<CampfireRecipe> campfireRecipes = new ArrayList<CampfireRecipe>();
	
	/**
	 * Adds a new item to be accepted as a fuel and returns it
	 */
	public static ItemStack addCampfireFuel(ItemStack newFuel) {
		campfireFuels.add(newFuel);
		return newFuel;
	}
	
	/**
	 * Adds a new item to be accepted as a firestarter and returns it
	 * @param newStarter
	 * 		<code>Item</code> to be added as a firestarter
	 * @param infinite
	 * 		If <code>true</code>, the <code>newStarter</code> will not be consumed when starting a fire
	 */
	public static ItemStack addCampfireStartere(ItemStack newStarter, boolean infinite) {
		CampfireStarter cfs = new CampfireStarter(newStarter, infinite);
		campfireStarters.add(cfs);
		return newStarter;
	}
	
	/**
	 * Adds a new campfire roasting recipe and returns it
	 * @param input
	 * 		<code>Item</code> to be cooked
	 * @param output
	 * 		<code>Item</code> returned from cooking
	 */
	public static CampfireRecipe addCampfireRecipe(ItemStack input, ItemStack output) {
		CampfireRecipe cfr = new CampfireRecipe(input, output);
		campfireRecipes.add(cfr);
		return cfr;
	}
	
}
