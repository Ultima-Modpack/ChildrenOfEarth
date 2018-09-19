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
		//Caculate Percentages
		if(fuelMax != 0) {
			fuelLeft = (int)(fuel/(double)fuelMax * 14);
		}
		if(totalCookTime != 0) {
			amountCooked = (int)(progress/(double)totalCookTime * 14);
		}
		
		drawTexturedModalRect(x + 57 - (14-fuelLeft), y + 37, 176, 0 - (14-fuelLeft), 14, 14);
		//System.out.println(fuelLeft);
		//Progress
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		
		String name = I18n.format(RegisterBlock.campfire.getUnlocalizedName() + ".name");
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
	}

}
