package classi;

import java.io.Serializable;
/**
 * Rappresenta la contabilità di uno stadio
 * @author Pasquale Settembre
 */
public class Cassa implements Cloneable,Serializable{
	/**
	 * Rappresenta l'incasso di uno stadio
	 * @see Cassa#aggiornaIncasso(float)
	 */
	private float incasso;
	
	/**
	 * Viene creata la cassa dello stadio, con l'incasso inizialmente a 0
	 */
	public Cassa(){
		incasso=0;
	}
	
	/**
	 * Restituisce l'incasso dello stadio
	 * @return incassio stadio
	 */
	public float getIncasso()
	{return incasso;}
	
	/**
	 * Calcola il prezzo del biglietto in base al prezzo e allo sconto
	 * @param prezzo rappresenta il prezzo per una partita
	 * @param sconto rappresenta lo sconto su una partita
	 * @return il prezzo con l'applicazione dello sconto
	 */
	public float calcolaPrezzo(float prezzo,int sconto)
	{
		return prezzo-((prezzo*sconto)/100);
	}
	
	/**
	 * Permette di calcolare lo sconto per il prezzo del biglietto
	 * @param sconto1 rappresenta lo sconto del cliente che varia in base alla tipologia
	 * @param sconto2 rappresenta lo sconto della partita
	 * @return restituisce uno dei due sconti, se quello dell'cliente è maggiore restituisce
	 * quello del cliente, altrimenti lo sconto della partita
	 */
	public int calcolaSconto(int sconto1,int sconto2){
		return (sconto1>sconto2)?sconto1:sconto2;
	}
	
	/**
	 * Permette di aggiornare l'incasso del botteghino
	 * @param i rappresenta quanto aumentare l'incasso
	 */
	public void aggiornaIncasso(float i)
	{
		incasso+=i;
	}


	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cassa other = (Cassa) obj;
		
		if (Float.floatToIntBits(incasso) != Float.floatToIntBits(other.incasso))
			return false;
		
		return true;
	}
	
}
