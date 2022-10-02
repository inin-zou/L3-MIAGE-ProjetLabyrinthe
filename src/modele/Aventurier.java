package modele;


/**
 * Classe qui représente l'aventurier qui traverse le labyrinthe.
 */
public class Aventurier
{
	/*------------*/
	/* Propriétés */
	/*------------*/

	/*----- Sa position dans le labyrinthe -----*/
	private int x;
	private int y;
	private int vp;
	private int step;
	private int key;
	private int money;
	private int canoe;

	/*--------------*/
	/* Constructeur */
	/*--------------*/

	public Aventurier (int x, int y, int vp,int step, int key, int money, int canoe )
		{
		this.x = x;
		this.y = y;
		this.vp = vp;
		this.step = step;
		this.key = key;
		this.money = money;
		this.canoe = canoe;
		}


	/*----------*/
	/* Méthodes */
	/*----------*/

	public int getX () { return this.x; }
	public void setX (int x) { this.x = x; }

	public int getY () { return this.y; }
	public void setY (int y) { this.y = y; }
	
	public int getVP () { return this.vp; }
	public void setVP (int vp) { this.vp = vp; }
	
	public int getStep () { return this.step; }
	public void setStep (int step) { this.step = step; }
	
	public int getKey () { return this.key; }
	public void setKey (int key) { this.key = key; }
	
	public int getMoney () { return this.money; }
	public void setMoney (int money) { this.money = money; }
	
	public int getCanoe () { return this.canoe; }
	public void setCanoe (int canoe) { this.canoe = canoe; }

} /*----- Fin de la classe Aventurier -----*/
