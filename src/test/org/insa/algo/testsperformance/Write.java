package org.insa.algo.testsperformance;

import java.io.FileWriter;
import java.util.ArrayList;

public class Write {
	public Write(String nomFichier, String mapName, ArrayList<ExecutionResults>listeResultatPerformance) {
		String commaDelimiter = ",";
		String newLineSeparator ="\n";
		String fileHeader ="Origine,Destination,Temps Execution Dijkstra (en ms), Nb Sommets, Temps CPU A* (en ms), Nb Sommets"; // Nb sommets
		try {
			FileWriter fileWriter = new FileWriter("src/test/resultats_performances"+nomFichier);
			fileWriter.append(fileHeader);
			for (ExecutionResults p : listeResultatPerformance) {
				fileWriter.append(newLineSeparator);
				fileWriter.append(String.valueOf(p.getOrigine()));
				fileWriter.append(commaDelimiter);
				fileWriter.append(String.valueOf(p.getDestination()));
				fileWriter.append(commaDelimiter);
				fileWriter.append(String.valueOf(p.getTempsExecutionDijkstra()));
				fileWriter.append(commaDelimiter);
				fileWriter.append(String.valueOf(p.getNbNodesVisitedDijkstra()));
				fileWriter.append(commaDelimiter);
				fileWriter.append(String.valueOf(p.getTempsExecutionAStar()));
				fileWriter.append(commaDelimiter);
				fileWriter.append(String.valueOf(p.getNbNodesVisitedAStar()));
			}
			fileWriter.flush();
			fileWriter.close();
			System.out.println("Done");	
		} 
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
