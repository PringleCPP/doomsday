package cipher.modules.movement;

import cipher.events.Event;
import cipher.modules.Module;

public class Fly extends Module {
  public Fly() {
    super("Fly", 34, Module.Category.MOVEMENT);
  }
  
  public void onDisable() {
    this.mc.thePlayer.capabilities.isFlying = false;
    this.mc.thePlayer.capabilities.allowFlying = false;
  }
  
  public void onEvent(Event e) {
    if (e instanceof cipher.events.listeners.EventUpdate && 
      e.isPre())
      this.mc.thePlayer.capabilities.allowFlying = true; 
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\movement\Fly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */