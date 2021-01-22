package cipher.modules.movement;

import cipher.events.Event;
import cipher.modules.Module;

public class Spider extends Module {
  public Spider() {
    super("Spider", 36, Module.Category.MOVEMENT);
  }
  
  public void onDisable() {}
  
  public void onEvent(Event e) {
    if (e instanceof cipher.events.listeners.EventUpdate && 
      e.isPre() && 
      this.mc.thePlayer.isCollidedHorizontally) {
      this.mc.thePlayer.onGround = true;
      this.mc.thePlayer.jump();
    } 
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\movement\Spider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */