package cipher.modules.movement;

import cipher.events.Event;
import cipher.modules.Module;

public class InfiniteJump extends Module {
  public InfiniteJump() {
    super("InfJump", 44, Module.Category.MOVEMENT);
  }
  
  public void onEvent(Event e) {
    if (this.mc.thePlayer.moveForward > 0.0F) {
      if (this.mc.thePlayer.moveForward > 0.0F && !this.mc.thePlayer.isUsingItem() && !this.mc.thePlayer.isSneaking() && !this.mc.thePlayer.isCollidedHorizontally)
        this.mc.thePlayer.setSprinting(true); 
      this.mc.thePlayer.jump();
    } 
  }
  
  public void onDisable() {
    if (this.mc.thePlayer.moveForward > 0.0F);
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\movement\InfiniteJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */