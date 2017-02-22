package classi;

import java.io.Serializable;

import interfacce.OggettoProgrammato;
import utils.StatoPosto;

/**
 * Classe che permette l'utilizzo del biglietto per ogni singola partita
 * @author Pasquale Settembre
 *
 */

public final class Biglietto extends Prenotazione implements Cloneable,Serializable{
	/**Attributo prezzo che rappresenta il prezzo riportato su ogni biglietto
	 * @see Biglietto#getPrezzo()
	 */
	private float prezzo;
	
	/**
	 * Creazione dell'oggetto Biglietto
	 * @param id   rappresenta l'username del cliente
	 * @param posto   rappresenta il posto per il biglietto
	 * @param partita  biglietto valido per una determinata partita
	 * @param prezzo   prezzo del biglietto
	 */
	public Biglietto(String id, int posto, OggettoProgrammato partita,float prezzo) {
		super(id, posto, partita,StatoPosto.Acquistato);
		this.prezzo=prezzo;
	}

	/**
	 * Metodo che ritorna il prezzo di un biglietto
	 * @return prezzo biglietto
	 */
	public float getPrezzo() {
		return prezzo;
	}

	
	public Object clone(){
		Biglietto b;
		b = (Biglietto)super.clone();
		b.prezzo=prezzo;
		return b;
		
	
	}
	
	public boolean equals(Object s){
		if(!super.equals(s)){
			return false;
		}
		Biglietto b=(Biglietto)s;
		return prezzo==b.prezzo;
	}

public String toString(){
	return super.toString()+" "+prezzo;
	
}
	
}
