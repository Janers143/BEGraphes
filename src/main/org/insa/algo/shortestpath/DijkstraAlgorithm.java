package org.insa.algo.shortestpath;

import org.insa.graph.*;
import org.insa.algo.utils.*;
import java.util.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Graph graphUsed = data.getGraph();
        int graphSize = graphUsed.size();
        
        Label labels[] = new Label[graphSize];
        BinaryHeap<Label> Tas = new BinaryHeap<Label>();
        
        Node Origin = data.getOrigin();
        Node Destination = data.getDestination();
        Node currentSuccessor;
        // Change à true si la valeur du cout et le pere a changé
        Boolean hasChanged = false;
        
        Label Current;
        List<Arc> Successors = new ArrayList<Arc>();
        
        
        // Initialisation premier label
        labels[Origin.getId()] = new Label(Origin, 0.0, null, null);
        labels[Destination.getId()] = new Label(Destination, Double.POSITIVE_INFINITY, null, null);
        Tas.insert(labels[Origin.getId()]);
        
        // Boucle principale
        while (!labels[Destination.getId()].isMarked()) {
        	// Le noeud courant est celui qui a le cout le plus petit
        	Current = Tas.findMin();
        	Current.marquerNode();
        	Successors = Current.getCourant().getSuccessors();
        	for (Arc arcCurrent : Successors) {
        		currentSuccessor = arcCurrent.getDestination();
        		// Si ce successeur n'a pas de label, on la crée
        		if (labels[currentSuccessor.getId()] == null) {
        			labels[currentSuccessor.getId()] = new Label(currentSuccessor, Double.POSITIVE_INFINITY, 
        														 Current.getCourant(), arcCurrent);
        			Tas.insert(labels[currentSuccessor.getId()]);
        		}
        		// Si ce successeur n'est pas marque
        		if (!labels[currentSuccessor.getId()].isMarked()) {
        			if (Current.getCost() + arcCurrent.getLength() > labels[currentSuccessor.getId()].getCost()) {
        				
        			}
        		}
        	}
        	
        }
        
        return solution;
    }
}
