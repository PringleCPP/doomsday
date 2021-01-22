package cipher.modules.movement;

import cipher.events.Event;
import cipher.modules.Module;

public class Sprint extends Module {
  public Sprint() {
    super("Sprint", 49, Module.Category.MOVEMENT);
  }
  
  public void onEnable() {}
  
  public void onDisable() {
    this.mc.thePlayer.setSprinting(this.mc.gameSettings.keyBindSprint.getIsKeyPressed());
  }
  
  public void onEvent(Event e) {
    if (e instanceof cipher.events.listeners.EventUpdate && 
      e.isPre())
      if (this.mc.thePlayer.moveForward > 0.0F && !this.mc.thePlayer.isUsingItem() && !this.mc.thePlayer.isSneaking() && !this.mc.thePlayer.isCollidedHorizontally)
        this.mc.thePlayer.setSprinting(true);  
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\movement\Sprint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */