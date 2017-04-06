import java.io.*;

public class Werknemer implements Serializable
{
  private int persnr;
  private String naam;
  private String functie;
  private int sal;
  private transient Kantoor kantoor;

  public Werknemer()
  {
  }

  public int getPersnr()
  {
    return persnr;
  }

  public void setPersnr(int persnr)
  {
    this.persnr = persnr;
  }

  public String getNaam()
  {
    return naam;
  }

  public void setNaam(String naam)
  {
    this.naam = naam;
  }

  public String getFunctie()
  {
    return functie;
  }

  public void setFunctie(String functie)
  {
    this.functie = functie;
  }

  public int getSal()
  {
    return sal;
  }

  public void setSal(int sal)
  {
    this.sal = sal;
  }

  public Kantoor getKantoor()
  {
    return kantoor;
  }

  public void setKantoor(Kantoor kantoor)
  {
    this.kantoor = kantoor;
  }
 
  public void writeObject(ObjectOutputStream out) throws IOException {
	out.writeInt(this.getPersnr());  
	out.writeUTF(this.getNaam());
	out.writeUTF(this.getFunctie());
	out.writeInt(this.getSal());  
	Kantoor kantoor = this.getKantoor();
	out.writeInt(kantoor.getKantnr());  
	out.writeUTF(kantoor.getNaam());  
	out.writeUTF(kantoor.getPlaats());  
  }

  public void readObject(ObjectInputStream in) throws IOException,
  ClassNotFoundException {
	this.persnr = in.readInt();
    this.naam = in.readUTF();
    this.functie = in.readUTF();
	this.sal = in.readInt();
	Kantoor kantoor = new Kantoor();
	kantoor.setKantnr(in.readInt());
	kantoor.setNaam(in.readUTF());
	kantoor.setPlaats(in.readUTF());
	this.kantoor = kantoor;
  }
}
