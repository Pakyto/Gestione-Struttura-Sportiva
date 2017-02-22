package classi;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import eccezioni.PrenotazioneEsistenteException;
import eccezioni.PrenotazioneNonEsistenteException;
import interfacce.OggettoProgrammato;
import utils.StatoPosto;

/**
 * Rappresenta il botteghino che si trova all'interno di uno stadio
 * @author Pasquale Settembre
 */
public class Botteghino extends Cassa implements Cloneable,Serializable{
	/**
	 * lista dei biglietti all'interno di un botteghino
	 * @see Botteghino#acquistaBiglietto(Prenotazione, int)
	 */
	private ArrayList<Biglietto> biglietti;
	/**
	 * lista di prenotazioni all'interno del botteghino
	 * @see Botteghino#prenota(OggettoProgrammato, int, String)
	 */
	private ArrayList<Prenotazione>prenotazioni;
	/**
	 * prezzo delle partite assegnato ad ogni botteghino di uno stadio
	 * @see Botteghino#getPrezzoPartite()
	 */
	private  float prezzoPartite;      
	
	/**
	 * Creazione di un botteghino 
	 * @param prezzo prezzo delle partite assegnate al botteghino
	 */
	public Botteghino(float prezzo){
		super();
		biglietti=new ArrayList<Biglietto>();
		prenotazioni=new ArrayList<Prenotazione>();
		prezzoPartite=prezzo;
	}

	/**
	 * Permette l'acquisto di un biglietto per cui si è effettuata una prenotazione
	 * @param p   rappresenta la prenotazione da cui effettuare l'acquisto del biglietto
	 * @param scontoUtente  rappresenta lo sconto dell'utente che varia a seconda della tipologia
	 */
	public void acquistaBiglietto(Prenotazione p,int scontoUtente){
		//mettere al posto di 0 lo sconto del cliente
		float prezzo=super.calcolaPrezzo(prezzoPartite,calcolaSconto(p.getPartita().getObjProgrammato().getSconto(),scontoUtente));
		biglietti.add(new Biglietto(p.getId(), p.getPosto(), p.getPartita(),prezzo));
		
		//se esiste gia una prenotazione la cancella
		if(prenotazioni.contains(p))
			prenotazioni.remove(p);
		
		//aggiorna l'incasso con il prezzo dell'ultimo biglietto che ha aggiunto
		float n=biglietti.get(biglietti.size()-1).getPrezzo();	
		super.aggiornaIncasso(n);
	}
	
	/**
	 * Restituisce l'incasso del botteghino
	 * @return incasso botteghino
	 */
	public float getIncasso(){
		return super.getIncasso();
	}
	
	/**
	 * Restituisce la lista dei biglietti all'interno del botteghino
	 * @return lista biglietti
	 */
	public ArrayList<Biglietto> getBiglietti() {
		return biglietti;
	}

	/**
	 * Restituisce la lista delle prenotazioni
	 * @return lista delle prenotazioni
	 */
	public ArrayList<Prenotazione> getListaPrenotazioni() {
		return prenotazioni;
	}
	
	/**
	 * Restituisce il prezzo delle partite 
	 * @return prezzo partite all'interno del botteghino 
	 */
	public  float getPrezzoPartite() {
		return prezzoPartite;
	}
	
	/**
	 * Permette di cambiare il prezzo delle partite
	 * @param p nuovo prezzo delle partite
	 */
	public void cambiaPrezzoPartite(float p){
		prezzoPartite=p;
	}

	/**
	 * Permette la prenotazione per una partita
	 * @param o  rappresenta la partita per cui si vuole effettuare una prenotazione
	 * @param posto  rappresenta il posto all'interno dello stadio scelto
	 * @param id    rappresenta l'username del cliente
	 * @throws PrenotazioneEsistenteException lancia l'eccezione se la prenotazione già esiste
	 */
	public void prenota(OggettoProgrammato o,int posto,String id) throws PrenotazioneEsistenteException{
		for(Prenotazione p:prenotazioni)
			if(p.getId().equals(id))
				if(p.getPartita().getTitle().equals(o.getTitle()))
					if(p.getPosto()==posto)
						throw new PrenotazioneEsistenteException();
					
		
			prenotazioni.add(new Prenotazione(id, posto, o));
				
	}
	
	
	public Object clone(){
		
		try{
			Botteghino b=(Botteghino)super.clone();
			b.cambiaPrezzoPartite(prezzoPartite);
			b.biglietti=(ArrayList<Biglietto>)biglietti.clone();
			b.prenotazioni=(ArrayList<Prenotazione>)prenotazioni.clone();
			return b;
		}catch(CloneNotSupportedException expection){
			return null;
		}
	}
		
