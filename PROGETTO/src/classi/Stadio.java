package classi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import eccezioni.PartitaNonInseribileException;
import eccezioni.PartitaProgrammataEsistenteException;
import eccezioni.PrenotazioneEsistenteException;
import eccezioni.PrenotazioneNonEsistenteException;
import interfacce.Sconto;
import interfacce.Utente;
/**
 * Lo stadio è la classe con cui si interfaccia {@link GestioneStadi} per qualsiasi operazione
 * @author Ilaria Mosca
 *
 */
public class Stadio implements Cloneable,Serializable{

	private static final long serialVersionUID = 1L;
	private String nome;
	/**
	 * Ogni stadio ha le proprie partite programmate
	 */
	private CalendarioPartite calendarioPartite;
	private int capienza;
	/**
	 * Ogni stadio ha un botteghino per acquistare e prenotare i biglietti
	 */
	private Botteghino botteghino;

	
	
	public Stadio(String nome, int capienza,float prezzo) {
		this.nome = nome;
		this.capienza = capienza;
		calendarioPartite=new CalendarioPartite();
		botteghino=new Botteghino(prezzo);
	}

	public Stadio(String nome, int capienza,int prezzo,ArrayList<PartitaProgrammata> partite) {
		this.nome = nome;
		this.capienza = capienza;
		calendarioPartite=new CalendarioPartite(partite);
		botteghino=new Botteghino(prezzo);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CalendarioPartite getCalendarioPartite() {
		return calendarioPartite;
	}

	public void setCalendarioPartite(CalendarioPartite calendarioPartite) {
		this.calendarioPartite = calendarioPartite;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
		Settore.setCapienzaMax(capienza);
	}

	public Botteghino getBotteghino() {
		return botteghino;
	}
	
	public void setBotteghino(Botteghino botteghino) {
		this.botteghino = botteghino;
	}
	
	public String toString(){
		return "["+getNome()+getCapienza()+getBotteghino().getIncasso()+"]";
	}
	
	//interfaccia stadio 
	
		//gestore
		
		/**
		 * @return incasso dello stadio
		 */
		public float getIncasso(){
			return botteghino.getIncasso();
		}
		
		/**
		 * Assegna il prezzo delle partite nello stadio
		 * @param prezzo prezzo delle partite
		 */
		public void assegnaPrezzo(float prezzo){
			botteghino.cambiaPrezzoPartite(prezzo);
		}
		
		/**
		 * Attivazione dello sconto su una partita
		 * @param percentuale percentuale di sconto
		 * @param squadra1 prima squadra
		 * @param squadra2 seconda squadra
		 * @param data  data partita
		 */
		public void attivaSconto(int percentuale,String squadra1,String squadra2,GregorianCalendar data){
			
			calendarioPartite.setSconto(squadra1,squadra2,data,percentuale);
			
		}
		
		/**
		 * Inserimento di una partita nel calendario delle partite
		 * @param squadra1	prima squadra della partita
		 * @param squadra2  seconda squadra della partita
		 * @param data		data della partita
		 * @throws PartitaProgrammataEsistenteException lancia l'eccezione se la partita già esiste nel calendario
		 * @throws PartitaNonInseribileException lancia l'eccezione se si vuole inserire una partita con la stessa data di un'altra partita già in calendario
		 */
		public void aggiungiPartita(String squadra1,String squadra2,GregorianCalendar data) throws PartitaProgrammataEsistenteException, PartitaNonInseribileException
		{
			calendarioPartite.add(squadra1,squadra2,data,capienza);
		}
		
		
		//operazioni cliente
		/**
		 * Prenotazione per una partita
		 * @param username username dell'utente	
		 * @param posto posto da prenotare
		 * @param squadra1 prima squadra della partita
		 * @param squadra2 seconda squadra della partita
		 * @param data  data della partita
		 * @throws PrenotazioneEsistenteException lancia l'eccezione se la prenotazione già esiste
		 */
		public void prenota(String username,int posto,String squadra1,String squadra2,GregorianCalendar data) throws PrenotazioneEsistenteException{
			
			botteghino.prenota(calendarioPartite.getPartitaProgrammata(squadra1,squadra2,data),posto,username);
		}
		
	
		public ArrayList<PartitaProgrammata> getPartiteSettimanali(){
			ArrayList<PartitaProgrammata> partiteSettimanali=new ArrayList<PartitaProgrammata>();
			ArrayList<PartitaProgrammata> partite=calendarioPartite.getPartiteProgrammate();
			for(PartitaProgrammata partita:partite)
				if(calendarioPartite.isInWeek(partita.getData()))
					partiteSettimanali.add(partita);
			
			return partiteSettimanali;
		}
	
		public ArrayList<PartitaProgrammata> getPartite() {
			
					return calendarioPartite.getPartiteProgrammate();
		}

		public ArrayList<PartitaProgrammata> getPartiteNonIniziate() {
			
			ArrayList<PartitaProgrammata> partiteNonIniziate=new ArrayList<PartitaProgrammata>();
			
				ArrayList<PartitaProgrammata> partite=calendarioPartite.getPartiteProgrammate();
				for(PartitaProgrammata partita:partite){
					if(!calendarioPartite.isStarted(partita.getData()))
						partiteNonIniziate.add(partita);
				}
				return partiteNonIniziate;
		}
	
		
		public ArrayList<Prenotazione> getPrenotazioni(String username) {
			
			return botteghino.getPrenotazioni(username);
		}
		
		public ArrayList<String> getAcquisti(String username) {
		
			return botteghino.getListaBiglietti(username);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Stadio other = (Stadio) obj;
		
			if (capienza != other.capienza)
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			return true;
		}
		
	public PartitaProgrammata getPartita(String squadra1,String squadra2,GregorianCalendar data){
		return 	calendarioPartite.getPartitaProgrammata(squadra1, squadra2, data);
	}
		/**
		 * Acquisto di un biglietto di cui si è effettuata una prenotazione
		 * @param p prenotazione
		 * @param u utente per l'applicazione dello sconto
		 */
		public void acquistaBiglietto(Prenotazione p,Utente u){
			botteghino.acquistaBiglietto(p, ((Sconto)u.getUtente()).getSconto());
		}

		/**
		 * Cancellazione di una prenotazione
		 * @param p prenotazione
		 * @throws PrenotazioneNonEsistenteException lancia l'eccezione se la prenotazione non esiste
		 */
		public void cancellaPrenotazione(Prenotazione p) throws PrenotazioneNonEsistenteException{
			botteghino.cancellaPrenotazione(p);
		}
		
		
		public void cancellaPrenotazioniScadute(){
			botteghino.cancellaPrenotazioniScadute();
		}
	}


