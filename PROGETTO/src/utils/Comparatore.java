package utils;

import java.awt.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import classi.PartitaProgrammata;
import classi.Stadio;

public class Comparatore {

public static Object getComparator(){
	return new LexicographicComparator();
}
	
public static class LexicographicComparator implements Comparator<PartitaProgrammata> {
    @Override
    public int compare(PartitaProgrammata a, PartitaProgrammata b) {
        return a.getsquadra1().compareToIgnoreCase(b.getsquadra1());
    }
}

public static class DateComparator implements Comparator<PartitaProgrammata> {
    @Override
    public int compare(PartitaProgrammata a, PartitaProgrammata b) {
        return a.getData().compareTo(b.getData());
    }
}

public static class StadioComparator implements Comparator<Stadio> {
    @Override
    public int compare(Stadio a, Stadio b) {
        return a.getNome().compareToIgnoreCase(b.getNome());
    }
}
}


