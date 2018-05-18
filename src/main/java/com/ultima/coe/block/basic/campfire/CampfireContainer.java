package com.ultima.coe.block.basic.campfire;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CampfireContainer extends Container {

	final CampfireTileEntity campfire;
	private int burnTimeRemaining;
	private int burnTimeInitialValue;
	private int cookTime;
	public static final int COOK_TIME_FOR_COMPLETION = 200;
	private boolean fire;

	public CampfireContainer(InventoryPlayer playerInv,CampfireTileEntity campfire) {

		this.campfire = campfire;
		IItemHandler inventory = campfire.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
				EnumFacing.NORTH);

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

		// Player's inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		// Player's hotbar
		for (int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
		}

	}

	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.campfire);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.burnTimeRemaining != this.campfire.getField(0))
            {
                icontainerlistener.sendWindowProperty(this, 0, this.campfire.getField(0));
            }
            
            if (this.cookTime != this.campfire.getField(1))
            {
                icontainerlistener.sendWindowProperty(this, 1, this.campfire.getField(1));
            }
            
            if (this.burnTimeRemaining != this.campfire.getField(2))
            {
                icontainerlistener.sendWindowProperty(this, 2, this.campfire.getField(2));
            }
            
            if (this.COOK_TIME_FOR_COMPLETION != this.campfire.getField(3))
            {
                icontainerlistener.sendWindowProperty(this, 3, this.campfire.getField(3));
            }
        }
		
		this.burnTimeRemaining = this.campfire.getField(0);
		this.cookTime = this.campfire.getField(1);
		this.burnTimeInitialValue = this.campfire.getField(2);
	}
	
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.campfire.setField(id, data);
    }


	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.campfire.isUsableByPlayer(playerIn);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
		//TODO Create shift click functionality
		return ItemStack.EMPTY;
    }

}
