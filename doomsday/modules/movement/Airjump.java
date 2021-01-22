package cipher.modules.movement;

import cipher.events.Event;
import cipher.modules.Module;

public class Airjump extends Module {
  public Airjump() {
    super("Airjump", 38, Module.Category.MOVEMENT);
  }
  
  public void onEvent(Event e) {
    if (this.mc.thePlayer.isAirBorne = true)
      this.mc.thePlayer.onGround = true; 
  }
  
  public void onDisable() {}
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\movement\Airjump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */