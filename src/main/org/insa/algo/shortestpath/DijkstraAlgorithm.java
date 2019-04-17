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
        Label Current;
        Node currentSuccessor;
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
        		//Si ce successeur n'est pas marque
        		// TODO;
        	}
        	
        }
        
        return solution;
    }
}
