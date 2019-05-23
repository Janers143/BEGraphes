package org.insa.algo.shortestpath;

import org.insa.algo.AbstractInputData;
import org.insa.graph.*;

public class LabelStar extends Label {
	
	private Node Destination;
	
	private double coutEstimation;
	
	public LabelStar(Node sommetCourant, double cost, Node father, Arc arcFather, ShortestPathData data) {
		super(sommetCourant, cost, father, arcFather);
		this.Destination = data.getDestination();
		if (data.getMode() == AbstractInputData.Mode.LENGTH) {
			// Si on cherche le plus court chemin,
			// l'heuristique se fait sur une distance :
			// la distance du point courant a la destination
			this.coutEstimation = Point.distance(sommetCourant.getPoint(), this.Destination.getPoint());
		}
		else if (data.getMode() == AbstractInputData.Mode.TIME) {
			// Si on cherche le chemin le plus rapide,
			// l'heuristique se fait sur un temps :
			// le temps que l'on met à parcourir la
			// distance du point courant a la destination
			// a la vitesse maximale autorisee
			int vit = Math.max(data.getMaximumSpeed(), data.getGraph().getGraphInformation().getMaximumSpeed());
			this.coutEstimation = Point.distance(sommetCourant.getPoint(), this.Destination.getPoint())/(vit*1000.0/3600.0);
		}
	}
	
	public double getTotalCost() {
		//this.estimationDestination();
		return this.getCost() + this.coutEstimation;
	}
}