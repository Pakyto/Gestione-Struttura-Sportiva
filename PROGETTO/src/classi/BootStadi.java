package classi;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import eccezioni.NomeStadioEsistenteException;
import eccezioni.UtenteEsistenteException;
import grafica.Login;
import utils.Caricamento;
import utils.Salvataggio;
import utils.Tipologia;

/**
 * Permette l'avvio del programma
 * @author Pasquale Settembre
 */
public class BootStadi {
	/**
	 * @see Attributo file, che rappresenta il file dove vengono caricate e salvate 
	 * le informazioni
	 */
	private static File file = new File("stadi.dat");
	private static GestioneStadi stadi;
	private static Login principale;
	
	/**
	 * @param args null
	 * @throws FileNotFoundException lancia l' eccezione se il file non esiste
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		if(file.exists()){
		stadi=Caricamento.Carica();
	
		Salvataggio.salva(stadi);
		}else
			{stadi=new GestioneStadi();
			try {
				stadi.registraUtente("admin", "admin", Tipologia.Gestore);
				stadi.registraUtente("c", "c", Tipologia.Studente);
				stadi.registraUtente("a", "a", Tipologia.Bambino);
				stadi.registraUtente("f", "f", Tipologia.Pensionato);
				try {
					stadi.addStadio(new Stadio("San Siro", 200, 10));
				} catch (NomeStadioEsistenteException e) {
					e.printStackTrace();
				}
			} catch (UtenteEsistenteException e) {
				e.printStackTrace();
			}
			Salvataggio.salva(stadi);
			}
				
			principale= new Login(stadi);	
				
	}
	
	
}
