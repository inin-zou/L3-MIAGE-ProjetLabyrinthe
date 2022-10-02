package jeu;

import vue.Vue;
import modele.Espace;
import modele.Key;
import modele.Mur;
import modele.Ocean;
import modele.Porte;
import modele.Prairie;
import modele.Sortie;
import modele.Tresor;
import modele.Aventurier;
import modele.Restaurant;
import modele.Soigneur;
import modele.Labyrinthe;
import modele.Marchand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Application labyrinthe.
 */
public class AppLabyrinthe
{
	/**
	 * Noms des fichiers contenant des labyrinthes.
	 */
	private static final String LAB_1 = "data" + File.separator + "labyrinthe_1.csv";
	private static final String LAB_2 = "data" + File.separator + "labyrinthe_2.csv";


	/**
	 * Chargement du labyrinthe et de l'aventurier.
	 *
	 * A partir d'un fichier csv, cette méthode crée le labyrinthe et
	 * l'aventurier sur la case de départ du labyrinthe.
	 */
	public static Labyrinthe chargeLabyrinthe (String fichier)
		{
		Labyrinthe lab = null;

		try (Scanner scanner = new Scanner(new FileInputStream(fichier)))
			{
			/*----- Lecture de la taille du labyrinthe -----*/
			int taille = Integer.valueOf(scanner.nextLine());

			/*----- Initialisation du labyrinthe -----*/
			lab = new Labyrinthe(taille);

			/*----- Lecture du fichier et des types de cases composant le labyrinthe -----*/
			for (int i=0; i<taille; i++)
				{
				/*----- Lecture d'une ligne du fichier -----*/
				String[] liste = scanner.nextLine().trim().split(";");

				int type_case;
				for (int j=0; j<taille; j++)
					{
					type_case = Integer.valueOf(liste[j]);

					/*----- Type 0 --> "Espace" -----*/
					if (type_case == 0) lab.setCase(i, j, new Espace());

					/*----- Type 1 --> "Mur" -----*/
					if (type_case == 1) lab.setCase(i, j, new Mur());
					
					/*----- Type 2 --> "Restaurant" -----*/
					if (type_case == 2) lab.setCase(i, j, new Ocean());
					
					/*----- Type 3 --> "Restaurant" -----*/
					if (type_case == 3) lab.setCase(i, j, new Tresor());
					
					/*----- Type 4 --> "Restaurant" -----*/
					if (type_case == 4) lab.setCase(i, j, new Soigneur());
					
					/*----- Type 5 --> "Restaurant" -----*/
					if (type_case == 5) lab.setCase(i, j, new Restaurant());
					
					/*----- Type 6 --> "Porte" -----*/
					if (type_case == 6) lab.setCase(i, j, new Porte());
					
					/*----- Type 7 --> "Key" -----*/
					if (type_case == 7) lab.setCase(i, j, new Key());
					
					/*----- Type 8 --> "Sortie" -----*/
					if (type_case == 8) lab.setCase(i, j, new Sortie());

					/*----- Type 9 --> "Espace de départ" et "Aventurier" -----*/
					if (type_case == 9)
						{
						lab.setCase(i, j, new Espace());
						lab.setAventurier(new Aventurier(i, j, 200, 0, 0, 20,0));
						}
					
					/*----- Type 10 --> "Marchand" -----*/
					if (type_case == 10) lab.setCase(i, j, new Marchand());
					
					/*----- Type 11 --> "Prairie" -----*/
					if (type_case == 11) lab.setCase(i, j, new Prairie());
					
					}
				}
			}
		catch (FileNotFoundException ex)
			{
			System.err.println("Erreur lors de la lecture du fichier : " + fichier + " - " + ex.getMessage());
			}

		return lab;
		}


	/*---------------------*/
	/* Programme principal */
	/*---------------------*/

	public static void main (String[] s) throws InterruptedException
		{
		/*----- Chargement du labyrinthe -----*/
		Labyrinthe lab1 = chargeLabyrinthe(LAB_1);
		Labyrinthe lab2 = chargeLabyrinthe(LAB_2);

		/*----- Création de la fenêtre de visualisation du labyrinthe et affichage -----*/
		new Vue(100, 100, lab1,lab1,lab2);
		}

} /*----- Fin de ma classe AppLabyrinthe -----*/