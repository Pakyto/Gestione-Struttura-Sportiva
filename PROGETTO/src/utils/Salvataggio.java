package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import classi.GestioneStadi;

/**
 * oggetto di supporto per salvare l' oggetto gestioneStadi nel file
 */
public class Salvataggio {

	/**
	 * salva l' oggetto gestionestadi nel file
	 */
	public static void salva(GestioneStadi s){
			ObjectOutputStream out = null;
			try {
				out = new ObjectOutputStream(new FileOutputStream("stadi.dat"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.writeObject(s);                //SCRITTURA SUL FILE
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();                      //CHIUSURA FILE
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
		
	
}
