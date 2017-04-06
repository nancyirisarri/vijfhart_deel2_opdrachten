import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

public class WerknemersKantoren {
	// een dubbele backslash om niet aan te geven dat er een escape karakter
	// gebruikt moet worden
	static Path path = Paths.get("").toAbsolutePath();
	static String lokatie = path.toAbsolutePath().toString() + File.separator;

	static File f = new File(lokatie + "werknemers_kantoren.txt");

	public static void main(String[] args) {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println(s);

		maakObjecten();
		leesObjecten();
	}

	static void maakObjecten() {
		try {
			// read the text file
			FileReader in = new FileReader(f);
			LineNumberReader lineIn = new LineNumberReader(in);

			boolean end = false;
			while (!end) {
				try {
					Werknemer werkn = new Werknemer();
					// eigenschappen werknemer inlezen

					Kantoor kant = new Kantoor();
					// eigenschappen kantoor inlezen en kantoor aan werknemer
					// koppelen

					String tekst = lineIn.readLine();
					int ctr = 0;
					while (tekst != null && tekst.length() != 0) {
						switch (ctr) {
						case 0: {
							werkn.setPersnr(Integer.parseInt(tekst));
							break;
						}
						case 1: {
							werkn.setNaam(tekst);
							break;
						}
						case 2: {
							werkn.setFunctie(tekst);
							break;
						}
						case 3: {
							werkn.setSal(Integer.parseInt(tekst));
							break;
						}
						case 4: {
							kant.setKantnr(Integer.parseInt(tekst));
							break;
						}
						case 5: {
							kant.setNaam(tekst);
							break;
						}
						case 6: {
							kant.setPlaats(tekst);
							werkn.setKantoor(kant);
							break;
						}
						}
						ctr++;
						tekst = lineIn.readLine();
					}

					// de achtste regel is de scheidingsregel naar de volgende
					// werknemer
					// als deze niet bestaat is het einde van de file bereikt.
					// if (lineIn.readLine() == null)
					if (tekst == null) {
						end = true;
						System.out.println("reached EOF");
					}

					// Schrijf de werknemer weg als object:
					FileOutputStream f = new FileOutputStream(lokatie + werkn.getNaam() + ".obj");
					ObjectOutputStream out = new ObjectOutputStream(f);
					werkn.writeObject(out);
					// out.writeObject(werkn);
					out.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
				// catch (ClassNotFoundException cnfe)
				// {
				// cnfe.printStackTrace();
				// }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	static void leesObjecten() {
		// Collection<Werknemer> werknemers = new Vector<Werknemer>();
		Vector<Werknemer> werknemers = new Vector<Werknemer>();
		File dir = new File(lokatie);
		String[] listing = dir.list();
		for (int i = 0; i < listing.length; i++) {
			if (listing[i].indexOf(".obj") != -1) {
				try {
					// lees werknemer in en voeg toe aan vector werknemers
					FileInputStream f = new FileInputStream(lokatie + listing[i]);
					ObjectInputStream in = new ObjectInputStream(f);

					Werknemer werknr = new Werknemer();
					werknr.readObject(in);

					werknemers.add(werknr);
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (werknemers != null) {
			File f = new File(lokatie + "werknemers_tabel.csv");
			if (f.exists())
				f.delete();

			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter(f));
				// en schrijf elke werknemer als regel weg in
				// werknemers_tabel.csv
				for (Werknemer werkn : werknemers) {
					String to_write = werkn.getPersnr() + "," + werkn.getNaam() + ",";
					to_write += werkn.getFunctie() + "," + werkn.getSal() + ",";

					Kantoor kantoor = werkn.getKantoor();

					to_write += kantoor.getKantnr() + "," + kantoor.getNaam() + ",";
					to_write += kantoor.getPlaats() + "\n";
					bw.write(to_write);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bw.close();
				} catch (IOException ioe) {
					System.out.println("problem closing file");
				}
			}
		}

	}
}
