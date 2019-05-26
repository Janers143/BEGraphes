package org.insa.algo.shortestpath;

import java.util.*;
import org.insa.graph.*;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.*;

public class AStarAlgorithm extends ShortestPathAlgorithm {
	
	private int nbNodesVisited;
	
    public AStarAlgorithm(ShortestPathData data) {
        super(data);
        this.nbNodesVisited = 0;
    }
   
    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        Graph graphUsed = data.getGraph();
        int graphSize = graphUsed.size();
        
        LabelStar labels[] = new LabelStar[graphSize];
        // On initialise le tableau
        for (int i = 0; i < graphSize; i++) {
        	labels[i] = new LabelStar(graphUsed.get(i), Double.POSITIVE_INFINITY, null, null, data);
        }
        
        BinaryHeap<Label> Tas = new BinaryHeap<Label>();
        
        Node Origin = data.getOrigin();
        Node Destination = data.getDestination();
        Node currentSuccessor;
        // Change à true si la valeur du cout et le pere a changé
        Boolean hasChanged = false;
        
        Label Current;
        List<Arc> Successors = new ArrayList<Arc>();
        
        
        // Initialisation premier LabelStar
        labels[Origin.getId()].setCost(0.0);
        Tas.insert(labels[Origin.getId()]);
        
        // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());
        
        // Boucle principale
        while (!labels[Destination.getId()].isMarked() && !Tas.isEmpty()) {
        	//loopCounter++;
        	// Le noeud courant est celui qui a le cout le plus petit
        	Current = Tas.findMin();
        	Tas.deleteMin();
        	Current.marquerNode();
        	notifyNodeMarked(Current.getCourant());
        	
        	// Affichage de la distance du noeud marqué dans un fichier texte (verification
        	// du fonctionnement correct de l'algo)
        	
        	// System.out.println("Noeud marqué : " + Current.getCourant() + " | Distance de l'origine : " + Current.getCost());
        	
        	// System.out.println("Taille du tas : " + Tas.size());
        	
        	Successors = Current.getCourant().getSuccessors();
        	
        	for (Arc arcCurrent : Successors) {  		
        		hasChanged = false;
        		
        		currentSuccessor = arcCurrent.getDestination();
        		// Si ce successeur n'est pas marque
        		if (!labels[currentSuccessor.getId()].isMarked()) {
        			
        			/*double oldDistance = labels[currentSuccessor.getId()].getCost();
        			double newDistance = Current.getCost() + arcCurrent.getLength();*/
        			
        			double oldDistance = labels[currentSuccessor.getId()].getTotalCost();
        			double newDistance;
        			
        			if (Double.isInfinite(labels[currentSuccessor.getId()].getCost()))
        				newDistance = Current.getCost() + data.getCost(arcCurrent);// + (labels[currentSuccessor.getId()].getTotalCost() - labels[currentSuccessor.getId()].getCost());
        			else
        				newDistance = Current.getCost() + data.getCost(arcCurrent) + (labels[currentSuccessor.getId()].getTotalCost() - labels[currentSuccessor.getId()].getCost());
        			
        			if (newDistance < oldDistance) {
        				labels[currentSuccessor.getId()].setCost(Current.getCost() + data.getCost(arcCurrent));
        				hasChanged = true;
        			}
        			if (Double.isInfinite(oldDistance) && Double.isFinite(newDistance)) {
                        notifyNodeReached(arcCurrent.getDestination());
                        this.nbNodesVisited++;
                    }
        			
        			if (hasChanged) {
        				try {
        					Tas.remove(labels[currentSuccessor.getId()]);
        				}
        				catch (ElementNotFoundException e) {
        					// System.out.println(e.getElement());
        				}
        				Tas.insert(labels[currentSuccessor.getId()]);
        				labels[currentSuccessor.getId()].setPere(Current.getCourant(), arcCurrent);
        			}
        		}
        	}
        }
        
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
        
        // System.out.println("Nombre total d'arcs du path : " + solution.getPath().getArcs().size());
        
        // System.out.println("Nombre total d'iterations : " + loopCounter);
        
        return solution;
    }
    
    public int getNbNodesVisited() {
    	return this.nbNodesVisited;
    }
}
