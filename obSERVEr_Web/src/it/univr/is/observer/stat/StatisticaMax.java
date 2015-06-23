package it.univr.is.observer.stat;

import java.util.List;

public class StatisticaMax implements Statistica {

	@Override
	public int calcola(List<Integer> velocita) {
		int max = Integer.MIN_VALUE;
		for (int v : velocita)
			if (v > max)
				max = v;
		return max;
	}
}
