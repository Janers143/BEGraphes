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
import org.insa.graph.Node;
import org.insa.graph.Path;
import org.insa.graph.Point;
import org.insa.graph.RoadInformation;
import org.insa.graph.RoadInformation.RoadType;
import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraScenarioTest{
	@Test
	public void testMapMidiPyrenneesDistance() throws Exception {
		String mapName = "/home/commetud/3MIC/Graphes-et-Algorithmes/Maps/midi-pyrenees.mapgr";
		
		DijkstraTestMap test = new DijkstraTestMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : Midi-Pyrenees -------------------------######");
		System.out.println("#####----- Mode : DISTANCE -------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin nul ---------");
		origine = 0 ;
		destination = 0;
		test.testOnMap(mapName, 0, origine, destination);    
		
		System.out.println("\n----- Cas d'un chemin simple ------");
		origine = 38926;
		destination = 59015;
		test.testOnMap(mapName, 0, origine, destination);    	
		
		System.out.println("----- Cas d'un chemin long --------");
		origine = 187898;
		destination = 482545;
		test.testOnMap(mapName, 0, origine, destination);
	}
	
	@Test
	public void testMapMidiPyrenneesTemps() throws Exception {
		String mapName = "/home/commetud/3MIC/Graphes-et-Algorithmes/Maps/midi-pyrenees.mapgr";
		
		DijkstraTestMap test = new DijkstraTestMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : Midi-Pyrenees -------------------------######");
		System.out.println("#####----- Mode : TEMPS ----------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin nul ---------");
		origine = 0 ;
		destination = 0;
		test.testOnMap(mapName, 1, origine, destination);    
		
		System.out.println("\n----- Cas d'un chemin simple ------");
		origine = 38926;
		destination = 59015;
		test.testOnMap(mapName, 1, origine, destination);    	
	
		System.out.println("----- Cas d'un chemin long --------");
		origine = 187898;
		destination = 482545;
		test.testOnMap(mapName, 1, origine, destination);
	}
	
	@Test
	public void testMapInsaDistance() throws Exception {
		String mapName = "/home/commetud/3MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
		
		DijkstraTestMap test = new DijkstraTestMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : INSA ----------------------------------######");
		System.out.println("#####----- Mode : DISTANCE -------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin nul ---------");
		origine = 0 ;
		destination = 0;
		test.testOnMap(mapName, 0, origine, destination);    
		
		System.out.println("\n----- Cas d'un chemin simple ------");
		origine = 764;
		destination = 412;
		test.testOnMap(mapName, 0, origine, destination);    	
		
		System.out.println("----- Cas d'un chemin long --------");
		origine = 954;
		destination = 810;
		test.testOnMap(mapName, 0, origine, destination);
	}
	
	@Test
	public void testMapInsaTemps() throws Exception {
		String mapName = "/home/commetud/3MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
		
		DijkstraTestMap test = new DijkstraTestMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : INSA ----------------------------------######");
		System.out.println("#####----- Mode : TEMPS ----------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin nul ---------");
		origine = 0 ;
		destination = 0;
		test.testOnMap(mapName, 1, origine, destination);    
		
		System.out.println("\n----- Cas d'un chemin simple ------");
		origine = 764;
		destination = 412;
		test.testOnMap(mapName, 1, origine, destination);    	
	
		System.out.println("----- Cas d'un chemin long --------");
		origine = 954;
		destination = 810;
		test.testOnMap(mapName, 1, origine, destination);
	}
	
	@Test
	public void testMapCarreDistance() throws Exception {
		String mapName = "/home/commetud/3MIC/Graphes-et-Algorithmes/Maps/carre.mapgr";
		
		DijkstraTestMap test = new DijkstraTestMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : Carre ---------------------------------######");
		System.out.println("#####----- Mode : DISTANCE -------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin nul ---------");
		origine = 0 ;
		destination = 0;
		test.testOnMap(mapName, 0, origine, destination);    
		
		System.out.println("\n----- Cas d'un chemin simple ------");
		origine = 12;
		destination = 13;
		test.testOnMap(mapName, 0, origine, destination);    	
		
		System.out.println("----- Cas d'un chemin long --------");
		origine = 0;
		destination = 24;
		test.testOnMap(mapName, 0, origine, destination);
	}
	
	@Test
	public void testMapCarreTemps() throws Exception {
		String mapName = "/home/commetud/3MIC/Graphes-et-Algorithmes/Maps/carre.mapgr";
		
		DijkstraTestMap test = new DijkstraTestMap();
		int origine;
		int destination;
		
		System.out.println("#####----- Test de validité avec oracle sur une carte-----######");
		System.out.println("#####----- Carte : Carre ---------------------------------######");
		System.out.println("#####----- Mode : TEMPS ----------------------------------######");
		System.out.println();
		
		System.out.println("----- Cas d'un chemin nul ---------");
		origine = 0 ;
		destination = 0;
		test.testOnMap(mapName, 1, origine, destination);    
		
		System.out.println("\n----- Cas d'un chemin simple ------");
		origine = 12;
		destination = 13;
		test.testOnMap(mapName, 1, origine, destination);    	
	
		System.out.println("----- Cas d'un chemin long --------");
		origine = 0;
		destination = 24;
		test.testOnMap(mapName, 1, origine, destination);
	}
}