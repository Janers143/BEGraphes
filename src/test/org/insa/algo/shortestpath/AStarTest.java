package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.ArcInspector;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.GraphStatistics;
import org.insa.graph.Node;
import org.insa.graph.Path;
import org.insa.graph.Point;
import org.insa.graph.RoadInformation;
import org.insa.graph.RoadInformation.RoadType;
import org.junit.BeforeClass;
import org.junit.Test;

public class AStarTest{

	// Small graph use for tests
    private static Graph graph;
    
    // List of nodes
    private static Node[] nodes;
    
    // List of arcs in the graph, a2b is the arc from node A (0) to B (1).
    @SuppressWarnings("unused")
    private static Arc a2b, a2d, a2e, a2i, b2a, b2c, c2d, c2e, d2c, e2d, e2i, f2h, g2f, h2g, i2d, i2e;
    
    // 
    private static GraphStatistics graphstats;
    
    @BeforeClass
    public static void initAll() throws IOException{
    	
    	// 3 types of road : 1000 km/h, 100km/h and 50km/h
    	RoadInformation speed1000 = new RoadInformation(RoadType.MOTORWAY, null, true, 1000, "");
    	RoadInformation speed100  = new RoadInformation(RoadType.MOTORWAY, null, true,  100, "");
    	RoadInformation speed50   = new RoadInformation(RoadType.MOTORWAY, null, true,   50, "");
    	
    	// Create all the nodes
    	nodes = new Node[9];
    	nodes[0] = new Node(0, new Point(5,6));
    	nodes[1] = new Node(1, new Point(8,4));
    	nodes[2] = new Node(2, new Point(8,2));
    	nodes[3] = new Node(3, new Point(5,-2));
    	nodes[4] = new Node(4, new Point(5,3));
    	nodes[5] = new Node(5, new Point(-1,5));
    	nodes[6] = new Node(6, new Point(-3,2));
    	nodes[7] = new Node(7, new Point(-1,0));
    	nodes[8] = new Node(8, new Point(2,3));
    	
    	// Create all the arcs
    	a2b = Node.linkNodes(nodes[0], nodes[1], 1200690, speed100 , null);
    	a2e = Node.linkNodes(nodes[0], nodes[4], 2337370, speed100 , null);
    	a2d = Node.linkNodes(nodes[0], nodes[3], 9794620, speed1000, null);
    	a2i = Node.linkNodes(nodes[0], nodes[8], 2357300, speed50  , null);
    	b2a = Node.linkNodes(nodes[1], nodes[0], 1200690, speed100 , null);
    	b2c = Node.linkNodes(nodes[1], nodes[2],  441200, speed100 , null);
    	c2d = Node.linkNodes(nodes[2], nodes[3], 3895290, speed50  , null);
    	c2e = Node.linkNodes(nodes[2], nodes[4],  351660, speed50  , null);
    	d2c = Node.linkNodes(nodes[3], nodes[2],  166941, speed100 , null);
    	e2d = Node.linkNodes(nodes[4], nodes[3],  226040, speed50  , null);
    	e2i = Node.linkNodes(nodes[4], nodes[8], 1000350, speed50  , null);
    	f2h = Node.linkNodes(nodes[5], nodes[7], 1669530, speed50  , null);
    	g2f = Node.linkNodes(nodes[6], nodes[5],  401060, speed100 , null);
    	h2g = Node.linkNodes(nodes[7], nodes[6],  629560, speed50  , null);
    	i2d = Node.linkNodes(nodes[8], nodes[3], 3843640, speed50  , null);
    	i2e = Node.linkNodes(nodes[8], nodes[4], 1333800, speed100 , null);
    	
    	/*Point topleft = new Point(8,-2);
    	Point bottomright = new Point(-1, 6);*/
    	
    	graphstats = new GraphStatistics(null, 16, 0, 100, 11);
    	
    	graph = new Graph("MyID", "MyMAP", Arrays.asList(nodes), graphstats);
    	
    	
    }
    
    /** Fonction permettant de tester l'algorithme de Dijkstra sur un chemin existant de A à D
     *  La chemin attendu est A->B->C->E->D */
    @Test
    public void testShortestPathA2D() {
    	// Chemin de A a D
    	Node noeudA = nodes[0], noeudD = nodes[3];
    	// On utilise l'arcInspector pour la distance la plus courte
    	ArcInspector arcInspectorUsed = ArcInspectorFactory.getAllFilters().get(0);
    	ShortestPathData data = new ShortestPathData(graph, noeudA, noeudD, arcInspectorUsed);
    	// On crée la liste d'arcs à utiliser
    	ArrayList<Arc> arcs = new ArrayList<>();
    	arcs.add(a2b);
    	arcs.add(b2c);
    	arcs.add(c2e);
    	arcs.add(e2d);
    	// On crée la solution correcte
    	ShortestPathSolution SolutionPathA2D = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
    	
    	// On lance l'algorithme de Dijkstra sur le graphe
    	AStarAlgorithm A = new AStarAlgorithm(data);
    	ShortestPathSolution SolutionAlgorithme = A.run();
    	System.out.println("Solution trouvee : " + SolutionAlgorithme.getPath().getArcs());
    	/*System.out.println("Temps de resolution 1 " + SolutionAlgorithme.getSolvingTime());
    	System.out.println("Cout du chemin 1 " + SolutionAlgorithme.getPath().getLength());*/
    	for (int i = 0; i < 9; i++) {
    		System.out.println(nodes[i]);
    	}
    	
    	
    	assertEquals(SolutionPathA2D, SolutionAlgorithme);
    }
    
