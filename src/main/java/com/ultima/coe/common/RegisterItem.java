package com.ultima.coe.common;

import com.ultima.coe.item.DataItem;
import com.ultima.coe.item.PebbleItem;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class RegisterItem {
	
	public static PebbleItem pebble = new PebbleItem();
	public static DataItem data = new DataItem();
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				pebble, data
		);
	}
	
	public static void registerModels() {
		pebble.registerItemModel();
		data.registerItemModel();
	}
	
}
