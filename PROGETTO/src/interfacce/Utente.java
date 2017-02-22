package interfacce;

import classi.Stadio;
import utils.Tipologia;

public interface Utente {

	<T> T getUtente();
	<T> void  accedi(T stadi);
	<T> T getOperazioni();
	String getUsername();
	String getPassword();
	String getTipologia();
	Utente registrati(String username,String password,Tipologia tipo);
}
