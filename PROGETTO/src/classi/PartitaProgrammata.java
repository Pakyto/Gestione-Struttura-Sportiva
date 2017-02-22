package classi;
import java.io.Serializable;
import java.util.GregorianCalendar;

import interfacce.OggettoProgrammato;
import interfacce.Sconto;
import utils.StatoPosto;

/**
 * La partita programmata è un'estensione di partita alla quale aggiunge una data di programmazione e un settore
 * @author Ilaria Mosca
 *
 */
public class PartitaProgrammata extends Partita implements OggettoProgrammato, Sconto ,Cloneable,Serializable {
	
	private static int i=0;
	private GregorianCalendar data;
	private int sconto, id;
	/**
	 * L'attributo settore specifica il numero e lo stato dei posti per la partita
	 */
	private Settore settore;
	
	/**
	 * questo costruttore è usato per la serializzazione
	 */
	public PartitaProgrammata(){
	super();
	}
	

	public PartitaProgrammata(String s1,String s2,GregorianCalendar data,int numPosti) {
		
		super(s1, s2);
		this.data=(GregorianCalendar)data.clone();
		settore=new Settore(numPosti);
		id=i++;
	
	}
	
	public String getsquadra1(){
		return super.getsquadra1();
	}
	
	public String getsquadra2(){
		return super.getSquadra2();
	}
	
	@Override
	public void setSconto(int s) {
		sconto=s;
		
	}

	@Override
	public int getSconto() {
	
		return sconto;
	}

	@Override
	public GregorianCalendar getData() {
		
		return (GregorianCalendar)data.clone();
	}

	@Override
	public int getId() {
	
	return id;
	}

	/**
	 * Metodo dell'interfaccia OggettoProgrammato 
	 * @see OggettoProgrammato
	 * @return le informazioni della partita programmata
	 */
	@Override
	public String getTitle() {
		
		return toString();
	}

	/**
	 * Metodo dell'interfaccia OggettoProgrammato grazie al quale cui sarà possibile acquistare o prenotare un biglietto
	 * @return la partitaProgrammata 
	 */
	@Override
	public PartitaProgrammata getObjProgrammato() {
		
		return (PartitaProgrammata) clone();
		
	}
	
	public String toString(){
		return 
			super.toString()+" "+
			   data.get(GregorianCalendar.DAY_OF_MONTH)+"/"+data.get(GregorianCalendar.MONTH)+"/"+data.get(GregorianCalendar.YEAR)
			   +" "+data.get(GregorianCalendar.HOUR_OF_DAY)+":"+data.get(GregorianCalendar.MINUTE)+" "+settore.getPostiDisponibili();
	           
	}

	@Override
	public int getPostiDisponibili() {
		
		return settore.getPostiDisponibili();
	}

	@Override
	public int getPosto(int posto) {
		
		return posto;
	}

	@Override
	public void setPosto(int posto, StatoPosto s) {
		settore.setPosto(posto, s);
		
	}

	@Override
	public StatoPosto getStatoPosto(int posto) {
	
		return settore.getPosto(posto);
	}
	
	public boolean equals(Object d){
		if(!super.equals(d))
			return false;
		PartitaProgrammata p=(PartitaProgrammata)d;
		return  (isEqualsDate(p.getData())) ;
	}
	
	public PartitaProgrammata clone(){
		PartitaProgrammata pa=(PartitaProgrammata)super.clone();
		pa.data=data;
		pa.sconto=sconto;
		pa.id=id;
		return pa;
	}

	/**
	 * Controlla se le date sono uguali, utilizzato in equals
	 * @see PartitaProgrammata#equals(Object)
	 * @param data2
	 * @return
	 */
	
	public boolean isEqualsDate(GregorianCalendar data2){
		
		if(data.get(GregorianCalendar.DAY_OF_MONTH)==data2.get(GregorianCalendar.DAY_OF_MONTH))
			if(data.get(GregorianCalendar.MONTH)==data2.get(GregorianCalendar.MONTH))
				if(data.get(GregorianCalendar.YEAR)==data2.get(GregorianCalendar.YEAR))
					if(data.get(GregorianCalendar.HOUR)==data2.get(GregorianCalendar.HOUR))
						if(data.get(GregorianCalendar.MINUTE)==data2.get(GregorianCalendar.MINUTE))
							return true;
		
	return false;
	}
}
