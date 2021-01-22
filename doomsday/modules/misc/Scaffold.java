package cipher.modules.misc;

import cipher.events.Event;
import cipher.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Scaffold extends Module {
  public Scaffold() {
    super("Scaffold", 25, Module.Category.MISC);
  }
  
  private static boolean cooldown = false;
  
  public ItemStack blockInSlot;
  
  public int lastItem;
  
  public void draw() {
    ScaledResolution sr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
    String blocks = String.valueOf(String.valueOf(getBlocksAmount()) + " Blocks");
    this.mc.fontRendererObj.drawString(blocks, (sr.getScaledWidth() / 2 - this.mc.fontRendererObj.getStringWidth(blocks) + 21), (sr.getScaledHeight() / 2 + 100), -16772865);
  }
  
  public void onEvent(Event e) {
    draw();
    BlockPos playBlockPos = new BlockPos(this.mc.thePlayer.posX, (this.mc.thePlayer.getEntityBoundingBox()).minY, this.mc.thePlayer.posZ);
    if (this.mc.theWorld.isAirBlock(playBlockPos.add(0, -1, 0)))
      if (isValidBlock(playBlockPos.add(0, -2, 0))) {
        place(playBlockPos.add(0, -1, 0), EnumFacing.UP);
      } else if (isValidBlock(playBlockPos.add(-1, -1, 0))) {
        place(playBlockPos.add(0, -1, 0), EnumFacing.EAST);
      } else if (isValidBlock(playBlockPos.add(1, -1, 0))) {
        place(playBlockPos.add(0, -1, 0), EnumFacing.WEST);
      } else if (isValidBlock(playBlockPos.add(0, -1, -1))) {
        place(playBlockPos.add(0, -1, 0), EnumFacing.SOUTH);
      } else if (isValidBlock(playBlockPos.add(0, -1, 1))) {
        place(playBlockPos.add(0, -1, 0), EnumFacing.NORTH);
      } else if (isValidBlock(playBlockPos.add(1, -1, 1))) {
        if (isValidBlock(playBlockPos.add(0, -1, 1)))
          place(playBlockPos.add(0, -1, 1), EnumFacing.NORTH); 
        place(playBlockPos.add(1, -1, 1), EnumFacing.EAST);
      } else if (isValidBlock(playBlockPos.add(-1, -1, 1))) {
        if (isValidBlock(playBlockPos.add(-1, -1, 0)))
          place(playBlockPos.add(0, -1, 1), EnumFacing.WEST); 
        place(playBlockPos.add(-1, -1, 1), EnumFacing.SOUTH);
      } else if (isValidBlock(playBlockPos.add(-1, -1, -1))) {
        if (isValidBlock(playBlockPos.add(0, -1, -1)))
          place(playBlockPos.add(0, -1, 1), EnumFacing.SOUTH); 
        place(playBlockPos.add(-1, -1, 1), EnumFacing.WEST);
      } else if (isValidBlock(playBlockPos.add(1, -1, -1))) {
        if (isValidBlock(playBlockPos.add(1, -1, 0)))
          place(playBlockPos.add(1, -1, 0), EnumFacing.EAST); 
        place(playBlockPos.add(1, -1, -1), EnumFacing.NORTH);
      }  
  }
  
  public void onEnable() {
    draw();
  }
  
  private void place(BlockPos pos, EnumFacing face) {
    cooldown = true;
    if (face == EnumFacing.UP) {
      pos = pos.add(0, -1, 0);
    } else if (face == EnumFacing.NORTH) {
      pos = pos.add(0, 0, 1);
    } else if (face == EnumFacing.EAST) {
      pos = pos.add(-1, 0, 0);
    } else if (face == EnumFacing.SOUTH) {
      pos = pos.add(0, 0, -1);
    } else if (face == EnumFacing.WEST) {
      pos = pos.add(1, 0, 0);
    } 
    int amount = 0;
    for (int i = 36; i < 45; i++) {
      ItemStack itemStack = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
      if (itemStack != null && itemStack.getItem() instanceof ItemBlock) {
        amount++;
        Block block = ((ItemBlock)itemStack.getItem()).getBlock();
        this.blockInSlot = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
      } 
    } 
    if (amount != 0) {
      this.mc.thePlayer.swingItem();
      this.mc.playerController.func_178890_a(this.mc.thePlayer, this.mc.theWorld, this.blockInSlot, pos, face, new Vec3(0.5D, 0.5D, 0.5D));
    } 
    double var4 = pos.getX() + 0.25D - this.mc.thePlayer.posX;
    double var6 = pos.getZ() + 0.25D - this.mc.thePlayer.posZ;
    double var8 = pos.getY() + 0.25D - this.mc.thePlayer.posY + this.mc.thePlayer.getEyeHeight();
    double var14 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
    float yaw = (float)(Math.atan2(var6, var4) * 180.0D / 3.141592653489D) - 90.0F;
    float pitch = (float)-(Math.atan2(var8, var14) * 180.0D / 3.141592653489D) - 90.0F;
    int ticks = 0;
    ticks++;
    if (ticks >= 100) {
      ticks = 0;
      this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(this.mc.thePlayer.posY, this.mc.thePlayer.posX, this.mc.thePlayer.posZ, yaw, pitch, true));
    } 
  }
  
  private boolean isValidBlock(BlockPos pos) {
    Block b = this.mc.theWorld.getBlockState(pos).getBlock();
    return (!(b instanceof net.minecraft.block.BlockLiquid) && b.getMaterial() != Material.air);
  }
  
  private int getBlocksAmount() {
    int amount = 0;
    for (int i = 36; i < 45; i++) {
      ItemStack itemStack = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
      if (itemStack != null && itemStack.getItem() instanceof ItemBlock) {
        Block block = ((ItemBlock)itemStack.getItem()).getBlock();
        amount += itemStack.stackSize;
      } 
    } 
    return amount;
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\misc\Scaffold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */