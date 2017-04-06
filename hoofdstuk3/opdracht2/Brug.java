import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit;

public class Brug implements Runnable {
  private boolean open = false;                       
  int positie, lengte;
  private Thread thread;
  public Lock lock = new ReentrantLock();
  public Condition openCondition = lock.newCondition();
  
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
	lock.lock();
	try {
		if(!open) {
			openCondition.await(5, TimeUnit.SECONDS);
		} 
		
		open = !open;		

		openCondition.signalAll();
		
	} catch(InterruptedException e) {
		e.printStackTrace();
	} finally {
		lock.unlock();			
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