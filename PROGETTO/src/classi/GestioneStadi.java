package classi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import eccezioni.DatiNonValidiException;
import eccezioni.NomeStadioEsistenteException;
import eccezioni.PartitaNonInseribileException;
import eccezioni.PartitaProgrammataEsistenteException;
import eccezioni.PrenotazioneEsistenteException;
import eccezioni.PrenotazioneNonEsistenteException;
import eccezioni.StadioEsistentException;
import eccezioni.UtenteEsistenteException;
import eccezioni.UtenteInesistenteException;
import interfacce.OggettoProgrammato;
import interfacce.OperazioniCliente;
import interfacce.Sconto;
import interfacce.OperazioniGestore;
import interfacce.Utente;
import utils.Tipologia;
/**
 * Rappresenta la interfaccia per la gestione di stadi
 * @author Pasquale Settembre
 */
public class GestioneStadi implements Cloneable,Serializable,OperazioniCliente,OperazioniGestore {
	/**
	 * Lista di stadi
	 * @see GestioneStadi#addStadio(Stadio)
	 */
	private ArrayList<Stadio>stadi;
	
	/**
	 * Lista di utenti
	 * @see GestioneStadi#registraUtente(String, String, Tipologia)
	 */
	private ArrayList<Utente>utenti;
	
	/**
	 * Creazione della gestione degli stadi
	 */
	public GestioneStadi(){
		stadi=new ArrayList<Stadio>();
		utenti=new ArrayList<Utente>();
	}
	
	/**
	 * Permette di attivare lo sconto su un cliente in base alla tipologia
	 * @param percentuale percentuale di sconto da applicare
	 * @param t  tipologia di cliente
	 */
	public void attivaSconto(int percentuale,Tipologia t){
		for(Utente u:utenti){
			if(	u.getTipologia().equals(t.toString())){
				((Sconto)u.getUtente()).setSconto(percentuale);
			}
		}
	}
	
	/**
	 * Aggiunge uno stadio 
	 * @param s  stadio da aggiungere
	 * @throws NomeStadioEsistenteException lancia l'eccezione se esiste uno stadio con lo stesso nome
	 */
	public void addStadio(Stadio s)throws NomeStadioEsistenteException {
		if(stadi.contains(s)){
			throw new NomeStadioEsistenteException();
		}
		stadi.add(s);
	}
	
	
	/**
	 * @param nomeStadio  nome dello stadio da restituire
	 * @return  restituisce le informazioni di un determinato stadio
	 */
	private Stadio getStadio(String nomeStadio){
		
		for(Stadio s:stadi)
			if (s.getNome().equals(nomeStadio))
				return s;
		return null;
	}
	
	/**
	 * 
	 * @return restituisce la lista degli stadi
	 */
	public ArrayList<Stadio> getStadi(){
		return stadi;	
		}
	
	
	public ArrayList<String> getListStadi(){
		ArrayList<String> list=new ArrayList<>();
		for(Stadio s:stadi)
			list.add(s.getNome());
		return list;	
		}
	
	/**
	 * Inserimento di una partita
	 * @param squadra1 prima squadra della partita
	 * @param squadra2 seconda squadra della partita
	 * @param data  data della partita
	 * @param nomeStadio nome dello stadio dove inserire le partite
	 * @throws PartitaProgrammataEsistenteException lancia l'eccezione se la partita già esiste
	 * @throws PartitaNonInseribileException lancia l'eccezione se la partita da inserire ha la stessa data e ora di una partita nel calendario
	 */
	public void inserisciPartita(String squadra1,String squadra2,GregorianCalendar data,String nomeStadio)throws PartitaProgrammataEsistenteException, PartitaNonInseribileException{
		
			getStadio(nomeStadio).aggiungiPartita(squadra1,squadra2,data);
		}
	
	
	/**
	 * Permette di assegnare il prezzo alle partite in uno stadio
	 * @param prezzo costo delle partite
	 * @param nomeStadio  nome dello stadio
	 */
	public void assegnaPrezzo(float prezzo,String nomeStadio){
		for(Stadio s:stadi){
			if(s.getNome().equals(nomeStadio)){
				s.assegnaPrezzo(prezzo);
			}
		}
	}
	
	/**
	 * Imposta la capienza degli stadi
	 * @param nomeStadi lista di nomi degli stadi
	 * @param capienza valore della capienza da impostare negli stadi
	 */
	public void setCapienzaStadi(ArrayList<String> nomeStadi,int capienza){
		for(String s:nomeStadi)
		{
			getStadio(s).setCapienza(capienza);
		}
	}
	
	/**
	 * @return restituisce l'incasso totale degli stadi
	 */
	public float getIncassoTotale(){
		float inc=0;
		for(Stadio s:stadi){
			inc=inc+s.getBotteghino().getIncasso();
		}
		return inc;
	}
	
	/**
	 * @param nome nome dello stadio
	 * @return restituisce l'incasso di un determinato stadio
	 */
	public float getIncassoStadio(String nome){
		
		return getStadio(nome).getIncasso(); 
	}	
	
