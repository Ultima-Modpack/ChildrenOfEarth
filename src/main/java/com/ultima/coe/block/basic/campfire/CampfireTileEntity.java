package com.ultima.coe.block.basic.campfire;

import javax.annotation.Nullable;

import com.ultima.coe.api.ChildrenOfEarthAPI;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CampfireTileEntity extends TileEntity implements ITickable{
	
	private ItemStackHandler inventory = new ItemStackHandler(4);
	
	//Amount of fuel left burning
	private int fuel = 0;
	// 0 = item is finished cooking
	private int progress = 0;
	//If fire is on
	private boolean fire = false;
	
	@Override
	public void update() {
		
	
	}
	
	//Returns True if the item can be smelt
	private boolean isStarter(){
		boolean flag = false;
		return flag;
	}
	
	private boolean  isFuel() {
		boolean flag = false;
		
		for(ItemStack is: ChildrenOfEarthAPI.campfireFuels) {
			flag |= inventory.getStackInSlot(0).isItemEqual(is);
		}
		
		return flag;
	}
	
	private boolean  isFood() {
		boolean flag = false;
		return flag;
	}
	
	private boolean canSmelt() {
		return false;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		compound.setInteger("fuel", this.fuel);
		compound.setInteger("progress", this.progress);
		compound.setBoolean("fire", this.fire);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		this.fuel = compound.getInteger("fuel");
		this.progress = compound.getInteger("progress");
		this.fire = compound.getBoolean("fire");
		super.readFromNBT(compound);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
	}


	
}
