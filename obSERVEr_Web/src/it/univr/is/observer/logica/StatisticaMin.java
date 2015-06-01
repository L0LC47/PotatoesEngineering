package it.univr.is.observer.logica;

import java.util.List;

public class StatisticaMin implements Statistica {

	@Override
	public int calcola(List<Integer> velocita) {
		int min = Integer.MAX_VALUE;
		for (int v : velocita)
			if(v<min)
				min = v;		
		return min;
	}
}
