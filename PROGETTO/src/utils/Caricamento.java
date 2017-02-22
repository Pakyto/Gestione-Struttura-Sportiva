package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import classi.GestioneStadi;

/**
 * oggetto di supporto per caricare la gestione stadio dal file
 */
public class Caricamento {
	private GestioneStadi c;
	private static File file = new File("stadi.dat");
	
	public Caricamento() {
		
	}
	
	/**
	 * Carica la gestione stadio dal file se esiste
	 * @return ritorna la gestione stadio appena caricata
	 */
	public static GestioneStadi Carica(){
		
		if(file.exists()){
			
			ObjectInputStream in=null;
			try {
				in = new ObjectInputStream(new FileInputStream(file));
			} catch (IOException e) {
				e.printStackTrace();
			} 
			try {
				return (GestioneStadi) in.readObject();   //LETTURA DAL FILE
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} 
			try {
				in.close();                            //CHIUSURA FILE
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
