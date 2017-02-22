package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;

import classi.CalendarioPartite;
import classi.GestioneStadi;
import classi.PartitaProgrammata;
import classi.Stadio;
import eccezioni.DatiNonValidiException;
import eccezioni.NomeStadioEsistenteException;
import eccezioni.PartitaNonInseribileException;
import eccezioni.PartitaProgrammataEsistenteException;
import eccezioni.PrenotazioneEsistenteException;
import eccezioni.UtenteEsistenteException;
import eccezioni.UtenteInesistenteException;
import grafica.Login;
import interfacce.OperazioniCliente;
import interfacce.OperazioniGestore;
import utils.Comparatore;
import utils.Tipologia;

public class main {

	public static void main(String[] args) {
		
		GestioneStadi stadi=new GestioneStadi();

		try {
			stadi.registraUtente("admin", "admin", Tipologia.Gestore);
			try {
				stadi.addStadio(new Stadio("San Siro", 200, 10));
				stadi.addStadio(new Stadio("San Pietro", 200, 10));
			} catch (NomeStadioEsistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UtenteEsistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stadi.inserisciPartita("milan", "napoli", new GregorianCalendar(), "San Siro");
			stadi.inserisciPartita("juve", "lazio", new GregorianCalendar(), "San Pietro");
		} catch (PartitaProgrammataEsistenteException | PartitaNonInseribileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Login inv = new Login(stadi);
		ArrayList<PartitaProgrammata>part=stadi.getPartiteNonIniziate();
		  Collections.sort(part,new Comparatore.LexicographicComparator());
		  System.out.println(part);
		
/*
CalendarioPartite calendario=new CalendarioPartite();
PartitaProgrammata p1,p2;
try {
	calendario.add(new PartitaProgrammata("Napoli", "Milan", new GregorianCalendar(), 100));
	calendario.add(new PartitaProgrammata("Napoli", "inter", new GregorianCalendar(), 50));
} catch (PartitaProgrammataEsistenteException e) {
	System.out.println("partita esistente");
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (PartitaNonInseribileException e) {
	// TODO Auto-generated catch block
	System.out.println("partita non inseribile");
	e.printStackTrace();
}


System.out.println(calendario.clone());

*/
/*
GestioneStadi gestioneStadi=new GestioneStadi();
OperazioniGestore opG;
OperazioniCliente opC;
ArrayList<PartitaProgrammata> partite=new ArrayList<PartitaProgrammata>();

GregorianCalendar data=new GregorianCalendar();
partite.add(new PartitaProgrammata("Napoli", "Milan",data, 100));
data.add(GregorianCalendar.DAY_OF_MONTH, 1);
partite.add(new PartitaProgrammata("Napoli", "Milan",data, 100));
data.add(GregorianCalendar.DAY_OF_MONTH, 1);
partite.add(new PartitaProgrammata("Napoli", "Milan",data, 100));
data.add(GregorianCalendar.DAY_OF_MONTH, 1);
partite.add(new PartitaProgrammata("Napoli", "Milan",data, 100));
data.add(GregorianCalendar.DAY_OF_MONTH, 1);
partite.add(new PartitaProgrammata("Napoli", "Milan",data, 100));
data.add(GregorianCalendar.DAY_OF_MONTH, 1);
partite.add(new PartitaProgrammata("Napoli", "Milan",data, 100));
data.add(GregorianCalendar.DAY_OF_MONTH, 1);
partite.add(new PartitaProgrammata("Napoli", "Milan",data, 100));

data.add(GregorianCalendar.DAY_OF_MONTH, 1);
partite.add(new PartitaProgrammata("Napoli", "Milan",data, 100));
data.add(GregorianCalendar.DAY_OF_MONTH, 1);
partite.add(new PartitaProgrammata("Napoli", "Milan",data, 100));
data.add(GregorianCalendar.DAY_OF_MONTH, 1);
partite.add(new PartitaProgrammata("Napoli", "juve",data, 100));*/

/*try {
	gestioneStadi.addStadio(new Stadio("San Siro", 1000, 10,partite));
	gestioneStadi.registraUtente("ilaria", "mosca", Tipologia.Studente);
	gestioneStadi.accedi("ilaria", "mosca");
	System.out.println(gestioneStadi.getUtenti());
	gestioneStadi.attivaSconto(10, Tipologia.Studente);
	
	opC=gestioneStadi.getUtente("ilaria").getOperazioni();
	opC.prenota(0, "ilaria", 2, "Napoli", "Milan", new GregorianCalendar());
//	gestioneStadi.prenota(0, "ilaria", 2, "Napoli", "Milan", new GregorianCalendar());
//	gestioneStadi.acquista("San Siro", "ilaria", 2, "Napoli", "Milan", new GregorianCalendar());
//	gestioneStadi.prenota(0, "ilaria", 3, "Napoli", "Milan", new GregorianCalendar());
	System.out.println(gestioneStadi.getUtenti());
	System.out.println(gestioneStadi.getAcquisti("ilaria"));
	System.out.println(gestioneStadi.getPrenotazioni("ilaria"));
} catch (NomeStadioEsistenteException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (PrenotazioneEsistenteException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (UtenteInesistenteException e) {

	e.printStackTrace();
} catch (DatiNonValidiException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
*/
	}
	
	
	  

}