	public boolean equals(Object s){
		if(s==null){
			return false;
		}
		
		if(getClass()!=s.getClass()){
			return false;
		}
		Botteghino bt = (Botteghino)s;
		return (prenotazioni.equals(bt.prenotazioni) && biglietti==bt.biglietti) ;
	}
	
	/**
	 * Restituisce la lista di prenotazioni di un determinato cliente
	 * @param username  rappresenta l'username del cliente 
	 * @return lista di prenotazioni del cliente con un determinato username
	 */
	public ArrayList<Prenotazione> getPrenotazioni(String username) {
		ArrayList<Prenotazione> listaPrenotazioni=new ArrayList<Prenotazione>();
		for(Prenotazione p:prenotazioni)
			if(p.getId().equals(username))
				listaPrenotazioni.add(p);
		return listaPrenotazioni;
	}

	
	/**
	 * Restituisce la lista di prenotazioni sottoforma di stringhe
	 * @param username
	 * @return
	 */
	public ArrayList<String> getListaPrenotazioni(String username) {
		ArrayList<String> sPrenotazioni=new ArrayList<String>();
		for(Prenotazione p:prenotazioni)
			if(p.getId().equals(username))
				sPrenotazioni.add(p.toString());
		return sPrenotazioni;
	
	}

	/**
	 * Restituisce la lista dei biglietti acquistati da parte di un determinato cliente
	 * @param username rappresenta l'username del cliente
	 * @return lista dei biglietti acquistati di un determinato cliente
	 */
	public ArrayList<Biglietto> getBiglietti(String username) {
		ArrayList<Biglietto> biglietti=new ArrayList<Biglietto>();
		for(Biglietto b:biglietti)
			if(b.getId().equals(username))
				biglietti.add(b);
		return biglietti;
	
	}
	
	public ArrayList<String> getListaBiglietti(String username) {
		ArrayList<String> sBiglietti=new ArrayList<String>();
		for(Biglietto b:biglietti)
			if(b.getId().equals(username))
				sBiglietti.add(b.toString());
		return sBiglietti;
	
	}
	
	/**
	 * Permette di cancellare una prenotazione
	 * @param prenotazione  rappresenta la prenotazione da cancellare
	 * @throws PrenotazioneNonEsistenteException lancia l'eccezione se la prenotazione non esiste
	 */
	public void cancellaPrenotazione(Prenotazione prenotazione) throws PrenotazioneNonEsistenteException {
		if(prenotazioni.contains(prenotazione))
		{	
			Prenotazione p=prenotazioni.get(prenotazioni.indexOf(prenotazione));
			p.getPartita().setPosto(p.getPosto(), StatoPosto.Libero);;
			prenotazioni.remove(prenotazione);
			}
		else
			throw new PrenotazioneNonEsistenteException();
	}
	
	/**
	 * Permette la cancellazione di prenotazioni scadute
	 */
	public void cancellaPrenotazioniScadute(){
		ArrayList<Prenotazione> prenotazioniScadute=new ArrayList<Prenotazione>();
			for(Prenotazione p:prenotazioni)
			{if(isExpiring(p.getPartita().getData()))
				prenotazioniScadute.add(p);
				
			}
			for(Prenotazione p:prenotazioniScadute)
				{try {
					cancellaPrenotazione(p);
					if(!prenotazioni.contains(p))
					System.out.println("scaduta");
					else
						System.out.println("non cancellata");
				} catch (PrenotazioneNonEsistenteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				}		
		}
	
		/**
		 * Controlla se una prenotazione è scaduta
		 * @param data data della partita
		 * @return vero se la data della partita è prima della scadenza della prenotazione,
		 * 	       falso altrimenti
		 */
		private boolean isExpiring(GregorianCalendar data){
			GregorianCalendar now=new GregorianCalendar();
			now.add(GregorianCalendar.HOUR,-12);
			if(data.before(now))
				return true;
			
			return false;
		}
}