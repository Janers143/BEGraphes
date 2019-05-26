package org.insa.algo.testsperformance;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.AStarAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

public class ExecutionResults {
	private int origine;
	private int destination;
	private float timeExecutionDijkstra;
	private float timeExecutionAStar;
	private int nbNodesDijsktra;
	private int nbNodesAStar;

	public ExecutionResults(String mapName, int pathType, int origine, int destination) {

		this.origine = origine;
		this.destination = destination;
		
		try {
			// Create a graph reader.
			GraphReader reader = new BinaryGraphReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

			// Read the graph.
			Graph graph = reader.read();

			/* Création de l'arcInspector */
			ArcInspector arcInspector;
			/* Si évaluation en temps */
			if (pathType == 0) {
				arcInspector = ArcInspectorFactory.getAllFilters().get(2);
			}
			/* Sinon évaluation en distance */
			else {
				arcInspector = ArcInspectorFactory.getAllFilters().get(0);
			}

			ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspector);

			long tempsDeb;
			long tempsFin;

			/* Calcul du temps d'exécution de Dijkstra */
			DijkstraAlgorithm D = new DijkstraAlgorithm(data);
			tempsDeb = System.nanoTime();
			//tempsDeb = System.currentTimeMillis();
			D.run();
			//tempsFin = System.currentTimeMillis();
			tempsFin = System.nanoTime();
			this.timeExecutionDijkstra = (tempsFin-tempsDeb)/1000000.0f;
			//this.tempsExecutionDijkstra = tempsFin-tempsDeb;
			this.nbNodesDijsktra = D.getNbNodesVisited();

			tempsDeb = 0;
			tempsFin = 0;
			
			/* Calcul du temps d'exécution d'AStar */
			AStarAlgorithm A = new AStarAlgorithm(data);
			//tempsDeb = System.currentTimeMillis();
			tempsDeb = System.nanoTime();
			A.run();
			tempsFin = System.nanoTime();
			//tempsFin = System.currentTimeMillis();
			this.timeExecutionAStar = (tempsFin-tempsDeb)/1000000.0f;
			//this.tempsExecutionAStar = tempsFin-tempsDeb;
			this.nbNodesAStar = A.getNbNodesVisited();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getOrigine() {
		return this.origine;
	}
	
	public int getDestination() {
		return this.destination;
	}
	
	public float getTempsExecutionDijkstra() {

		return this.timeExecutionDijkstra;
	}
	
	public float getTempsExecutionAStar() {
		return this.timeExecutionAStar;
	}
	
	public int getNbNodesVisitedDijkstra() {
		return this.nbNodesDijsktra;
	}
	
	public int getNbNodesVisitedAStar() {
		return this.nbNodesAStar;
	}

}