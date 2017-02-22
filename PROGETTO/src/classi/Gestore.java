package classi;

import interfacce.OperazioniGestore;
import interfacce.Utente;
import utils.Tipologia;
/**
 * Classe Gestore che utilizza le operazioni del gestore
 * @author Ilaria Mosca
 */
public class Gestore implements Utente{

private	String username;
private	String password;
private	Tipologia tipo;
/**
 * interfaccia usata dal gestore che racchiude tutte le operazioni a lui consentite
 * @see OperazioniGestore
 */
private	OperazioniGestore operazioni;
	
	public String getUsername() {
	
		return username;
	}

	
	public String getPassword() {
		
		return password;
	}

	
	public Utente registrati(String username, String password, Tipologia tipo) {
	this.username=username;
	this.password=password;
	this.tipo=tipo;
	
	return this;
		
	}



	@Override
	public <T> T getUtente() {
		
		return (T) this;
	}


	@Override
	public <T> void accedi(T stadi) {
		operazioni=(OperazioniGestore) stadi;
		
	}


	@Override
	public String getTipologia() {
		
		return tipo.toString();
	}
	
	public <T> T getOperazioni(){
		return (T)operazioni;
	}

	
}
