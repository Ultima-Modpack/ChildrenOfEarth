package com.ultima.coe.common;

import com.ultima.coe.block.basic.campfire.CampfireContainer;
import com.ultima.coe.block.basic.campfire.CampfireGuiOld;
import com.ultima.coe.block.basic.campfire.CampfireTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class RegisterGui implements IGuiHandler {
	
	public static final int CAMPFIRE = 0;

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case CAMPFIRE:
				return new CampfireContainer(player.inventory, (CampfireTileEntity)world.getTileEntity(new BlockPos(x, y, z)));
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case CAMPFIRE:
				return new CampfireGuiOld(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
			default:
				return null;
		}
	}

}