    /** Fonction permettant de tester l'algorithme de Dijkstre sur un chemin inexistant */
    @Test
    public void testNoPathExistingA2F() {
    	// Chemin de A a F
    	Node noeudA = nodes[0], noeudF = nodes[5];
    	// On utilise l'arcInspector pour la distance la plus courte
    	ArcInspector arcInspectorUsed = ArcInspectorFactory.getAllFilters().get(0);
    	ShortestPathData data = new ShortestPathData(graph, noeudA, noeudF, arcInspectorUsed);
    	// On crée la solution correcte
    	ShortestPathSolution SolutionPathA2F = new ShortestPathSolution(data, Status.INFEASIBLE);
    	
    	// On lance l'algorithme de Dijkstra sur le graphe
    	AStarAlgorithm A = new AStarAlgorithm(data);
    	ShortestPathSolution SolutionAlgorithme = A.run();
    	System.out.println("Temps de resolution 2 " + SolutionAlgorithme.getSolvingTime());
    	
    	assertEquals(SolutionPathA2F, SolutionAlgorithme);
    }
    
    /** Fonction permettant de tester l'algorithme de Dijkstra sur un chemin ayant pour origine et pour
     *  destination le même noeud */
    @Test
    public void testShortestPathA2A() {
    	// Chemin de A a A
    	Node noeudA = nodes[0];
    	// On utilise l'arcInspector pour la distance la plus courte
    	ArcInspector arcInspectorUsed = ArcInspectorFactory.getAllFilters().get(0);
    	ShortestPathData data = new ShortestPathData(graph, noeudA, noeudA, arcInspectorUsed);
    	// On crée la liste d'arcs à utiliser
    	ArrayList<Arc> arcs = new ArrayList<>();
    	// On crée la solution correcte
    	ShortestPathSolution SolutionPathA2D = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
    	
    	// On lance l'algorithme de Dijkstra sur le graphe
    	AStarAlgorithm A = new AStarAlgorithm(data);
    	ShortestPathSolution SolutionAlgorithme = A.run();
    	System.out.println("Temps de resolution 3 " + SolutionAlgorithme.getSolvingTime());
    	/*for (int i = 0; i < 9; i++) {
    		System.out.println(nodes[i]);
    	}*/
    	
    	assertEquals(SolutionPathA2D, SolutionAlgorithme);
    }
    
    /** Fonction permettant de tester l'algorithme de Dijkstra sur un chemin ayant pour origine et pour
     *  destination deux noeuds connectés */
    @Test
    public void testShortestPathA2B() {
    	// Chemin de A a B
    	Node noeudA = nodes[0], noeudB = nodes[1];
    	// On utilise l'arcInspector pour la distance la plus courte
    	ArcInspector arcInspectorUsed = ArcInspectorFactory.getAllFilters().get(0);
    	ShortestPathData data = new ShortestPathData(graph, noeudA, noeudB, arcInspectorUsed);
    	// On crée la liste d'arcs à utiliser
    	ArrayList<Arc> arcs = new ArrayList<>();
    	arcs.add(a2b);
    	// On crée la solution correcte
    	ShortestPathSolution SolutionPathA2D = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
    	
    	// On lance l'algorithme de Dijkstra sur le graphe
    	AStarAlgorithm A = new AStarAlgorithm(data);
    	ShortestPathSolution SolutionAlgorithme = A.run();
    	System.out.println("Temps de resolution 4 " + SolutionAlgorithme.getSolvingTime());
    	/*for (int i = 0; i < 9; i++) {
    		System.out.println(nodes[i]);
    	}*/
    	
    	assertEquals(SolutionPathA2D, SolutionAlgorithme);
    }
    
