package org.insa.algo.shortestpath;

import org.insa.graph.*;

public class Label {
	
	private Node courant;
	
	private boolean marque;
	
	private double cout;
	
	private Node pere;
	
	private Arc arcPere;
	
	/** Constructeur de la classe label */
	public Label(Node sommetCourant, boolean connu, double cost, Node father, Arc arcFather) {
		this.courant = sommetCourant;
		this.marque = connu;
		this.cout = cost;
		this.pere = father;
		this.arcPere = arcFather;
	}
	
	/** Retourne le cout pour arriver à ce noeud */
	public double getCost() {
		return this.cout;
	}
	
}