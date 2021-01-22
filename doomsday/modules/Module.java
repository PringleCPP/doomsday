package cipher.modules;

import cipher.events.Event;
import net.minecraft.client.Minecraft;

public class Module {
  public String name;
  
  public boolean toggled;
  
  public int keyCode;
  
  public Category category;
  
  public Minecraft mc = Minecraft.getMinecraft();
  
  public Module(String name, int key, Category c) {
    this.name = name;
    this.keyCode = key;
    this.category = c;
  }
  
  public boolean isEnabled() {
    return this.toggled;
  }
  
  public int getKey() {
    return this.keyCode;
  }
  
  public void onEvent(Event e) {}
  
  public void toggle() {
    this.toggled = !this.toggled;
    if (this.toggled) {
      onEnable();
    } else {
      onDisable();
    } 
  }
  
  public void onEnable() {}
  
  public void onDisable() {}
  
  public enum Category {
    PLAYER("Player"),
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    MISC("Misc");
    
    public String name;
    
    public int moduleIndex;
    
    Category(String name) {
      this.name = name;
    }
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */