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

	private ItemStackHandler inventory = new ItemStackHandler(4);

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
		if (fire) {
			if ((fuel == 0) && (!(isFuel() || isFood() || isSpace()))) {
				fire = false;
			}
		} else {
			if (isFuel() && isFood() && isStarter() && isSpace()) {
				fire = true;
				// Remove starter
				for (CampfireStarter cs : ChildrenOfEarthAPI.campfireStarters) {
					if (cs.matches(inventory.getStackInSlot(1))) {
						if (!cs.isInfinite()) {
							inventory.getStackInSlot(1).shrink(1);
						}
					}
				}
			}
		}
		if (fire) {
			if (fuel == 0) {
				if (isFuel() && isFood() && isSpace()) {
					for (CampfireFuel cf : ChildrenOfEarthAPI.campfireFuels) {
						if (cf.matches(inventory.getStackInSlot(0))) {
							fuelMax = fuel = cf.getFuelVal();
						}
					}
					inventory.getStackInSlot(0).shrink(1);

				} else {
					progress = 0;
					fire = false;

				}
			}
			if (fuel > 0) {
				if (isFood() && isSpace()) {
					if (progress == 200) {
						progress = 0;
						// Process
						ItemStack input = inventory.getStackInSlot(2);
						Item itemIn = input.getItem();
						input.shrink(1);
						ItemStack output = ItemStack.EMPTY;
						for (CampfireRecipe cr : ChildrenOfEarthAPI.campfireRecipes) {
							if (cr.matches(new ItemStack(itemIn))) {
								output = cr.getOutput();
							}
						}

						if (inventory.getStackInSlot(3).isEmpty()) {
							inventory.setStackInSlot(3, output);
						} else {
							inventory.getStackInSlot(3).grow(1);
							progress++;
						}
					}
					progress++;

				} else {
					progress = 0;
				}
				fuel--;
			}
		}
	}

	// Returns True if the item can be smelt
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
