package org.insa.algo.shortestpath;

import org.insa.graph.*;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.*;
import java.util.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        Graph graphUsed = data.getGraph();
        int graphSize = graphUsed.size();
        
        Label labels[] = new Label[graphSize];
        // On initialise le tableau
        for (int i = 0; i < graphSize; i++) {
        	labels[i] = new Label(graphUsed.get(i), Double.POSITIVE_INFINITY, null, null);
        }
        
        BinaryHeap<Label> Tas = new BinaryHeap<Label>();
        
        Node Origin = data.getOrigin();
        Node Destination = data.getDestination();
        Node currentSuccessor;
        // Change à true si la valeur du cout et le pere a changé
        Boolean hasChanged = false;
        
        Label Current;
        List<Arc> Successors = new ArrayList<Arc>();
        
        
        // Initialisation premier label
        labels[Origin.getId()].setCost(0.0);
        Tas.insert(labels[Origin.getId()]);
        
        System.out.println("Avant la boucle principale");
        
        // Boucle principale
        while (!labels[Destination.getId()].isMarked() && !Tas.isEmpty()) {
        	// Le noeud courant est celui qui a le cout le plus petit
        	Current = Tas.findMin();
        	Tas.deleteMin();
        	Current.marquerNode();
        	Successors = Current.getCourant().getSuccessors();
        	for (Arc arcCurrent : Successors) {
        		hasChanged = false;
        		
        		currentSuccessor = arcCurrent.getDestination();
        		// Si ce successeur n'a pas de label, on la crée
        		/*if (labels[currentSuccessor.getId()] == null) {
        			labels[currentSuccessor.getId()] = new Label(currentSuccessor, Double.POSITIVE_INFINITY, 
        														 Current.getCourant(), arcCurrent);
        			Tas.insert(labels[currentSuccessor.getId()]);
        		}*/
        		// Si ce successeur n'est pas marque
        		if (!labels[currentSuccessor.getId()].isMarked()) {
        			double oldDistance = labels[currentSuccessor.getId()].getCost();
        			double newDistance = Current.getCost() + arcCurrent.getLength();
        			if (newDistance < oldDistance) {
        				labels[currentSuccessor.getId()].setCost(newDistance);
        				hasChanged = true;
        			}
        			if (Double.isInfinite(oldDistance) && Double.isFinite(newDistance)) {
                        notifyNodeReached(arcCurrent.getDestination());
                    }
        			
        			if (hasChanged) {
        				try {
        					Tas.remove(labels[currentSuccessor.getId()]);
        				}
        				catch (ElementNotFoundException e) {
        					System.out.println(e.getElement());
        				}
        				Tas.insert(labels[currentSuccessor.getId()]);
        				labels[currentSuccessor.getId()].setPere(Current.getCourant(), arcCurrent);
        			}
        		}
        	}
        }
        
        System.out.println("Après la boucle principale");
        
        /*Creer le Shortest Path à partir de labels*/
        
        ArrayList<Arc> arcsPath = new ArrayList<>();
        ShortestPathSolution solution = null;
        Arc currentArcPath; 
        if (labels[Destination.getId()].isMarked()) {
        	// The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());
            
            // On crée une liste d'arcs
            currentArcPath = labels[Destination.getId()].getArcPere();
            while (currentArcPath != null) {
            	arcsPath.add(currentArcPath);
            	currentArcPath = labels[currentArcPath.getOrigin().getId()].getArcPere();
            }
            
            // On l'inverse
            Collections.reverse(arcsPath);
            
            // On crée la solution finale
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graphUsed, arcsPath));
        }
        else {
        	solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        
        return solution;
    }
}
