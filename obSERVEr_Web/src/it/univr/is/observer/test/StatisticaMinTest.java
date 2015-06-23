package it.univr.is.observer.test;

import static org.junit.Assert.assertEquals;
import it.univr.is.observer.stat.Statistica;
import it.univr.is.observer.stat.StatisticaMin;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StatisticaMinTest {

	@Test
	public void testCalcola() {
		Statistica statistica = new StatisticaMin();
		List<Integer> velocita = new ArrayList<>();
		
		velocita.add(100);
		velocita.add(5);
		velocita.add(50);
		velocita.add(42);
		velocita.add(7);
		
		int res = statistica.calcola(velocita);
		
		assertEquals(5,res);
	}

}
