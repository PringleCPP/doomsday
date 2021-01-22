package cipher.events.listeners;

import cipher.events.Event;

public class EventKey extends Event<EventKey> {
  public int key;
  
  public EventKey(int key) {
    this.key = key;
  }
  
  public int getkey() {
    return this.key;
  }
  
  public void setkey(int key) {
    this.key = key;
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\events\listeners\EventKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */