package com.ultima.coe.block.basic.campfire;

import com.ultima.coe.Reference;
import com.ultima.coe.common.RegisterBlock;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class CampfireGui extends GuiContainer {
	
	private InventoryPlayer playerInv;
	private Container container;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/campfire.png");
	private int fuel, fuelMax, progress, totalCookTime;
	private int fuelLeft;
	private int amountCooked;
	
	public CampfireGui(Container container, InventoryPlayer playerInv) {
		super(container);
		this.playerInv = playerInv;
		this.container = container;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		drawDefaultBackground();
		GlStateManager.color(1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		//Sync Data From TileEntity
		fuel = container.getSlot(4).getStack().getCount();
		fuelMax = container.getSlot(5).getStack().getCount();
		progress = container.getSlot(6).getStack().getCount();
		totalCookTime = container.getSlot(7).getStack().getCount();
		
		//Test Output
		//System.out.println("Fuel:" + fuel);
		//System.out.println("Fuel Max:" + fuelMax);
		//System.out.println("Progress:" + progress);
		//System.out.println("Total Cook Time:" + totalCookTime);
		
		//Calculate Fill For GUI
		int progresscooking;
		if(totalCookTime != 0) {
		progresscooking = (int)((progress*24)/totalCookTime);
		}else {
			progresscooking = 0;
		}
		int progressfire;
		if(fuelMax != 0) {
			progressfire= (int)(fuel*14/fuelMax) + 1;
		}else
		{
			progressfire = 0;
		}
		//drawTexturedModalRect(x + 57 - (14-fuelLeft), y + 37, 176, 0 - (14-fuelLeft), 14, 14);
		//Progress
		drawTexturedModalRect(x + 57, y + 37 + (14 - progressfire), 176, 14 - progressfire, 14 , progressfire);
		drawTexturedModalRect(x+79,y+35,176,14,progresscooking,17);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		
		String name = I18n.format(RegisterBlock.campfire.getUnlocalizedName() + ".name");
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
	}

}
