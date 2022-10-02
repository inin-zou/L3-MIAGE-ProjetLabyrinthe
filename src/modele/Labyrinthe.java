package modele;


/**
 * Classe Labyrinthe.
 */
public class Labyrinthe
{
	/*------------*/
	/* Propriétés */
	/*------------*/

	/*----- Taille du labyrinthe -----*/
	private final int taille_du_labyrinthe;

	/*----- Liste des cases composant le labyrinthe -----*/
	private final Case[][] cases;

	/*----- Aventurier qui doit traverser le labyrinthe -----*/
	private Aventurier aventurier;

	/*--------------*/
	/* Constructeur */
	/*--------------*/

	public Labyrinthe (int taille)
		{
		this.taille_du_labyrinthe = taille;
		this.cases = new Case[this.taille_du_labyrinthe][this.taille_du_labyrinthe];
		}


	/*----------*/
	/* Méthodes */
	/*----------*/

	/**
	 * Retourne la taille du labyrinthe.
	 */
	public int getTaille () { return this.taille_du_labyrinthe; }


	/**
	 * Affecte / retourne la case en position i,j.
	 */
	public void setCase (int i, int j, Case c) { this.cases[i][j] = c; }
	public Case getCase (int i, int j) { return this.cases[i][j]; }


	/**
	 * Retourne / affecte l'aventurier du labyrinthe.
	 */
	public Aventurier getAventurier () { return this.aventurier; }
	public void setAventurier (Aventurier aventurier) { this.aventurier = aventurier; }
	
	

} /*----- Fin de la classe Labyrinthe -----*/