    /** Fonction permettant de tester l'algorithme de Dijkstra sur un chemin existant de A à D en mode plus rapide
     *  La chemin attendu est A->B->C->E->D */
    @Test
    public void testFastestPathA2D() {
    	// Chemin de A a D
    	Node noeudA = nodes[0], noeudD = nodes[3];
    	// On utilise l'arcInspector pour le temps le plus court
    	ArcInspector arcInspectorUsed = ArcInspectorFactory.getAllFilters().get(2);
    	ShortestPathData data = new ShortestPathData(graph, noeudA, noeudD, arcInspectorUsed);
    	// On crée la liste d'arcs à utiliser
    	ArrayList<Arc> arcs = new ArrayList<>();
    	arcs.add(a2d);
    	// On crée la solution correcte
    	ShortestPathSolution SolutionPathA2D = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
    	
    	// On lance l'algorithme de Dijkstra sur le graphe
    	AStarAlgorithm A = new AStarAlgorithm(data);
    	ShortestPathSolution SolutionAlgorithme = A.run();
    	System.out.println("Temps de resolution 1 " + SolutionAlgorithme.getSolvingTime());
    	System.out.println("Cout du chemin 1 " + SolutionAlgorithme.getPath().getLength());
    	/*for (int i = 0; i < 9; i++) {
    		System.out.println(nodes[i]);
    	}*/
    	
    	assertEquals(SolutionPathA2D, SolutionAlgorithme);
    }
    
    /** Fonction permettant de tester l'algorithme de Dijkstre sur un chemin inexistant */
    @Test
    public void testNoFastestPathExistingA2F() {
    	// Chemin de A a F
    	Node noeudA = nodes[0], noeudF = nodes[5];
    	// On utilise l'arcInspector pour la distance la plus courte
    	ArcInspector arcInspectorUsed = ArcInspectorFactory.getAllFilters().get(2);
    	ShortestPathData data = new ShortestPathData(graph, noeudA, noeudF, arcInspectorUsed);
    	// On crée la solution correcte
    	ShortestPathSolution SolutionPathA2F = new ShortestPathSolution(data, Status.INFEASIBLE);
    	
    	// On lance l'algorithme de Dijkstra sur le graphe
    	AStarAlgorithm A = new AStarAlgorithm(data);
    	ShortestPathSolution SolutionAlgorithme = A.run();
    	System.out.println("Temps de resolution 2 " + SolutionAlgorithme.getSolvingTime());
    	
    	assertEquals(SolutionPathA2F, SolutionAlgorithme);
    }
    
    /** Fonction permettant de tester l'algorithme de Dijkstra sur un chemin ayant pour origine et pour
     *  destination le même noeud */
    @Test
    public void testFastestPathA2A() {
    	// Chemin de A a A
    	Node noeudA = nodes[0];
    	// On utilise l'arcInspector pour la distance la plus courte
    	ArcInspector arcInspectorUsed = ArcInspectorFactory.getAllFilters().get(2);
    	ShortestPathData data = new ShortestPathData(graph, noeudA, noeudA, arcInspectorUsed);
    	// On crée la liste d'arcs à utiliser
    	ArrayList<Arc> arcs = new ArrayList<>();
    	// On crée la solution correcte
    	ShortestPathSolution SolutionPathA2D = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
    	
    	// On lance l'algorithme de Dijkstra sur le graphe
    	AStarAlgorithm A = new AStarAlgorithm(data);
    	ShortestPathSolution SolutionAlgorithme = A.run();
    	System.out.println("Temps de resolution 3 " + SolutionAlgorithme.getSolvingTime());
    	/*for (int i = 0; i < 9; i++) {
    		System.out.println(nodes[i]);
    	}*/
    	
    	assertEquals(SolutionPathA2D, SolutionAlgorithme);
    }
    
    /** Fonction permettant de tester l'algorithme de Dijkstra sur un chemin ayant pour origine et pour
     *  destination deux noeuds connectés */
    @Test
    public void testFastestPathA2B() {
    	// Chemin de A a B
    	Node noeudA = nodes[0], noeudB = nodes[1];
    	// On utilise l'arcInspector pour la distance la plus courte
    	ArcInspector arcInspectorUsed = ArcInspectorFactory.getAllFilters().get(0);
    	ShortestPathData data = new ShortestPathData(graph, noeudA, noeudB, arcInspectorUsed);
    	// On crée la liste d'arcs à utiliser
    	ArrayList<Arc> arcs = new ArrayList<>();
    	arcs.add(a2b);
    	// On crée la solution correcte
    	ShortestPathSolution SolutionPathA2D = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
    	
    	// On lance l'algorithme de Dijkstra sur le graphe
    	AStarAlgorithm A = new AStarAlgorithm(data);
    	ShortestPathSolution SolutionAlgorithme = A.run();
    	System.out.println("Temps de resolution 4 " + SolutionAlgorithme.getSolvingTime());
    	/*for (int i = 0; i < 9; i++) {
    		System.out.println(nodes[i]);
    	}*/
    	
    	assertEquals(SolutionPathA2D, SolutionAlgorithme);
    }
    
}