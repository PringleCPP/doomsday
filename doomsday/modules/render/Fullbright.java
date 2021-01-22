package cipher.modules.render;

import cipher.modules.Module;

public class Fullbright extends Module {
  public Fullbright() {
    super("Fullbright", 24, Module.Category.RENDER);
  }
  
  public void onEnable() {
    this.mc.gameSettings.gammaSetting = 100.0F;
  }
  
  public void onDisable() {
    this.mc.gameSettings.gammaSetting = 1.0F;
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\modules\render\Fullbright.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */