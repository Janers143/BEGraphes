package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

import org.junit.Test;

public class DijkstraTestMap {
	
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
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
			ShortestPathSolution solution = D.run();
			ShortestPathSolution attendu = B.run();
			
			if (solution.getPath() == null) {
				assertEquals(attendu.getPath(), solution.getPath());
				System.out.println("PAS DE SOLUTION TROUVEE (infini)");
			}
			else {
				// On a trouve un chemin
			}
		}
	}
}