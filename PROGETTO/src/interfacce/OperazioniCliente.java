package interfacce;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import classi.Biglietto;
import classi.PartitaProgrammata;
import classi.Prenotazione;
import eccezioni.PrenotazioneEsistenteException;
import eccezioni.PrenotazioneNonEsistenteException;
import eccezioni.UtenteInesistenteException;

public interface OperazioniCliente{
	
	ArrayList<PartitaProgrammata> getPartiteSettimanali();
	ArrayList<PartitaProgrammata> getPartite(String nomeStadio);
	ArrayList<PartitaProgrammata> getPartiteNonIniziate();
	ArrayList<Prenotazione> getPrenotazioni(String username);
	ArrayList<String> getAcquisti(String username) throws UtenteInesistenteException;
	void acquista(Prenotazione p,String username) throws UtenteInesistenteException;
	void acquista(String nomeStadio,String username,int posto,String squadra1,String squadra2,GregorianCalendar data) throws UtenteInesistenteException;
	void prenota(String idStadio,String username,int posto,String squadra1,String squadra2,GregorianCalendar data) throws PrenotazioneEsistenteException;
	void cancellaPrenotazione(Prenotazione p) throws PrenotazioneNonEsistenteException;

}

