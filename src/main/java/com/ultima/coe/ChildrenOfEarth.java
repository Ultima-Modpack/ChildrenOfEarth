package com.ultima.coe;

import java.util.logging.Logger;

import com.ultima.coe.common.COETab;
import com.ultima.coe.common.RegisterBlock;
import com.ultima.coe.common.RegisterGui;
import com.ultima.coe.common.RegisterItem;
import com.ultima.coe.proxy.CommonProxy;
import com.ultima.coe.recipies.RegisterCampfireRecipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class ChildrenOfEarth {
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	public static final COETab creativeTab = new COETab();
	public static Logger logger;

	@Mod.Instance
	public static ChildrenOfEarth instance;

	// Item and Block Initialization
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			RegisterItem.register(event.getRegistry());
			RegisterBlock.registerItemBlocks(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			RegisterBlock.register(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerItems(ModelRegistryEvent event) {
			RegisterItem.registerModels();
			RegisterBlock.registerModels();
		}
		
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new RegisterGui());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		RegisterCampfireRecipe.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
