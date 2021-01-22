package cipher.ui;

import cipher.Client;
import cipher.events.Event;
import cipher.events.listeners.EventRenderGUI;
import cipher.modules.Module;
import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class HUD {
  public Minecraft mc = Minecraft.getMinecraft();
  
  public void draw() {
    ScaledResolution sr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
    FontRenderer fr = this.mc.fontRendererObj;
    Client.modules.sort(Comparator.comparingInt(m -> paramFontRenderer.getStringWidth(((Module)m).name)).reversed());
    GlStateManager.pushMatrix();
    GlStateManager.translate(4.0F, 4.0F, 0.0F);
    GlStateManager.scale(2.0F, 2.0F, 1.0F);
    GlStateManager.translate(-4.0F, -4.0F, 0.0F);
    fr.drawStringWithShadow(String.valueOf(Client.name) + " " + Client.version, 4.0D, 4.0D, -1);
    GlStateManager.popMatrix();
    int count = 0;
    for (Module m : Client.modules) {
      if (!m.toggled || m.name.equals("TabGUI"))
        continue; 
      double offset = (count * (fr.FONT_HEIGHT + 6));
      Gui.drawRect((sr.getScaledWidth() - fr.getStringWidth(m.name) - 10), offset, (sr.getScaledWidth() - fr.getStringWidth(m.name) - 8), (6 + fr.FONT_HEIGHT) + offset, -16711936);
      Gui.drawRect((sr.getScaledWidth() - fr.getStringWidth(m.name) - 8), offset, sr.getScaledWidth(), (6 + fr.FONT_HEIGHT) + offset, -1879048192);
      fr.drawStringWithShadow(m.name, (sr.getScaledWidth() - fr.getStringWidth(m.name) - 4), 4.0D + offset, -4);
      count++;
    } 
    Client.onEvent((Event)new EventRenderGUI());
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\ciphe\\ui\HUD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */