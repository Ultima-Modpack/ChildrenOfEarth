package com.ultima.coe.common;

import com.ultima.coe.item.PebbleItem;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class RegisterItem {
	
	public static PebbleItem pebble = new PebbleItem();
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				pebble
		);
	}
	
	public static void registerModels() {
		pebble.registerItemModel();
	}
	
}