	/**
	 * Permette la registrazione di un nuovo cliente
	 * @param username username scelto dal cliente
	 * @param password password scelta dal cliente
	 * @param tipo  tipologia di cliente
	 * @throws UtenteEsistenteException lancia l'eccezione se esiste un utente con username uguale
	 */
	public void registraUtente(String username,String password,Tipologia tipo) throws UtenteEsistenteException
	{
		Cliente c=new Cliente();
		for(Utente u:utenti){
			if(u.getUsername().equals(username))
				throw new UtenteEsistenteException();
		}
		utenti.add(c.registrati(username, password, tipo));
		((Sconto)getUtente(username)).setSconto(getSconto(tipo));
	}
	
	/**
	 * 
	 * @param t  tipologia di cliente
	 * @return  restituisce lo sconto del cliente
	 */
	private int getSconto(Tipologia t){
		for(Utente u:utenti)
			if(u.getTipologia().equals(t.toString()))
			{
				return ((Sconto)u.getUtente()).getSconto();
			}
		return 0;
	}

	/**
	 * Permette l'accesso alla struttura ad un utente
	 * @param username  username dell'utente
	 * @param password  password dell'utente
	 * @throws DatiNonValidiException lancia l'eccezione se i dati inseriti non sono corretti
	 */
	public void accedi(String username,String password) throws DatiNonValidiException
	{boolean b=false;
		for (Utente u:utenti){
			if((u.getUsername().equals(username))&&(u.getPassword().equals(password)))
			{	u.accedi(this);	
				b=true;
				break;
			}
		}
		
		if(!b)
		throw new DatiNonValidiException();
	
	}
	
	//interfaccia cliente
	
		/**
		 * questo metodo ritorna la lista delle partite settimanali partendo dalla data attuale
		 * @return partiteSettimanali
		 */
		
		public ArrayList<PartitaProgrammata> getPartiteSettimanali() {
			ArrayList<PartitaProgrammata> partiteSettimanali=new ArrayList<PartitaProgrammata>();
			
			for(Stadio stadio:stadi)
			{partiteSettimanali.addAll(stadio.getPartiteSettimanali());
			}
				return partiteSettimanali;
		}
		
	/**
	 * Restituisce le partite programmate in uno stadio
	 * @param nomeStadio nome dello stadio
	 * @return lista partite
	 */
	public ArrayList<PartitaProgrammata> getPartite(String nomeStadio) {
		for(Stadio s:stadi)
			if(s.getNome().equals(nomeStadio))
				return s.getPartite();
	return null;
	}
	
	
	/**
	 * Restituisce la lista delle partite ancora non iniziate dalla data attuale in poi
	 * @return lista partite non iniziate
	 */
	public ArrayList<PartitaProgrammata> getPartiteNonIniziate() {
		ArrayList<PartitaProgrammata> partiteNonIniziate=new ArrayList<PartitaProgrammata>();
		for(Stadio stadio:stadi)
		{partiteNonIniziate.addAll(stadio.getPartiteNonIniziate());
		}
			return partiteNonIniziate;
	}
	

	/**
	 * Restituisce le prenotazioni di un cliente
	 * @param username username del cliente
	 * @return lista di prenotazioni del cliente
	 */
	public ArrayList<Prenotazione> getPrenotazioni(String username) {
		ArrayList<Prenotazione> prenotazioniUtente=new ArrayList<Prenotazione>();
		for(Utente utente:utenti)
			if(utente.getUsername().equals(username))
			{
				for(Stadio stadio:stadi){
					prenotazioniUtente.addAll(stadio.getPrenotazioni(username));		
				}
			}
		return prenotazioniUtente;
	}
	
	/**
	 * Restituisce la lista dei biglietti acquistati da un cliente
	 * @param username username del cliente
	 * @return lista di biglietti acquistati dal cliente
	 */
	public ArrayList<String> getAcquisti(String username) throws UtenteInesistenteException {
		ArrayList<String> acquistiUtente=new ArrayList<String>();
		boolean flag=false;
		for(Utente utente:utenti)
			if(utente.getUsername().equals(username))
			{flag=true;
				for(Stadio stadio:stadi){
					acquistiUtente.addAll(stadio.getAcquisti(username));
					}
				
			}
		if(!flag)
			throw new UtenteInesistenteException();
		return acquistiUtente;
		
	}
	
	/**
	 * Permette la prenotazione di una partita
	 * @param idStadio nome dello stadio
	 * @param username username del cliente
	 * @param posto posto scelto per la prenotazione
	 * @param squadra1 prima squadra della partita
	 * @param squradra2 seconda squadra della partita
	 * @param data data della partita
	 * @throws PrenotazioneNonEsistenteException lancia l'eccezione se la prenotazione già esiste
	 */
	public void prenota(String idStadio,String username,int posto,String squadra1,String squadra2,GregorianCalendar data) throws PrenotazioneEsistenteException{
		getStadio(idStadio).prenota(username, posto, squadra1, squadra2, data);
	}

	/**
	 * Restituisce la lista degli utenti 
	 * @return lista utenti
	 */
	public ArrayList<String> getUtenti() {
		ArrayList<String> listaUtenti=new ArrayList<String>();
		for(Utente u:utenti)
			if(u.getTipologia().equals("gestore"))
				listaUtenti.add(u.getUsername()+" "+u.getPassword()+" "+u.getTipologia()+"\n");
			else
			listaUtenti.add(u.getUsername()+" "+u.getPassword()+" "+u.getTipologia()+" "+((Sconto)u.getUtente()).getSconto()+"\n");
	
	
		return listaUtenti;
	}
	
	/**
	 * Restituisce un determinato utente
	 * @param username username del cliente
	 * @return restituisce l'utente
	 */
	public Utente getUtente(String username){
		for(Utente u:utenti)
			if(u.getUsername().equals(username))
				return u;
		return null;
	}

	/**
	 * Inserimento di uno stadio
	 * @param nome nome dello stadio da inserire
	 * @param capienza capienza dello stadio
	 * @param prezzo costo delle partite all'interno dello stadio
	 * @throws StadioEsistentException lancia l'eccezione se lo stadio già esiste
	 */
	public void aggiungiStadio(String nome, int capienza, float prezzo) throws StadioEsistentException {
		Stadio s=new Stadio(nome, capienza, prezzo);
		if(stadi.contains(s))
			throw new StadioEsistentException();
		stadi.add(s);
	}

	/**
	 * Restituisce la lista delle partite
	 * @return lista partite
	 */
	public ArrayList<PartitaProgrammata> getPartite() {
		ArrayList<PartitaProgrammata> partite=new ArrayList<PartitaProgrammata>();
		for(Stadio s:stadi)
			partite.addAll(s.getPartite());
			
		return partite;
	}

	/**
	 * Imposta la capienza di uno stadio
	 * @param stadio nome dello stadio
	 * @param capienza nuova capienza da assegnare allo stadio
	 */
	public void setCapienzaStadio(String stadio, int capienza) {
		getStadio(stadio).setCapienza(capienza);
	}

	/**
	 * Acquisto di un biglietto di cui si è effettuata una prenotazione
	 * @param p prenotazione del biglietto
	 * @param username username del cliente
	 * @throws UtenteInesistenteException lancia l'eccezione se l'utente non esiste
	 */
	public void acquista(Prenotazione p,String username) throws UtenteInesistenteException{
		Utente utente=null;
		for(Utente u:utenti){
			if(u.getUsername().equals(username))
				utente=u;
		}
		if(utente==null) throw new UtenteInesistenteException();		
		
		for(Stadio s: stadi){
			for(PartitaProgrammata pg:s.getPartite()){
				if(p.getPartita().equals(pg)){
					s.acquistaBiglietto(p,utente);
					
				}
			}
		}
	}
	
	/**
	 * Permette l'acquisto di un biglietto di cui non si è effettuata una prenotazione
	 * @param nomeStadio nome dello stadio
	 * @param username username del cliente
	 * @param posto biglietto valido per un determinato posto
	 * @param squadra1 prima squadra della partita
	 * @param squadra2 seconda squadra della partita
	 * @param data data della partita
	 * @throws UtenteInesistenteException lancia l'eccezione se l'utente non esiste
	 */
	public void acquista(String nomeStadio,String username,int posto,String squadra1,String squadra2,GregorianCalendar data) throws UtenteInesistenteException{
		Utente utente=null;
		for(Utente u:utenti)
			if(u.getUsername().equals(username))
				utente=u;
			
		if(utente==null) throw new UtenteInesistenteException();	
		
		getStadio(nomeStadio).acquistaBiglietto(new Prenotazione(username,posto,getStadio(nomeStadio).getPartita(squadra1, squadra2, data)),getUtente(username));
	}

	/**
	 * Cancellazione di una prenotazione
	 * @param p prenotazione da cancellare
	 * @throws PrenotazioneNonEsistenteException lancia l'eccezione se la prenotazione non esiste
	 */
	public void cancellaPrenotazione(Prenotazione p) throws PrenotazioneNonEsistenteException{
		for(Stadio stadio:stadi){
			if(stadio.getPrenotazioni(p.getId()).contains(p)){
				stadio.cancellaPrenotazione(p);
			}
		}
	}

	/**
	 * Restituisce la capienza di uno stadio
	 * @param stadio nome dello stadio
	 * @return capienza dello stadio
	 */
	public int getCapienza(String stadio) {
		return getStadio(stadio).getCapienza();
	}

	/**
	 * Attivazione dello sconto su una partita
	 * @param perc percentuale di sconto
	 * @param p partita su cui effettuare lo sconto
	 */
	public void attivaSconto(int perc, PartitaProgrammata p) {
		for(Stadio s:stadi){
			if(s.getPartite().contains(p)){
				s.attivaSconto(perc, p.getsquadra1(), p.getsquadra2(), p.getData());
			}
		}
	}	
}
	