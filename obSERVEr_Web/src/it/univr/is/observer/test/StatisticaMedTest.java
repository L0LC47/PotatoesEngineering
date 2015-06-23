package it.univr.is.observer.test;

import static org.junit.Assert.assertEquals;
import it.univr.is.observer.stat.Statistica;
import it.univr.is.observer.stat.StatisticaMedia;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StatisticaMedTest {

	@Test
	public void testCalcola() {
		Statistica statistica = new StatisticaMedia();
		List<Integer> velocita = new ArrayList<>();
		
		velocita.add(100);
		velocita.add(50);
		velocita.add(100);
		velocita.add(50);
		velocita.add(100);
		velocita.add(50);
		
		int res = statistica.calcola(velocita);
		
		assertEquals(75,res);
	}

}
