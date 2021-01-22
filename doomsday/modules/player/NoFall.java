package cipher.modules.player;

import cipher.events.Event;
import cipher.modules.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {
  public NoFall() {
    super("NoFall", 50, Module.Category.PLAYER);
  }
  
  public void onEvent(Event e) {
    if (e instanceof cipher.events.listeners.EventUpdate && e.isPre() && 
      this.mc.thePlayer.fallDistance > 2.0F)
      this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer(true)); 
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\player\NoFall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */