package classi;
import java.io.Serializable;

import interfacce.OperazioniCliente;
import interfacce.Sconto;
import interfacce.Utente;
import utils.Tipologia;
/**
 * Cliente della struttura sportiva che può essere di diverse tipologie
 * @author Pasquale
 */
public class Cliente implements Utente,Sconto,Serializable,Cloneable {
	/**
	 * username di un cliente
	 * @see Cliente#registrati(String,String, Tipologia)
	 */
	private String username;
	/**
	 * password di un cliente
	 * @see Cliente#registrati(String, String, Tipologia)
	 */
	private String password;
	/**
	 * tipologia di cliente
	 * Cliente#getTipologia()
	 */
	private Tipologia tipo;
	/**
	 * sconto del cliente inizialmente a 0
	 * @see Cliente#setSconto(int)
	 */
	private int sconto=0;
	/**
	 * operazioni permesse dal cliente
	 * @see Cliente#getOperazioni()
	 */
	private OperazioniCliente operazioni;

	
	/**
	 * @return restituisce l'username del cliente
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @return restituisce la password del cliente
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Permette la registrazione di un nuovo utente
	 * @param username rappresenta l'username da utilizzare
	 * @param password rappresenta la password scelta dal cliente
	 * @param tipo rappresenta la tipologia di cliente
	 */
	public Utente registrati(String username, String password, Tipologia tipo) {
		this.username=username;
		this.password=password;
		this.tipo=tipo;
		return this;
	}

	/**
	 * Permette l'accesso di un cliente alla struttura
	 * @param stadi il cliente effettua operazioni su questo oggetto
	 */
	public <T> void accedi(T stadi){
		operazioni=(OperazioniCliente) stadi;
	}
	
	/**
	 * Permette di restituire un utente
	 * @return ritorna l'utente stesso
	 */
	public <T> T getUtente() {
		return (T) this;
	}

	/**
	 * Permette di impostare lo sconto su un cliente
	 * @param sconto rappresenta la percentuale di sconto da applicare
	 */
	public void setSconto(int sconto) {
		this.sconto=sconto;
		
	}

	/**
	 * @return restituisce lo sconto del cliente
	 */
	public int getSconto() {
	
		return sconto;
	}

	/**
	 * @return restituisce la tipologia del cliente
	 */
	public String getTipologia() {
		return tipo.toString();
	}

	/**
	 * @return restituisce le operazioni permesse dal cliente
	 */
	public <T> T getOperazioni(){
		return (T)operazioni;
	}

}
