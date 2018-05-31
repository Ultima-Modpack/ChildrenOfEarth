package com.ultima.coe.common;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CampfirePacket implements IMessage {

	private int fuel;
	private int fuelMax;
	// 0 = item is finished cooking
	private int progress;
	private int totalCookTime;

	public CampfirePacket() {
	}

	public CampfirePacket(int fuel, int fuelMax, int progress, int totalCookTime) {
		this.fuel = fuel;
		this.fuelMax = fuelMax;
		this.progress = progress;
		this.totalCookTime = totalCookTime;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(fuel);
		buf.writeInt(fuelMax);
		buf.writeInt(progress);
		buf.writeInt(totalCookTime);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		fuel = buf.readInt();
		fuelMax = buf.readInt();
		progress = buf.readInt();
		totalCookTime = buf.readInt();
	}
	
	public static class MyMessageHandler implements IMessageHandler<CampfirePacket, IMessage> {
		  // Do note that the default constructor is required, but implicitly defined in this case

		  @Override public IMessage onMessage(CampfirePacket message, MessageContext ctx) {
		    // This is the player the packet was sent to the server from
		    EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;
		    // The value that was sent
		    int amount = message.toSend;
		    // Execute the action on the main server thread by adding it as a scheduled task
		    serverPlayer.getServerWorld().addScheduledTask(() -> {
		      serverPlayer.inventory.addItemStackToInventory(new ItemStack(Items.DIAMOND, amount));
		    });
		    // No response packet
		    return null;
		  }
		}
}
