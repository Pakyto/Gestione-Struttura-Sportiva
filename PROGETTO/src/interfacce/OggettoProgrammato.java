package interfacce;
import java.io.Serializable;
import java.util.GregorianCalendar;

import utils.StatoPosto;


public interface OggettoProgrammato {

	GregorianCalendar getData();
	int getId();
	String getTitle();
	<T extends Sconto> T getObjProgrammato();
	int getPostiDisponibili();
	int getPosto(int posto);
	void setPosto(int posto,StatoPosto s);
	StatoPosto getStatoPosto(int posto);
}
