package it.univr.is.observer.logica;

import java.util.List;

public class StatisticaMediaMovimento implements Statistica {

	@Override
	public int calcola(List<Integer> velocita) {
		int med = 0, count = 0;
		for (int v : velocita)
			if (v != 0) {
				med += v;
				++count;
			}
		if (count == 0)
			return 0;
		return med / count;
	}
}
