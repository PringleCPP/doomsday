package cipher.modules.render;

import cipher.Client;
import cipher.events.Event;
import cipher.events.listeners.EventKey;
import cipher.modules.Module;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class TabGUI extends Module {
  public int currentTab;
  
  public boolean expanded;
  
  public TabGUI() {
    super("TabGUI", 0, Module.Category.RENDER);
    this.toggled = true;
  }
  
  public void onEvent(Event e) {
    if (e instanceof cipher.events.listeners.EventRenderGUI) {
      FontRenderer fr = this.mc.fontRendererObj;
      Gui.drawRect(5.0D, 30.5D, 70.0D, (30 + (Module.Category.values()).length * 16) + 1.5D, -1879048192);
      Gui.drawRect(5.0D, (33 + this.currentTab * 16), 70.0D, (33 + this.currentTab * 16 + 12), -16711936);
      int count = 0;
      byte b;
      int i;
      Module.Category[] arrayOfCategory;
      for (i = (arrayOfCategory = Module.Category.values()).length, b = 0; b < i; ) {
        Module.Category c = arrayOfCategory[b];
        fr.drawStringWithShadow(c.name, 11.0D, (35 + count * 16), -1);
        count++;
        b++;
      } 
      if (this.expanded) {
        Module.Category category = Module.Category.values()[this.currentTab];
        List<Module> modules = Client.getModulesByCategory(category);
        if (modules.size() == 0)
          return; 
        Gui.drawRect(70.0D, 30.5D, 138.0D, (30 + modules.size() * 16) + 1.5D, -1879048192);
        Gui.drawRect(70.0D, (33 + category.moduleIndex * 16), 138.0D, (33 + category.moduleIndex * 16 + 12), -16711936);
        count = 0;
        for (Module m : modules) {
          fr.drawStringWithShadow(m.name, 73.0D, (35 + count * 16), -1);
          count++;
        } 
      } 
    } 
    if (e instanceof EventKey) {
      int code = ((EventKey)e).key;
      Module.Category category = Module.Category.values()[this.currentTab];
      List<Module> modules = Client.getModulesByCategory(category);
      if (code == 200)
        if (this.expanded) {
          if (category.moduleIndex <= 0) {
            category.moduleIndex = modules.size() - 1;
          } else {
            category.moduleIndex--;
          } 
        } else if (this.currentTab <= 0) {
          this.currentTab = (Module.Category.values()).length - 1;
        } else {
          this.currentTab--;
        }  
      if (code == 208)
        if (this.expanded) {
          if (category.moduleIndex >= modules.size() - 1) {
            category.moduleIndex = 0;
          } else {
            category.moduleIndex++;
          } 
        } else if (this.currentTab >= (Module.Category.values()).length - 1) {
          this.currentTab = 0;
        } else {
          this.currentTab++;
        }  
      if (code == 205)
        if (this.expanded && modules.size() != 0) {
          Module module = modules.get(category.moduleIndex);
          if (!module.name.equals("TabGUI"))
            module.toggle(); 
        } else {
          this.expanded = true;
        }  
      if (code == 203)
        this.expanded = false; 
    } 
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\render\TabGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */