package org.insa.algo.testsperformance;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/* Permet de récupérer les informations d'un fichier .txt : */
/* Nom de la carte, liste des origines, liste des destinations */
public class Read {

	private String mapName ;
	private ArrayList<Integer> listeOrigine;
	private ArrayList<Integer> listeDestination;

	public Read(String nomFichier) {
		this.listeOrigine = new ArrayList<Integer>();
		this.listeDestination = new ArrayList<Integer>();
		this.readFile(nomFichier);
	}

	public void readFile (String nomFichier) {
		try {
			Scanner scan = new Scanner(new File(nomFichier));
			int origine;
			int destination;
			
			/* Recupere le nom de la carte */
			if (scan.hasNext()) {
				mapName = scan.nextLine();
			}
			
			/* Recupere les origines et destinations */
			while (scan.hasNextInt()) {
				origine = scan.nextInt();
				this.listeOrigine.add(origine);
				if (scan.hasNextInt()) {
					destination = scan.nextInt();
					this.listeDestination.add(destination);
				} 
				else {
					throw new NombreImpairPointsException("le fichier contient un nombre impair de points");
				}
			}
			scan.close();
		}
		catch(Exception e) {
				System.out.println(e.getMessage());
		}
	}

	public String getMapName() {
		return this.mapName;
	}

	public ArrayList<Integer> getListeOrigine(){
		return this.listeOrigine;
	}

	public ArrayList<Integer> getListeDestination(){
		return this.listeDestination;
	}
}