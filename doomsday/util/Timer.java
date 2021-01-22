package cipher.util;

public class Timer {
  public long lastMS = System.currentTimeMillis();
  
  public void reset() {
    this.lastMS = System.currentTimeMillis();
  }
  
  public boolean hasTimeElapsed(long time, boolean reset) {
    if (System.currentTimeMillis() - this.lastMS > time) {
      if (reset)
        reset(); 
      return true;
    } 
    return false;
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\ciphe\\util\Timer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */