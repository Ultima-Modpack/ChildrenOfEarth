package com.ultima.coe.proxy;

import com.ultima.coe.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;


@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    /*
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
    	RegisterBlock.registerModels();
    	//ModItems.initModels();
    }
    */
    
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
    	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + id, "inventory"));
    }
    
}