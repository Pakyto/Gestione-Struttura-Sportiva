package classi;

import java.io.Serializable;
import java.util.ArrayList;

import utils.StatoPosto;

/**
 * La classe settore specifica il numero di posti per ogni partita e lo stato di ogni posto
 * @author Ilaria Mosca
 *
 */
public class Settore implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * inizialmente tutti i posti sono liberi e la capienza massima fa riferimento alla capienza dello stadio
	 */
	private ArrayList<StatoPosto> posti;
	private int capienzaSettore;
	private static int capienzaMax;


public Settore(int numPosti)
{
	posti=new ArrayList<StatoPosto>();
	capienzaMax=numPosti;
	capienzaSettore=capienzaMax;
	//all'inizio tutti i posti sono liberi
	for(int i=0;i<numPosti;i++)
	{
		posti.add(StatoPosto.Libero);
		
	}
}

public StatoPosto getPosto(int i)
{
	return posti.get(i);
}


public void setPosto(int i,StatoPosto s)
{
	posti.set(i, s);
	
}

public int getPostiDisponibili(){
	int postiDisponibili=0;
	for(StatoPosto p:posti)
	{
		if(p.equals(StatoPosto.Libero))
		postiDisponibili++;
	}
		return postiDisponibili;
		
}

	public static void setCapienzaMax(int capienza){
		capienzaMax=capienza;
	}


	public Object clone(){
		try{
			return super.clone();
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
	Settore set = (Settore)s;
	return posti.equals(set.posti) && capienzaSettore==set.capienzaSettore && capienzaMax==set.capienzaMax;
	}


}
