package org.insa.algo.shortestpath;

import org.insa.graph.*;

public class LabelStar extends Label {
	
	private Node Destination;
	
	public LabelStar(Node sommetCourant, double cost, Node father, Arc arcFather, Node destination) {
		super(sommetCourant, cost, father, arcFather);
		this.Destination = destination;
	}
	
	public double estimationDestination() {
		Point pointDestination = this.Destination.getPoint();
		Point pointCourant = this.getCourant().getPoint();
		return pointCourant.distanceTo(pointDestination);
	}
	
	public double getTotalCost() {
		this.estimationDestination();
		return this.getCost() + this.estimationDestination();
	}
}