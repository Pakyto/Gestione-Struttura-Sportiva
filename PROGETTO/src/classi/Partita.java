package classi;

import java.io.Serializable;
/**
 * La partita è l'oggetto di base da cui viene creata la PArtita Programmata
 * @author Ilaria Mosca
 *
 */
public class Partita implements Serializable{

	private String squadra1;
	private String squadra2;
	
	public Partita(){
	}
	
	public Partita(String s1,String s2){
		squadra1=s1;
		squadra2=s2;
	}

	public String getsquadra1() {
		return squadra1;
	}

	public void setSquadra1(String squadra1) {
		this.squadra1 = squadra1;
	}

	public String getSquadra2() {
		return squadra2;
	}

	public void setsquadra2(String squadra2) {
		this.squadra2 = squadra2;
	}
	
	public String toString(){
		return squadra1+" "+squadra2;
	}
	

	
	public boolean equals(Object s){
		if(s==null){
			return false;
		}
		if(getClass()!=s.getClass()){
			return false;
		}
		Partita p = (Partita)s;
		return (squadra1.equals(p.squadra1) && squadra2.equals(p.squadra2));
	}
	
	public Partita clone(){
		try{
			return (Partita)super.clone();
		}catch(CloneNotSupportedException e){
			return null;
		}
	}

}
