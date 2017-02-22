package classi;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import eccezioni.PartitaNonInseribileException;
import eccezioni.PartitaProgrammataEsistenteException;
/**
 * Calendario delle partite programmate all'interno di uno stadio
 * @author Pasquale Settembre
 */
public class CalendarioPartite implements Cloneable,Serializable {
	/**Rappresenta la lista di partite all'interno del calendario
	 * @see CalendarioPartite#add(String, String, GregorianCalendar, int)
	 */
	private ArrayList<PartitaProgrammata> partiteProgrammate;
	
	/**
	 * Creazione del calendario delle partite di uno stadio
	 * @param partite partite da inserire quando viene creato il calendario
	 */
	public CalendarioPartite(ArrayList<PartitaProgrammata> partite){
		partiteProgrammate=partite;
	}
	
	/**
	 * Creazione calendario partite senza l'inserimento di partite
	 */
	public CalendarioPartite(){
		partiteProgrammate=new ArrayList<PartitaProgrammata>();
	}
	
	/**
	 * Inserimento di una partita all'interno del calendario
	 * @param squadra1  rappresenta la prima squadra
	 * @param squadra2	rappresenta la seconda squadra 
	 * @param data		rappresenta la data in cui si svolge la partita
	 * @param settore	rappresenta il numero di posti all'interno dello stadio per la partita
	 * @throws PartitaProgrammataEsistenteException lancia l'eccezione se la partita già si trova all'interno del calendario
	 * @throws PartitaNonInseribileException lancia l'eccezione se la partita da inserire ha la stessa data e ora di un'altra partita che già si trova nel calendario
	 */
	public void add(String squadra1,String squadra2,GregorianCalendar data,int settore) throws PartitaProgrammataEsistenteException, PartitaNonInseribileException
	{
		PartitaProgrammata p=new PartitaProgrammata(squadra1, squadra2, data, settore);
		if (partiteProgrammate.contains(p))
		throw new PartitaProgrammataEsistenteException();
		else
			for(PartitaProgrammata part:partiteProgrammate)
				if(part.isEqualsDate(p.getData()))
					throw new PartitaNonInseribileException();
			partiteProgrammate.add(p);
	}
	
	/**
	 * Restituisce la lista delle partite programmate nel calendario
	 * @return  lista partite
	 */
	public ArrayList<PartitaProgrammata> getPartiteProgrammate() {
		return partiteProgrammate;
	}

	public Object clone(){
	
	CalendarioPartite calendario;
	try {
		calendario = (CalendarioPartite)super.clone();	
	//	calendario.partiteProgrammate=partiteProgrammate;
		return calendario;
	} catch (CloneNotSupportedException e) {
		return null;
		
	}
}
	public String toString(){
		String partite="";
		for(PartitaProgrammata p:partiteProgrammate)
			partite.concat(p.toString()+" ");
		return getClass().getSimpleName()+partiteProgrammate.toString();
	}
	
	/**
	 * Viene impostato lo sconto per una determinata partita
	 * @param squadra1   rappresenta la prima squadra 
	 * @param squadra2	 rappresenta la seconda squadra
	 * @param data		 rappresenta la data in cui si svolge la partita
	 * @param sconto	 percentuale di sconto da applicare sulla partita
	 */
	public void setSconto(String squadra1,String squadra2,GregorianCalendar data,int sconto)
	{
		PartitaProgrammata p=new PartitaProgrammata(squadra1, squadra2, data, sconto);
			for(PartitaProgrammata s:partiteProgrammate){
				if(s.getsquadra1().equals(p.getsquadra1()) && s.getSquadra2().equals(p.getSquadra2())){
					if(s.getData().equals(p.getData())){
						s.setSconto(sconto);
					}
				}
				}
	}
	
	/**
	 * Controlla se una partita si svolge prima o dopo una certa data
	 * @param data rappresenta la data dove si vuole verificare l'esistenza di partite
	 * @return
	 */
	public boolean isInWeek(GregorianCalendar data)
	{
		GregorianCalendar dataOggi=new GregorianCalendar();
		GregorianCalendar dataFineSettimana=new GregorianCalendar();
		dataFineSettimana.add(GregorianCalendar.DAY_OF_MONTH, 7);
		if(data.before(dataOggi)||data.after(dataFineSettimana))
			return false;
		else return true;
	}
	
	/**
	 * Controlla se una partita è iniziata o meno
	 * @param data  rappresenta la data per effettuare il controllo
	 * @return
	 */
	public boolean isStarted(GregorianCalendar data)
	{
		GregorianCalendar dataOggi=new GregorianCalendar();
		
		if(data.before(dataOggi))
			return true;
		
		else return false;
	}

	/**
	 * Restituisce i dettagli di una partita
	 * @param squadra1 rappresenta la prima squadra
	 * @param squadra2 rappresenta la seconda squadra
	 * @param data   rappresenta la data della partita
	 * @return le informazioni della partita
	 */
	public PartitaProgrammata getPartitaProgrammata(String squadra1, String squadra2, GregorianCalendar data) {
		for(PartitaProgrammata p:partiteProgrammate)
			if(p.getsquadra1().equals(squadra1))
				if(p.getSquadra2().equals(squadra2))
					if(p.isEqualsDate(data))
						return p;
		return null;
		
	}
	
}


