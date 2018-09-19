package com.ultima.coe.block.basic.campfire;

import javax.annotation.Nullable;

import com.ultima.coe.api.ChildrenOfEarthAPI;
import com.ultima.coe.recipies.campfire.CampfireFuel;
import com.ultima.coe.recipies.campfire.CampfireRecipe;
import com.ultima.coe.recipies.campfire.CampfireStarter;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CampfireTileEntity extends TileEntity implements ITickable {

	private ItemStackHandler inventory = new ItemStackHandler(8);

	// Amount of fuel left burning
	private int fuel = 0;
	private int fuelMax = 0;
	// 0 = item is finished cooking
	private int progress = 0;
	private final int totalCookTime = 200;
	// If fire is on
	private boolean fire = false;

	@Override
	public void update() {
		// Fire
		if (!fire && isStarter() && isFuel() && isFood() && isSpace()) {
			useStarter();
			fire = true;
		}
		//Fuel
		if(fire){
			if(fuel <= 0) {
				if(isFuel()) {
					useFuel();
					fuel--;
				}else {
					fire = false;
				}
			}else {
				fuel--;
			}
		}
		//Progress
		if(fire) {
			if(isFood() && isSpace()) {
				progress++;
			}else {
				progress = 0;
			}
		}
		//Cook
		if(progress == totalCookTime) {
			cook();
			progress = 0;
		}
		//Sync
		updateGUI();
		//Debug
		//System.out.println("Fire:" + fire + " Fuel:" + fuel + " FuelMax:" + fuelMax + " Progress:" + progress + " TotalCookTime:" + totalCookTime);

	}

	// Returns True if the item is a starter
	private boolean isStarter() {
		for (CampfireStarter cs : ChildrenOfEarthAPI.campfireStarters) {
			if (cs.matches(inventory.getStackInSlot(1))) {
				return true;
			}
		}

		return false;
	}
	

	private boolean isFuel() {
		for (CampfireFuel cf : ChildrenOfEarthAPI.campfireFuels) {
			if (cf.matches(inventory.getStackInSlot(0))) {
				return true;
			}
		}

		return false;
	}
	

	private boolean isFood() {
		for (CampfireRecipe cr : ChildrenOfEarthAPI.campfireRecipes) {
			if (cr.matches(inventory.getStackInSlot(2))) {
				return true;
			}
		}

		return false;
	}
	

	private boolean isSpace() {
		ItemStack output = inventory.getStackInSlot(3);

		if (output.isEmpty()) {
			return true;
		}

		for (CampfireRecipe cr : ChildrenOfEarthAPI.campfireRecipes) {
			if (cr.getOutput().isItemEqual(output)
					&& output.getCount() + cr.getOutput().getCount() <= output.getMaxStackSize()) {
				return true;
			}
		}

		return false;
	}
	
	private void updateGUI() {
		inventory.setStackInSlot(4, new ItemStack(Item.getByNameOrId("coe:data"), fuel));
		inventory.setStackInSlot(5, new ItemStack(Item.getByNameOrId("coe:data"), fuelMax));
		inventory.setStackInSlot(6, new ItemStack(Item.getByNameOrId("coe:data"), progress));
		inventory.setStackInSlot(7, new ItemStack(Item.getByNameOrId("coe:data"), totalCookTime));
	}
	
	// TODO
	private ItemStack output() {
		for (CampfireRecipe cr : ChildrenOfEarthAPI.campfireRecipes) {
			if (cr.matches(inventory.getStackInSlot(2))) {
				System.out.println(cr.getOutput().getItem().getRegistryName().toString());
				return cr.getOutput();
			}
		}
		return ItemStack.EMPTY;
	}

	// TODO
	private void cook() {
		inventory.insertItem(3, output(), false);
		System.out.println("*****************************Cook*****************************");
	}

	// TODO
	private void useFuel() {

	}
	
	//TODO
	private void useStarter(){
		
	}
	

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		compound.setInteger("fuel", this.fuel);
		compound.setInteger("fuelmax", fuelMax);
		compound.setInteger("progress", this.progress);
		compound.setBoolean("fire", this.fire);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		this.fuel = compound.getInteger("fuel");
		this.fuelMax = compound.getInteger("fuelmax");
		this.progress = compound.getInteger("progress");
		this.fire = compound.getBoolean("fire");
		super.readFromNBT(compound);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory
				: super.getCapability(capability, facing);
	}

}
