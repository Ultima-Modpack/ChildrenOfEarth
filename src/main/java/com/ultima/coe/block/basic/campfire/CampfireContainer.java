package com.ultima.coe.block.basic.campfire;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CampfireContainer extends Container {
	
	public CampfireContainer(InventoryPlayer playerInv, final CampfireTileEntity campfire) {
		
		IItemHandler inventory = campfire.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		
		// First 4 slots
		
		// Fuel
		addSlotToContainer(new SlotItemHandler(inventory, 0, 56, 53) {
			@Override
			public void onSlotChanged() {
				campfire.markDirty();
			}
		});
		
		// Starter
		addSlotToContainer(new SlotItemHandler(inventory, 1, 35, 35) {
			@Override
			public void onSlotChanged() {
				campfire.markDirty();
			}
		});
		
		// Food
		addSlotToContainer(new SlotItemHandler(inventory, 2, 56, 17) {
			@Override
			public void onSlotChanged() {
				campfire.markDirty();
			}
		});
		
		// Output
		addSlotToContainer(new SlotItemHandler(inventory, 3, 116, 35) {
			@Override
			public void onSlotChanged() {
				campfire.markDirty();
			}
		});
		
		//Fuel
		addSlotToContainer(new SlotItemHandler(inventory, 4, 1000, 1000) {
			@Override
			public void onSlotChanged() {
				campfire.markDirty();
			}
		});
		
		//FuelMax
		addSlotToContainer(new SlotItemHandler(inventory, 5, 1000, 1000) {
			@Override
			public void onSlotChanged() {
				campfire.markDirty();
			}
		});
		
		//Progress
		addSlotToContainer(new SlotItemHandler(inventory, 6, 1000, 1000) {
			@Override
			public void onSlotChanged() {
				campfire.markDirty();
			}
		});
		
		//TotalCookTime
		addSlotToContainer(new SlotItemHandler(inventory, 7, 1000, 1000) {
			@Override
			public void onSlotChanged() {
				campfire.markDirty();
			}
		});
		

		// Player's inventory
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		// Player's hotbar
		for(int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k, 8 + k*18, 142));
		}
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
