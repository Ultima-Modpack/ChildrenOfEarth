package com.ultima.coe.recipies;

import com.ultima.coe.api.ChildrenOfEarthAPI;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RegisterCampfireRecipe {

	public static void init() {
		//Fuel
		ChildrenOfEarthAPI.addCampfireFuel(new ItemStack(Item.getByNameOrId("minecraft:log")));
		ChildrenOfEarthAPI.addCampfireFuel(new ItemStack(Item.getByNameOrId("minecraft:log2")));
		ChildrenOfEarthAPI.addCampfireFuel(new ItemStack(Item.getByNameOrId("minecraft:planks")));
		ChildrenOfEarthAPI.addCampfireFuel(new ItemStack(Item.getByNameOrId("minecraft:stick")));
		
		//Starter
		ChildrenOfEarthAPI.addCampfireStarter(new ItemStack(Item.getByNameOrId("minecraft:stick")), false);
		ChildrenOfEarthAPI.addCampfireStarter(new ItemStack(Item.getByNameOrId("minecraft:tallgrass")), false);
		ChildrenOfEarthAPI.addCampfireStarter(new ItemStack(Item.getByNameOrId("minecraft:flint_and_steel")), true);
		
		//Food
		ChildrenOfEarthAPI.addCampfireRecipe(new ItemStack(Item.getByNameOrId("minecraft:porkchop")), new ItemStack(Item.getByNameOrId("minecraft:cooked_porkchop")));
		ChildrenOfEarthAPI.addCampfireRecipe(new ItemStack(Item.getByNameOrId("minecraft:fish")), new ItemStack(Item.getByNameOrId("minecraft:cooked_fish")));
		ChildrenOfEarthAPI.addCampfireRecipe(new ItemStack(Item.getByNameOrId("minecraft:fish"),0,1), new ItemStack(Item.getByNameOrId("minecraft:cooked_fish"),0,1));
		ChildrenOfEarthAPI.addCampfireRecipe(new ItemStack(Item.getByNameOrId("minecraft:beef")), new ItemStack(Item.getByNameOrId("minecraft:cooked_beef")));
		ChildrenOfEarthAPI.addCampfireRecipe(new ItemStack(Item.getByNameOrId("minecraft:chicken")), new ItemStack(Item.getByNameOrId("minecraft:cooked_chicken")));
		ChildrenOfEarthAPI.addCampfireRecipe(new ItemStack(Item.getByNameOrId("minecraft:rabbit")), new ItemStack(Item.getByNameOrId("minecraft:cooked_rabbit")));
		ChildrenOfEarthAPI.addCampfireRecipe(new ItemStack(Item.getByNameOrId("minecraft:mutton")), new ItemStack(Item.getByNameOrId("minecraft:cooked_mutton")));
	}
	
}
