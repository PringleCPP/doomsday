package cipher;

import cipher.events.Event;
import cipher.events.listeners.EventKey;
import cipher.modules.Module;
import cipher.modules.combat.NoCombat;
import cipher.modules.misc.Scaffold;
import cipher.modules.movement.Airjump;
import cipher.modules.movement.Bhop;
import cipher.modules.movement.Fly;
import cipher.modules.movement.InfiniteJump;
import cipher.modules.movement.Spider;
import cipher.modules.player.NoFall;
import cipher.modules.render.Fullbright;
import cipher.modules.render.TabGUI;
import cipher.ui.HUD;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.lwjgl.opengl.Display;

public class Client {
  public static String name = "Cipher";
  
  public static String version = "1.2";
  
  public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();
  
  public static HUD hud = new HUD();
  
  public static void startup() {
    System.out.print("Starting " + name + " v" + version);
    Display.setTitle(String.valueOf(name) + " v" + version);
    modules.add(new Fly());
    modules.add(new NoFall());
    modules.add(new NoCombat());
    modules.add(new Fullbright());
    modules.add(new Bhop());
    modules.add(new InfiniteJump());
    modules.add(new Spider());
    modules.add(new TabGUI());
    modules.add(new Airjump());
    modules.add(new Scaffold());
    modules.add(new NoCombat());
  }
  
  public static void onEvent(Event e) {
    for (Module m : modules) {
      if (!m.toggled)
        continue; 
      m.onEvent(e);
    } 
  }
  
  public static void keyPress(int key) {
    onEvent((Event)new EventKey(key));
    for (Module m : modules) {
      if (m.getKey() == key)
        m.toggle(); 
    } 
  }
  
  public static List<Module> getModulesByCategory(Module.Category c) {
    List<Module> modules = new ArrayList<>();
    for (Module m : Client.modules) {
      if (m.category == c)
        modules.add(m); 
    } 
    return modules;
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\Client.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */