package com.ultima.coe.block.basic.campfire;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ultima.coe.ChildrenOfEarth;
import com.ultima.coe.block.BlockTileEntity;
import com.ultima.coe.common.RegisterGui;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class CampfireBlock extends BlockTileEntity<CampfireTileEntity> {
	
	public CampfireBlock() {
		super(Material.FIRE, "campfire");
	}
	
	
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(!world.isRemote) {
			if(!player.isSneaking()) {
				player.openGui(ChildrenOfEarth.instance, RegisterGui.CAMPFIRE, world, pos.getX(), pos.getY(), pos.getZ());
				return true;
			}
		}
		
		return false;
		
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		CampfireTileEntity tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		List<ItemStack> stacks = new ArrayList<ItemStack>();
		
		stacks.add(itemHandler.getStackInSlot(0));
		stacks.add(itemHandler.getStackInSlot(1));
		stacks.add(itemHandler.getStackInSlot(2));
		stacks.add(itemHandler.getStackInSlot(3));
		
		for(ItemStack is:stacks) {
			if(!is.isEmpty()) {
				EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), is);
				world.spawnEntity(item);
			}
		}
		
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public Class<CampfireTileEntity> getTileEntityClass() {
		return CampfireTileEntity.class;
	}
	
	@Nullable
	@Override
	public CampfireTileEntity createTileEntity(World world, IBlockState state) {
		return new CampfireTileEntity();
	}
	
	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	@Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8D, 1.0D);
    }
}
