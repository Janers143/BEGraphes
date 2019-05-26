package org.insa.algo.testsperformance;


import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class TestPerformance {
	private ArrayList<ExecutionResults> listeResultatPerformance;

	public TestPerformance() {
		this.listeResultatPerformance = new ArrayList<ExecutionResults>();
	}


	public void doRun(String fileNameRead, String fileNameWrite, int typeEvaluation) {
		//Lecture lect = new Lecture("/home/katran/Bureau/Maison/Graphes/BE_Graphes/input/haute-garonne.txt");
		//Lecture lect = new Lecture("C:/Users/Utilisateur/Desktop/BE_Graphes/input/haute-garonne.txt");
		
		Read read = new Read(fileNameRead);

		String mapName = read.getMapName();

		Iterator<Integer> origineIter = read.getListeOrigine().iterator();
		Iterator<Integer> destinationIter = read.getListeDestination().iterator();


		while(origineIter.hasNext()) {
			ExecutionResults resultat = new ExecutionResults ("/home/commetud/3MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr", typeEvaluation, origineIter.next(), destinationIter.next());
			//ResultatExecutionAlgos resultat = new ResultatExecutionAlgos(lect.getMapName(), typeEvaluation, origineIter.next(), destinationIter.next());

			this.listeResultatPerformance.add(resultat);
			//System.out.println("Temps execution : Dijkstra = " + resultat.getTempsExecutionDijkstra() + "/ AStar = " + resultat.getTempsExecutionAStar());
			//System.out.println("Nb sommets visités: Dijkstra = " + resultat.getNbSommetsVisitesDijkstra() + " / AStar = " + resultat.getNbSommetsVisitesAStar());
		}

	
		Write write = new Write(fileNameWrite, mapName, this.listeResultatPerformance);
	}


	@Test
	public void testLecture() {
		//Lecture lect = new Lecture("/home/katran/Bureau/Maison/Graphes/BE_Graphes/input/haute-garonne.txt");
		//Lecture lect = new Lecture("C:/Users/Alicia/eclipse-workspace/BE_Graphes/input/haute-garonne.txt");
		//Lecture lect = new Lecture("C:/Users/Utilisateur/Desktop/BE_Graphes/input/haute-garonne.txt");
		Read lect = new Read("/home/jesus/Desktop/BEGraphes/FichiersLect/bretagne_15_25.txt");
		System.out.println("la carte : "+lect.getMapName());
		System.out.println("1ère origine : "+lect.getListeOrigine().get(0));
	}


}