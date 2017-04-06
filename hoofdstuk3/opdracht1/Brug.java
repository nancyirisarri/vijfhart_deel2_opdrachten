import java.awt.Color;
import java.awt.Graphics;

public class Brug implements Runnable {
  private boolean open = false;                       
  int positie, lengte;
  private Thread thread;
  
  
  public Brug(int positie, int lengte) {  // maak brug aan
    this.positie=positie;                 // met bepaalde positie en lengte
    this.lengte=lengte;
    thread= new Thread(this);
    thread.start();
  } 
  
  public void run(){
    while(true){
      openSluit();
      try{Thread.sleep(open?2000:3000);} // laat de brug langer gesloten dan open
      catch(InterruptedException ie){}
    }
  }
  
  public void openSluit() {              // open of sluit de brug
	synchronized(this) {
      try {
		if(open)
		wait(10l);
	  } catch(InterruptedException ie) {
		ie.printStackTrace();  
	  }
	  
      open = !open;
      
      if(!open)
      notifyAll();
    }
  }

  public boolean open() {
    return open;
  }
  
  public void paint(Graphics g) {        // teken een geopende of gesloten brug
    g.setColor(Color.red);
    if(open())
      g.drawLine(positie,100,positie,100-lengte);
    else                            
      g.drawLine(positie,100,positie+lengte,100);
  }
  
}