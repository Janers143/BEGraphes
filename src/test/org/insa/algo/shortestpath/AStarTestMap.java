package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.BellmanFordAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

import org.junit.Test;

public class AStarTestMap {
	
	/** Fonction permettant de tester l'algo de dijkstra avec oracle :
	 *  on prendra la solution obtenue par Bellman-Ford comme oracle.
	 *  Il y a deux types d'ávaluation : 
	 *  	- ShortestPath si typePath = 0
	 *  	- FastestPath si typePath = 1
	 */
	@Test
	public void testOnMap(String mapPath, int typePath, int origine, int destination) throws Exception {
		// On crée un lecteur de graphes et on lit ce graphe
		GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapPath))));
		Graph graph = reader.read();
		
		// On choisit un arcInspector en fonction de la valeur de typePath
		ArcInspector arcInspectorUsed;
		if (typePath == 0) {
			// On prend l'arcInspector 0 pour le shortestpath
			arcInspectorUsed = ArcInspectorFactory.getAllFilters().get(0);
			System.out.println("Recherche du ShortestPath");
		}
		else {
			// On prend l'arcInspector 2 pour le fastestpath
			arcInspectorUsed = ArcInspectorFactory.getAllFilters().get(2);
			System.out.println("Recherche du FastestPath");
		}
		
		System.out.println("Origine : " + origine);
		System.out.println("Destination : " + destination);
		
		if (origine == destination) {
			System.out.println("L'origine et la destination sont identiques");
			System.out.println("Cout de la solution : 0");
		}
		else {
			ShortestPathData data = new ShortestPathData(graph, graph.get(origine),
														 graph.get(destination), arcInspectorUsed);
			
			// On trouve les chemins à l'aide de Bellman-Ford et de Dijkstra
			BellmanFordAlgorithm B = new BellmanFordAlgorithm(data);
			AStarAlgorithm A = new AStarAlgorithm(data);
			ShortestPathSolution solution = A.run();
			ShortestPathSolution attendu = B.run();
			
			if (solution.getPath() == null) {
				assertEquals(attendu.getPath(), solution.getPath());
				System.out.println("PAS DE SOLUTION TROUVEE (infini)");
			}
			else {
				// On a trouve un chemin
				double coutSolutionAttendu;
				double coutSolutionObtenu;
				if (typePath == 0) {
					// Le cout correspond à la longueur du path
					coutSolutionObtenu  = solution.getPath().getLength();
					coutSolutionAttendu = attendu.getPath().getLength();
				}
				else {
					// Le cout correspond au temps de parcours du path
					coutSolutionObtenu  = solution.getPath().getMinimumTravelTime();
					coutSolutionAttendu = attendu.getPath().getMinimumTravelTime();
				}
				assertEquals(coutSolutionObtenu, coutSolutionAttendu, 1e-6);
				System.out.println("Cout de la solution : " + coutSolutionObtenu);
			}
			System.out.println();
		}
	}
}