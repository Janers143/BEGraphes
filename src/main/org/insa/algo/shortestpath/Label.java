package org.insa.algo.shortestpath;

import org.insa.graph.*;

public class Label implements Comparable<Label>{
	
	private Node courant;
	
	private boolean marque;
	
	private double cout;
	
	private Node pere;
	
	private Arc arcPere;
	
	/** Constructeur de la classe label */
	public Label(Node sommetCourant, double cost, Node father, Arc arcFather) {
		this.courant = sommetCourant;
		this.marque = false;
		this.cout = cost;
		this.pere = father;
		this.arcPere = arcFather;
	}
	
	/** Retourne le cout pour arriver à ce noeud */
	public double getCost() {
		return this.cout;
	}
	
	public Node getCourant() {
		return this.courant;
	}
	
	public Node getPere() {
		return this.pere;
	}
	
	public Arc getArcPere() {
		return this.arcPere;
	}
	
	public void marquerNode() {
		this.marque = true;
	}
	
	public void setPere(Node father, Arc arcFather) {
		this.pere = father;
		this.arcPere = arcFather;
	}
	
	public void setCost(double cost) {
		this.cout = cost;
	}
	
	public boolean isMarked() {
		return this.marque;
	}
	
	public double getTotalCost() {
		return this.getCost();
	}
	
	// On redéfinit la methode compareTo pour pouvoir comparer deux labels
	// On les compare par leur cout
	public int compareTo(Label other) {
		return Double.compare(this.getTotalCost(), other.getTotalCost());
	}
}