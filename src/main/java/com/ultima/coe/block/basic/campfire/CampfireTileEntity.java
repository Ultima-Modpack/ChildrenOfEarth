package com.ultima.coe.block.basic.campfire;

import com.ultima.coe.api.ChildrenOfEarthAPI;
import com.ultima.coe.recipies.campfire.CampfireFuel;
import com.ultima.coe.recipies.campfire.CampfireRecipe;
import com.ultima.coe.recipies.campfire.CampfireStarter;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

public class CampfireTileEntity extends TileEntity implements IInventory, ITickable {

	public static final int SLOTS = 4;
	private int burnTimeRemaining = 0;
	private int burnTimeInitialValue = 0;
	private int cookTime;
	private boolean fire;
	public static final int COOK_TIME_FOR_COMPLETION = 200;
	private ItemStackHandler inventory = new ItemStackHandler(SLOTS);

	public CampfireTileEntity() {
		clear();
	}
	
	@Override
	public void update() {
		// TODO write
	}

	public double fractionOfFuelRemaining() {
		if (burnTimeInitialValue <= 0)
			return 0;
		double fraction = burnTimeRemaining / (double) burnTimeInitialValue;
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}

	public int secondsOfFuelRemaining() {
		if (burnTimeRemaining <= 0)
			return 0;
		return burnTimeRemaining / 20; // 20 ticks per second
	}

	public double fractionOfCookTimeComplete() {
		double fraction = cookTime / (double) COOK_TIME_FOR_COMPLETION;
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}

	// Burns Fuel and returns its burn time
	private void burnFuel() {

		markDirty();
	}

	private void start() {

		markDirty();
	}

	private void smeltItem() {
		
		markDirty();
	}
	
	@Override
	public int getSizeInventory() {
		return SLOTS;
	}
	
	@Override
	public boolean isEmpty()
	{
		for(int i = 0; i<SLOTS ;i++) {
			if(inventory.getStackInSlot(i) != ItemStack.EMPTY) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory.getStackInSlot(slot);
	}
	
	@Override
	public ItemStack decrStackSize(int slotIndex, int count) {
		markDirty();
		return inventory.extractItem(slotIndex, count, false);

	}
	
	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
		
		inventory.extractItem(slotIndex, getInventoryStackLimit(), false);
		inventory.insertItem(slotIndex, itemstack, false);
		markDirty();
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (this.world.getTileEntity(this.pos) != this) return false;
		final double X_CENTRE_OFFSET = 0.5;
		final double Y_CENTRE_OFFSET = 0.5;
		final double Z_CENTRE_OFFSET = 0.5;
		final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
		return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		switch (index) {
		case 0:
			for (CampfireFuel cr : ChildrenOfEarthAPI.campfireFuels) {
			    if (cr.matches(inventory.getStackInSlot(0))) {
			      return true;
			    }
			  }
			break;
		case 1:
			for (CampfireStarter cr : ChildrenOfEarthAPI.campfireStarters) {
			    if (cr.matches(inventory.getStackInSlot(1))) {
			      return true;
			    }
			  }
			break;
		case 2:
			for (CampfireRecipe cr : ChildrenOfEarthAPI.campfireRecipes) {
				if (cr.matches(inventory.getStackInSlot(2))) {
					return true;
				}
			}
			break;
		}
		return false;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		compound.setBoolean("fire", this.fire);
		compound.setInteger("burnTimeRemaining", this.burnTimeRemaining);
		compound.setInteger("burnTimeInitialValue", this.burnTimeInitialValue);
		compound.setInteger("cookTime", this.cookTime);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		this.fire = compound.getBoolean("fire");
		this.burnTimeRemaining = compound.getInteger("burnTimeRemaining");
		this.burnTimeInitialValue = compound.getInteger("burnTimeInitialValue");
		this.cookTime = compound.getInteger("cookTime");
		super.readFromNBT(compound);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int slotIndex) {
		markDirty();
		return inventory.extractItem(slotIndex, this.getInventoryStackLimit(), false);
	}
	
	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public String getName() {
		return "container.coe.campfire.name";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getField(int id) {
		// TODO Finish
		switch (id) {
			case 0: // Burn time remaining
				return burnTimeRemaining;
			case 1: // Progress towards completion
				return cookTime;
			case 2: // Initial burn time
				return burnTimeInitialValue;
			case 3: // Max progress
				return COOK_TIME_FOR_COMPLETION;
		}
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
			case 0:
				burnTimeRemaining = value;
				break;
			case 1:
				cookTime = value;
				break;
			case 2:
				burnTimeInitialValue = value;
				break;
			case 3:
				break;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		for(int i = 0; i<4; i++) {
			removeStackFromSlot(i);
		}
		
	}
	
	public boolean isBurning()
    {
        return this.burnTimeRemaining > 0;
    }
	
    @SideOnly(Side.CLIENT)
    public static boolean isBurning(CampfireTileEntity inventory)
    {
        return inventory.getField(0) > 0;
    }

}
