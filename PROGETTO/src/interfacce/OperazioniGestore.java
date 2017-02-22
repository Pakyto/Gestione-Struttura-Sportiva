package interfacce;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import classi.PartitaProgrammata;
import classi.Stadio;
import eccezioni.PartitaNonInseribileException;
import eccezioni.PartitaProgrammataEsistenteException;
import eccezioni.StadioEsistentException;
import utils.Tipologia;
public interface OperazioniGestore{
	
	ArrayList<PartitaProgrammata> getPartite();
	void aggiungiStadio(String nome,int capienza,float prezzo) throws StadioEsistentException;
	void inserisciPartita(String squadra1,String squadra2,GregorianCalendar data,String nomeStadio)throws PartitaProgrammataEsistenteException, PartitaNonInseribileException;
	void attivaSconto(int perc,Tipologia t);
	void attivaSconto(int perc,PartitaProgrammata p);
	void assegnaPrezzo(float prezzo,String nomeStadio);
	void setCapienzaStadi(ArrayList<String> stadi,int capienza);
	void setCapienzaStadio(String stadio,int capienza);
	float getIncassoTotale();
	float getIncassoStadio(String nome);
	int getCapienza(String stadio);
}
