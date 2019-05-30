package org.insa.algo.testsperformance;

import org.junit.Test;

public class AllTests {
	
	/*@Test
	public void testsPerformance1() {
		TestPerformance test1Temps = new TestPerformance();
		test1Temps.doRun("/home/jesus/Desktop/BEGraphes/FichiersLect/bretagne_15_25.txt", "resultat_performance_bretagne_15_25_temps.csv", 0);
	}

	@Test
	public void testsPerformance2() {
		TestPerformance test1Distance = new TestPerformance();
		test1Distance.doRun("/home/jesus/Desktop/BEGraphes/FichiersLect/bretagne_15_25.txt", "resultat_performance_bretagne_15_25_distance.csv", 1);
	}*/
		
	@Test
	public void testsPerformance3() {
		TestPerformance test2Temps = new TestPerformance();
		test2Temps.doRun("/home/jesus/Desktop/BEGraphes/FichiersLect/bretagne_50_100.txt", "resultat_performance_bretagne_50_100_temps.csv", 0);
	}
		
	@Test
	public void testsPerformance4() {
		TestPerformance test2Distance = new TestPerformance();
		test2Distance.doRun("/home/jesus/Desktop/BEGraphes/FichiersLect/bretagne_50_100.txt", "resultat_performance_bretagne_50_100_distance.csv", 1);
	}

	@Test
	public void testsPerformance5() {
		TestPerformance test3Temps = new TestPerformance();
		test3Temps.doRun("/home/jesus/Desktop/BEGraphes/FichiersLect/bretagne_150_170.txt", "resultat_performance_bretagne_150_170_temps.csv", 0);
	}
		
	@Test
	public void testsPerformance6() {
		TestPerformance test3Distance = new TestPerformance();
		test3Distance.doRun("/home/jesus/Desktop/BEGraphes/FichiersLect/bretagne_150_170.txt", "resultat_performance_bretagne_150_170_distance.csv", 1);
	}

}