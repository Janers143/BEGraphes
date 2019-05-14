package org.insa.algo.shortestpath;

import org.insa.graph.*;

public class LabelStar extends Label{
	
	private double coutEstimeDestination;
	
	public LabelStar(Node sommetCourant, double cost, Node father, Arc arcFather) {
		super(sommetCourant, cost, father, arcFather);
		coutEstimeDestination = 0.0;
	}
	
	public void estimationDestination(Node Destination) {
		Point pointDestination = Destination.getPoint();
		Point pointCourant = this.getCourant().getPoint();
		this.coutEstimeDestination = pointCourant.distanceTo(pointDestination);
	}
	
}