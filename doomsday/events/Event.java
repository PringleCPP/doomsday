package cipher.events;

public class Event<T> {
  public boolean cancelled;
  
  public EventType type;
  
  public EventDirection direction;
  
  public boolean isCancelled() {
    return this.cancelled;
  }
  
  public void setCancelled(boolean cancelled) {
    this.cancelled = cancelled;
  }
  
  public EventType getType() {
    return this.type;
  }
  
  public void setType(EventType type) {
    this.type = type;
  }
  
  public EventDirection getDirection() {
    return this.direction;
  }
  
  public void setDirection(EventDirection direction) {
    this.direction = direction;
  }
  
  public boolean isPre() {
    if (this.type == null)
      return false; 
    return (this.type == EventType.PRE);
  }
  
  public boolean isPost() {
    if (this.type == null)
      return false; 
    return (this.type == EventType.POST);
  }
  
  public boolean isIncoming() {
    if (this.direction == null)
      return false; 
    return (this.direction == EventDirection.INCOMING);
  }
  
  public boolean isOutgoing() {
    if (this.direction == null)
      return false; 
    return (this.direction == EventDirection.OUTGOING);
  }
}


/* Location:              C:\Users\Administrator.5CD9228F42\Desktop\Cipher.jar!\cipher\events\Event.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */