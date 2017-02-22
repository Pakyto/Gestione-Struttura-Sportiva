package classi;

import java.io.Serializable;
import java.util.GregorianCalendar;

import interfacce.OggettoProgrammato;
import utils.StatoPosto;
/**
 * La prenotazione può essere effettuata per qualsiasi evento poichè usa l'interfaccia OggettoProgrammato
 * @author Ilaria Mosca
 *
 */
public class Prenotazione implements Cloneable,Serializable {
	
	private int posto;
	/**
	 * la partita programmata
	 */
	private OggettoProgrammato partita;
	
	
	/**
	 * l'username dell'utente
	 */
	private String id;
	
	public Prenotazione(String id,int posto,OggettoProgrammato partita){
		this.id=id;
		this.posto=posto;
		this.partita=partita;
		this.partita.setPosto(posto, StatoPosto.Prenotato);
	}
	
	public Prenotazione(String id,int posto,OggettoProgrammato partita,StatoPosto stato){
		this.id=id;
		this.posto=posto;
		this.partita=partita;
		this.partita.setPosto(posto, stato);
	}
	
	public int getPosto() {
		return posto;
	}

	public OggettoProgrammato getPartita() {
		return partita;
	}

	public String getId() {
		return id;
	}

	
	public Object clone(){
		try{
			return (Prenotazione)super.clone();
		}catch(CloneNotSupportedException e){
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
			Prenotazione p = (Prenotazione)s;
			return posto==p.posto && p.getPartita().getTitle().equals(partita.getTitle())&& id==p.id;
		}
	
	public String toString()
	{
		return id+" "+posto+" "+partita.getTitle();
	}



}
