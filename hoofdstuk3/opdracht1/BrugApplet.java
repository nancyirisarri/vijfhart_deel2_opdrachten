import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;

public class BrugApplet extends java.applet.Applet implements Runnable
{
  Brug brug;
  boolean doorgaan = true;              //boolean om aan te geven of de applet thread doorgaat.
  Auto auto1, auto2;                    //auto's aanmaken
  private Thread thread;                //Thread voor de applet.
  private Dimension dim;                //dim, bufferImage en bufferGraphics
  private Image bufferImage;            //zorgen voor een rustiger beeld
  private Graphics bufferGraphics;      

  public void init()  {
    dim = getSize();                    // dim, bufferImage en bufferGraphics
    bufferImage = createImage(dim.width, dim.height); // zorgen voor een
    bufferGraphics = bufferImage.getGraphics();  // rustiger beeld
    brug = new Brug(150,80);
    auto1 = new Auto(363,79,12);        //initialiseren van auto1.
    auto1.setPlaatje(getImage(getDocumentBase(),"autobw.gif"));  //plaatje aan auto koppelen.
    auto1.setBrug(brug);
    auto1.start();
    auto2 = new Auto(363,79,7);        //initialiseren van auto2.
    auto2.setPlaatje(getImage(getDocumentBase(),"autord.gif"));  //plaatje aan auto koppelen.
    auto2.setBrug(brug);
    auto2.start();
  }
  
   public void update(Graphics g) // overschrijft de standaard manier van een plaatje tekenen:
   {                              // om een rustig beeld te verkrijgen
  	bufferGraphics.setColor(getBackground()); 
  	bufferGraphics.fillRect(0, 0, dim.width, dim.height); 
  	bufferGraphics.setColor(getForeground());
  	paint(bufferGraphics);   	  
  	g.drawImage(bufferImage, 0, 0, this); 
   }
  
  public void paint (Graphics g) {
    g.drawLine(0,100,brug.positie,100);
    g.drawLine(brug.positie+brug.lengte,100,400,100);
    brug.paint(g);
    auto1.paint(g, this);
    auto2.paint(g,this);
  }

  public void start() {         //starten van de applet thread.
    if (thread == null) {
      thread = new Thread(this);
      thread.start();
    }
  }

  public void run() {            //run methode van de applet thread
    while(doorgaan) {           //repaint om de 50 milliseconden
      repaint();
      try{Thread.sleep(12);}
      catch (InterruptedException e){}
    }
  }
  
  public void stop() {
    if (thread != null)  {
      thread = null;
      auto1.stop();
      doorgaan = false;
    }
  }
  
  public static void main(String[] args) {
	  new BrugApplet().start();
  }
}
