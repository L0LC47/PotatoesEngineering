package it.univr.is.observer.stat;

import java.util.List;

public class StatisticaMedia implements Statistica {

	@Override
	public int calcola(List<Integer> velocita) {
		int med = 0;
		for (int v : velocita)
			med += v;
		return med / velocita.size();
	}
}
