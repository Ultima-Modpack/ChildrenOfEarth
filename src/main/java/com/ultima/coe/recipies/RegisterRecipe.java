package com.ultima.coe.recipies;

import com.ultima.coe.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegisterRecipe {

	public static void init() {
		GameRegistry.addSmelting(Item.getByNameOrId(Reference.MOD_ID+":pebble"), new ItemStack(Item.getByNameOrId(Reference.MOD_ID+":pebble")), 1F);
	}
	
}
