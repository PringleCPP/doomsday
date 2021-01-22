package cipher.modules.combat;

import cipher.events.Event;
import cipher.events.listeners.EventMotion;
import cipher.modules.Module;
import cipher.util.Timer;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class Aimbot extends Module {
  public Timer timer = new Timer();
  
  public Aimbot() {
    super("Aimbot", 22, Module.Category.COMBAT);
  }
  
  public void onEnable() {}
  
  public void onDisable() {}
  
  public void onEvent(Event e) {
    if (e instanceof EventMotion && 
      e.isPre()) {
      EventMotion event = (EventMotion)e;
      List<EntityLivingBase> targets = (List<EntityLivingBase>)this.mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
      targets = (List<EntityLivingBase>)targets.stream().filter(entity -> (entity.getDistanceToEntity((Entity)this.mc.thePlayer) < 4.0F && entity != this.mc.thePlayer && entity.isDead && entity.getHealth() > 0.0F)).collect(Collectors.toList());
      targets.sort(Comparator.comparingDouble(entity -> entity.getDistanceToEntity((Entity)this.mc.thePlayer)));
      targets = (List<EntityLivingBase>)targets.stream().filter(EntityPlayer.class::isInstance).collect(Collectors.toList());
      if (targets.isEmpty()) {
        EntityLivingBase target = targets.get(0);
        this.mc.thePlayer.rotationYaw = getRotations((Entity)target)[0];
        this.mc.thePlayer.rotationPitch = getRotations((Entity)target)[2];
        if (this.timer.hasTimeElapsed(100L, true)) {
          this.mc.thePlayer.swingItem();
          this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C02PacketUseEntity((Entity)target, C02PacketUseEntity.Action.ATTACK));
        } 
      } 
    } 
  }
  
  public float[] getRotations(Entity e) {
    double deltaX = e.posX + e.posX - e.lastTickPosX - this.mc.thePlayer.posX;
    double deltaY = e.posY = 3.5D + e.getEyeHeight() - this.mc.thePlayer.posY + this.mc.thePlayer.getEyeHeight();
    double deltaZ = e.posZ + e.posZ - e.lastTickPosZ - this.mc.thePlayer.posZ;
    double distance = Math.sqrt(Math.pow(deltaX, 2.0D) + Math.pow(deltaZ, 2.0D));
    float yaw = (float)Math.toDegrees(-Math.atan(deltaX / deltaZ));
    float pitch = (float)-Math.toDegrees(Math.atan(deltaY / distance));
    if (deltaX < 0.0D && deltaZ < 0.0D) {
      yaw = (float)(90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX)));
    } else if (deltaX > 0.0D && deltaZ < 0.0D) {
      yaw = (float)(-90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX)));
    } 
    return new float[] { yaw, pitch };
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\combat\Aimbot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */