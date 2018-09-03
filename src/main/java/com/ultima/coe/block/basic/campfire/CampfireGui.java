package com.ultima.coe.block.basic.campfire;

import java.util.ArrayList;
import java.util.List;

import com.ultima.coe.Reference;

import cjminecraft.core.client.gui.GuiBase;
import cjminecraft.core.client.gui.element.ElementEnergyBar;
import cjminecraft.core.client.gui.element.ElementProgressBar;
import cjminecraft.core.client.gui.element.ElementProgressBar.ProgressBarDirection;
import cjminecraft.core.network.PacketHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.CapabilityItemHandler;

public class CampfireGui extends GuiBase{
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/campfire.png");
	private CampfireTileEntity te;
	public static int cooldown, maxCooldown = 0;
	public static int sync = 0;
	
	public CampfireGui(InventoryPlayer playerInv, CampfireTileEntity te) {
		super(new CampfireContainer(playerInv, te), TEXTURE);
		setGuiSize(176, 166);
		this.te = te;
		this.name = "container.campfire";
	}
	
	@Override
	public void initGui() {
		super.initGui();
		addElement(new ElementEnergyBar(this, 7, 16, 18, 54).shouldSync(this.te.getPos(), null));
		addElement(new ElementProgressBar(this, 135, 36, 14, 14).setDirection(ProgressBarDirection.LEFT_TO_RIGHT)
				.setTextureUV(176, 0).setTexture(TEXTURE, 256, 256));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
		int actualMouseY = mouseY - ((this.height - this.ySize) / 2);

		if (isPointInRegion(134, 17, 18, 18, mouseX, mouseY)
				&& te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
						.getStackInSlot(9) == ItemStack.EMPTY) {
			List<String> text = new ArrayList<String>();
			text.add(TextFormatting.GRAY + I18n.format("gui.block_breaker.enchanted_book.tooltip"));
			this.drawHoveringText(text, actualMouseX, actualMouseY);
		}
	}


	@Override
	protected void updateElementInformation() {
		super.updateElementInformation();
		((ElementProgressBar) this.elements.get(1)).setMin(cooldown).setMax(maxCooldown);
		sync++;
		sync %= 10;
		if (sync == 0)
			PacketHandler.INSTANCE
					.sendToServer(new PacketGetWorker(this.te.getPos(), this.mc.player.getAdjustedHorizontalFacing(),
							"cjminecraft.bitofeverything.client.gui.GuiBlockBreaker", "cooldown", "maxCooldown"));
	}
}
