package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modele.Aventurier;
import modele.BadRestaurant;
import modele.Espace;
import modele.Labyrinthe;
import java.awt.event.*;

import javax.swing.JMenuItem;



/**
 * Fenêtre de visualisation du labyrinthe.
 */
@SuppressWarnings("serial")
public class Vue extends JFrame
{
	/*------------*/
	/* Propriétés */
	/*------------*/

	/**
	 * Référence vers le labyrinthe que la classe Vue va visualiser.
	 */
	private Labyrinthe labyrinthe;
	private final Labyrinthe labyrinthe1;
	private final Labyrinthe labyrinthe2;

	/*----- Barre d'état de la fenêtre -----*/
	private final JLabel barre_etat;
	
	/*----- MenuBar de la fenêtre -----*/
	private final JMenuBar menuBar;
	
	
	/*----- Zone de dessin -----*/
	Dessin dessin;
	
	/*----- Zone d'Etat -----*/
	Etat etat;

	boolean startFlag = false;
	long startTime;
	
	/*--------------*/
	/* Constructeur */
	/*--------------*/
	public Vue (int x, int y, Labyrinthe labyrinthe, Labyrinthe labyrinthe1, Labyrinthe labyrinthe2)
		{
		/*----- Lien avec le labyrinthe -----*/
		this.labyrinthe = labyrinthe;
		this.labyrinthe1 = labyrinthe1;
		this.labyrinthe2 = labyrinthe2;
		

		/*----- Paramètres de la fenêtre -----*/
		this.setTitle("Labyrinthe");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLocation(x,y);
		this.setLayout(new BorderLayout());
		
		/*----- Zone de dessin -----*/
		this.dessin = new Dessin(600);
		this.dessin.setFocusable(true);
		/*----- Attachement des écouteurs des évènements souris et clavier -----*/
		this.dessin.addMouseListener(this.dessin);
		this.dessin.addMouseMotionListener(this.dessin);
		this.dessin.addKeyListener(this.dessin);
		this.add(this.dessin, BorderLayout.CENTER);
		
		/*----- Barre d'état de la fenêtre -----*/	

		this.barre_etat = new JLabel("barre_etat");
		this.add(this.barre_etat, BorderLayout.SOUTH);
		
		/*----- Zone d'Etat -----*/
		this.etat = new Etat(300,600);
		this.etat.setFocusable(true);
		this.add(this.etat, BorderLayout.EAST);

		
		/*----- MenuBar de la fenêtre -----*/
		this.menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu menu1 = new JMenu("Game");
		JMenu menu2 = new JMenu("Map");
		JMenu menu3 = new JMenu("Help");
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		JMenuItem item1 = new JMenuItem ("Start");
		JMenuItem item2 = new JMenuItem ("Continue");
		JMenuItem item3 = new JMenuItem ("Stop");
		JMenuItem item4 = new JMenuItem ("Quit");
		JMenuItem item5 = new JMenuItem ("Lab_1");
		JMenuItem item6 = new JMenuItem ("Lab_2");
		JMenuItem item7 = new JMenuItem ("Instructions de jeu");
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu1.add(item4);
		menu2.add(item5);
		menu2.add(item6);
		menu3.add(item7);

		
		
		this.add(this.menuBar, BorderLayout.NORTH);
		
		
		item1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == item1) {
					startTime = System.currentTimeMillis();
					startFlag = true;
					item1.setEnabled(false);
					item2.setEnabled(false);
					item3.setEnabled(true);
				}
			}
		});
		
		item2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == item2) {
					startFlag = true;
					item1.setEnabled(false);
					item2.setEnabled(false);
					item3.setEnabled(true);
				}
			}
		});
		
		item3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == item3) {
					startFlag = false;
					item1.setEnabled(false);
					item2.setEnabled(true);
					item3.setEnabled(false);
				}
			}
		});
		
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLabyrinthe(labyrinthe1);
			}
		});
		
		item6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLabyrinthe(labyrinthe2);
			}
		});
		
		item7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html>Vous êtes un aventurier qui se situe à l'entrée d’un labyrinthe. "
								+ "<br>Vous allez vous déplacer de case en case et en trouver la sortie "
								+ "<br>le plus rapidement possible et avant de mourir d'épuisement."
								+"</html>");
			}
		});

		/*----- Pour ajuster la fenêtre à son contenu et la rendre visible -----*/
		this.pack();
		this.setVisible(true);
		}

	
	public Labyrinthe getLabyrinthe() {return labyrinthe;}
	public void setLabyrinthe(Labyrinthe labyrinthe) {this.labyrinthe = labyrinthe;}



	/*----------------*/
	/* Classe interne */
	/*----------------*/
	
	class Etat extends JPanel
		{
		/*----- Propriétés de la classe interne -----*/
		int a;
		int b;
		
		public Etat (int a, int b)
			{
			/*----- Initialisation des données -----*/
			this.a = a;
			this.b = b;
			
			/*----- Paramètre du JPanel -----*/
			this.setPreferredSize(new Dimension(this.a, this.b));
			}
		
		public void paintComponent(Graphics g)
			{
			
			super.paintComponent(g); 
			g.drawString("Etat d'Aventurier : ",25,50);//在坐标在75，75的位置输出文本

			/*----- Affichage de l'aventurier -----*/
			Aventurier aventurier = labyrinthe.getAventurier();
			g.drawString("Position de l'Aventurier : (" + aventurier.getX() +", "+ aventurier.getY()+")",25,75);
		
			String Step;
			Step = "Step : " + aventurier.getStep();
			g.drawString(Step,25,100);
			
			String VP;
			VP = "VP : " + aventurier.getVP();
			g.drawString(VP,25,125);
			
			String Money;
			Money = "Argent : " + aventurier.getMoney() + " EUR";
			g.drawString(Money,25,150);
			
			long t = (System.currentTimeMillis()-startTime)/1000;
			long m = t/60;
			long s = t-m*60;
			
			String Time;
			if (t < 72*60*60) {
				Time = "Temps : " + m + "m" + s + "s";
				g.drawString(Time,25,175);
				
			}
			
			String Key;
			if (aventurier.getKey()>0) {
				Key = "Nombre de clé : " + aventurier.getKey();
				g.drawString(Key,25,200);
			}
			
			
			String Canoe;
			if (aventurier.getCanoe()>0) {
				Canoe = "Nombre de canoe : " + aventurier.getCanoe();
				g.drawString(Canoe,25,225);
			}
			
			
			
			
			
			
			String InstructionTitle;
			InstructionTitle = "Instruction de jeu :";
			g.drawString(InstructionTitle,25,350);
			
			String Instruction1;
			Instruction1 = "zone grise : un soigneur";
			g.drawString(Instruction1,25,375);
			
			String inst,inst1,inst2,inst3,inst4,inst5,inst6;
			inst = "zone jaune : clé ou trésor";
			inst1 = "zone orange : une porte fermée";
			inst2 = "zone cyane : un restaurant qui disparaîtra";
			inst3 = "zone rouge : sortie de labyrinthe";
			inst4 = "zone bleue : océan";
			inst5 = "zone grise foncée : un marchand";
			inst6 = "zone vert : prairie";
			g.drawString(inst,25,400);
			g.drawString(inst1,25,425);
			g.drawString(inst2,25,450);
			g.drawString(inst3,25,475);
			g.drawString(inst4,25,500);
			g.drawString(inst5,25,525);
			g.drawString(inst6,25,550);
				
					
			repaint();
			
			}
		
		}
	
	
	
	

	class Dessin extends JPanel implements KeyListener, MouseListener, MouseMotionListener
		{
		/*----- Propriétés de la classe interne -----*/
		int largeur;
		int taille_case;

		/*----- Constructeur de la classe interne -----*/
		public Dessin (int larg)
			{
			/*----- Initialisation des données -----*/
			this.taille_case = larg / labyrinthe.getTaille();
			this.largeur = this.taille_case * labyrinthe.getTaille();
			
			/*----- Paramètre du JPanel -----*/
			this.setPreferredSize(new Dimension(this.largeur, this.largeur));
			
			}

		/**
		 * Méthode qui permet de dessiner ou redessinner le labyrinthe lorsque
		 * la méthode repaint() est appellée sur la classe Dessin.
		 */
		@Override
		public void paint (Graphics g)
			{
			/*----- On efface le dessin en entier de dessin -----*/
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0,0,this.largeur,this.largeur);

			/*----- Affichage des cases du labyrinthe -----*/
			for (int i=0; i < labyrinthe.getTaille(); i++)
				for (int j=0; j < labyrinthe.getTaille(); j++)
					{
					/*----- Couleur de la case -----*/
					if (labyrinthe.getCase(i,j).getClassName().equals("Mur"))		g.setColor(Color.BLACK);
					if (labyrinthe.getCase(i,j).getClassName().equals("Espace"))	g.setColor(Color.WHITE);
					if (labyrinthe.getCase(i,j).getClassName().equals("Sortie"))	g.setColor(Color.RED);
					if (labyrinthe.getCase(i,j).getClassName().equals("Key"))	g.setColor(Color.YELLOW);
					if (labyrinthe.getCase(i,j).getClassName().equals("Tresor"))	g.setColor(Color.YELLOW);
					if (labyrinthe.getCase(i,j).getClassName().equals("Porte"))	g.setColor(Color.ORANGE);
					if (labyrinthe.getCase(i,j).getClassName().equals("Restaurant"))	g.setColor(Color.CYAN);
					if (labyrinthe.getCase(i,j).getClassName().equals("BadRestaurant"))	g.setColor(Color.CYAN);
					if (labyrinthe.getCase(i,j).getClassName().equals("Ocean"))	g.setColor(Color.BLUE);
					if (labyrinthe.getCase(i,j).getClassName().equals("Prairie"))	g.setColor(Color.GREEN);
					if (labyrinthe.getCase(i,j).getClassName().equals("Soigneur"))	g.setColor(Color.LIGHT_GRAY);
					if (labyrinthe.getCase(i,j).getClassName().equals("Marchand"))	g.setColor(Color.DARK_GRAY);
					
					

					/*----- Affichage de la case sous forme d'un rectangle plein -----*/
					g.fillRect(taille_case*j, taille_case*i, taille_case, taille_case);
					}
					

			/*----- Affichage de l'aventurier -----*/
			Aventurier aventurier = labyrinthe.getAventurier();
			g.setColor(Color.MAGENTA);
			g.fillOval(taille_case*aventurier.getY() + taille_case/4, taille_case*aventurier.getX() + taille_case/4, taille_case/2, taille_case/2);
			
			repaint();
			}

		/**
		 * Gestion des interactions souris et clavier sur le labyrinthe.
		 */
		@Override
		public void mouseClicked (MouseEvent e)
			{
			/*----- Lecture de la position de la souris dans le dessin -----*/
			int x = e.getX();
			int y = e.getY();

			/*----- Recherche des coordonnées de la case du labyrinthe sur laquelle le joueur a cliqué -----*/
			int ligne = y / this.taille_case;
			int colonne = x / this.taille_case;

			/*----- On regarde si l'aventiruer est sur la case syr laquelle on vient de cliquer -----*/
			String msgAventurier = "";
			if (labyrinthe.getAventurier().getX() == ligne && labyrinthe.getAventurier().getY() == colonne)
				msgAventurier = "\nL'aventurier est sur cette case.";

			/*----- Etat de la case -----*/
			JOptionPane.showMessageDialog(this, "Vous avez cliqué sur la case (" + ligne + "," + colonne +").\nC'est un "
												+ labyrinthe.getCase(ligne, colonne).getClassName()
												+ "." + msgAventurier);
			}

		@Override
		public void mousePressed (MouseEvent e) { }

		@Override
		public void mouseReleased (MouseEvent e) { }

		@Override
		public void mouseEntered (MouseEvent e) { }

		@Override
		public void mouseExited (MouseEvent e) { }

		@Override
		public void mouseDragged (MouseEvent e) { }

		@Override
		public void mouseMoved (MouseEvent e)
			{
			int x = e.getX();
			int y = e.getY();
			
			int ligne = y / this.taille_case;
			int colonne = x / this.taille_case;
			
			barre_etat.setText("Position de la souris : (" + ligne + ", " + colonne+")");
			}

		@Override
		public void keyTyped (KeyEvent e) { /* Ne pas utiliser */ }

		@Override
		public void keyPressed (KeyEvent e) { }

		@Override
		public void keyReleased (KeyEvent e)
			{
			if (!startFlag) {
				JOptionPane.showMessageDialog(this, "Il faut cliquer sur 'Start'.");
				return;
			}
			/**
			 * Déplacement de l'aventurier dans le labyrinthe.
			 */
			Aventurier aventurier = labyrinthe.getAventurier();
			
			int posX = labyrinthe.getAventurier().getX();
			int posY = labyrinthe.getAventurier().getY();
			int step = labyrinthe.getAventurier().getStep();
			
			long t = (System.currentTimeMillis()-startTime)/1000;
			long m = t/60;
			long s = t-m*60;
			
			/*----- Vers le bas -----*/
			if (e.getKeyCode() == KeyEvent.VK_DOWN){
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Espace")){
					labyrinthe.getAventurier().setX(posX+1);
					labyrinthe.getCase(posX+1,posY).action(aventurier);
					}
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Ocean")){
					labyrinthe.getAventurier().setX(posX+1);
					labyrinthe.getCase(posX+1,posY).action(aventurier);
					}
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Prairie")){
					labyrinthe.getAventurier().setX(posX+1);
					labyrinthe.getCase(posX+1,posY).action(aventurier);
					}
				
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Soigneur")){
					if (aventurier.getMoney()>=15) {
						if (JOptionPane.showConfirmDialog(this,"Voulez vous recevoir un traitement par le soigneur ? "+"\n(coût de 10 euros)") == 0) {
							labyrinthe.getAventurier().setX(posX+1);
							JOptionPane.showMessageDialog(this, "Le soigneur a soigné de tes blessures"+"\n(VP + 100)");
							labyrinthe.getCase(posX+1,posY).action(aventurier);
							labyrinthe.setCase(posX+1,posY, new Espace());
						}
					}
					else 
						JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le traitement.");
				}
				
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Marchand")){
					if (JOptionPane.showConfirmDialog(this,"Voulez-vous acheter un canoe ?"+"\n(coût de 50 euros)") == 0) {
						if (aventurier.getMoney()<50) {
							JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le canoe.");
						}
						else {
							JOptionPane.showMessageDialog(this, "Deal.");
							labyrinthe.getCase(posX+1,posY).action(aventurier);
							labyrinthe.setCase(posX+1,posY, new Espace());
						}
					}
				}
					
				
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Tresor")){
					labyrinthe.getAventurier().setX(posX+1);
					JOptionPane.showMessageDialog(this, "Vous trouvez 3 billets de 10 euros par terre !"+"\n(Argent + 30)");
					labyrinthe.getCase(posX+1,posY).action(aventurier);
					labyrinthe.setCase(posX+1,posY, new Espace());
					}
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Mur")) {
					JOptionPane.showMessageDialog(this, "Attention ! Vous allez enfoncer sur le mur.");
					}
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Key")) {
					labyrinthe.setCase(posY+1,posY, new Espace());
					labyrinthe.getAventurier().setX(posX+1);
					JOptionPane.showMessageDialog(this, "Bravo ! Vous avez obtenu une clé.");
					labyrinthe.getCase(posX+1,posY).action(aventurier);
					labyrinthe.setCase(posX+1,posY, new Espace());
					}
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Porte")) {
					if (aventurier.getKey()>=1) {
						JOptionPane.showMessageDialog(this, "Bravo ! Vous avez ouvret une porte.");
						labyrinthe.getAventurier().setX(posX+1);
						labyrinthe.getCase(posX+1,posY).action(aventurier);
						labyrinthe.setCase(posX+1,posY, new Espace());
						}
					else
						JOptionPane.showMessageDialog(this, "Il faut trouver une clé pour ouvrir une porte.");
					}
				
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Restaurant")){
					if (JOptionPane.showConfirmDialog(this,"Entrez-vous dans le RestoLab ?"+"\n(coût de 15 euros)") == 0){
						if (aventurier.getMoney()<15) {
							JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le repas.");
						}
						else {
							JOptionPane.showMessageDialog(this, "Bienvenue au RestoLab !");
							labyrinthe.getAventurier().setX(posX+1);
							int i = (int) (Math.random() * (3 - 1) + 1); //i=1 or i=2 (50% vs 50%)
							if (i == 1) {
							labyrinthe.setCase(posX+1,posY, new BadRestaurant());
							JOptionPane.showMessageDialog(this, "Zut! Vous êtes allergique aux fruits de mer!"+"\n(VP - 50)");
							labyrinthe.getCase(posX+1,posY).action(aventurier);
							labyrinthe.setCase(posX+1,posY, new Espace());
							}
							else {
							JOptionPane.showMessageDialog(this, "Vous avez bien reposé!"+"\n(VP + 100)");
							labyrinthe.getCase(posX+1,posY).action(aventurier);
							labyrinthe.setCase(posX+1,posY, new Espace());
							}
						}
					}
				}
					
				
				
				if (labyrinthe.getCase(posX+1,posY).getClassName().equals("Sortie")){
					labyrinthe.getAventurier().setX(posX+1);
					labyrinthe.getAventurier().setStep(step+1);
					JOptionPane.showMessageDialog(this, "Bravo! Vous avez trouvé la sortie !");
					}
				}

			/*----- Vers le haut -----*/
			if (e.getKeyCode() == KeyEvent.VK_UP){
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Espace")){
					labyrinthe.getAventurier().setX(posX-1);
					labyrinthe.getCase(posX-1,posY).action(aventurier);
					}
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Ocean")){
					labyrinthe.getAventurier().setX(posX-1);
					labyrinthe.getCase(posX-1,posY).action(aventurier);
					}
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Prairie")){
					labyrinthe.getAventurier().setX(posX-1);
					labyrinthe.getCase(posX-1,posY).action(aventurier);
					}
			
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Soigneur")){
					if (aventurier.getMoney()>=15) {
						if (JOptionPane.showConfirmDialog(this,"Voulez vous recevoir un traitement par le soigneur ? "+"\n(coût de 10 euros)") == 0) {
							labyrinthe.getAventurier().setX(posX-1);
							JOptionPane.showMessageDialog(this, "Le soigneur a soigné de tes blessures"+"\n(VP + 100)");
							labyrinthe.getCase(posX-1,posY).action(aventurier);
							labyrinthe.setCase(posX-1,posY, new Espace());
						}
					}
					else 
						JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le traitement.");
				}
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Marchand")){
					if (JOptionPane.showConfirmDialog(this,"Voulez-vous acheter un canoe ?"+"\n(coût de 50 euros)") == 0) {
						if (aventurier.getMoney()<50) {
							JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le canoe.");
						}
						else {
							JOptionPane.showMessageDialog(this, "Deal.");
							labyrinthe.getCase(posX-1,posY).action(aventurier);
							labyrinthe.setCase(posX-1,posY, new Espace());
						}
					}
				}
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Tresor")){
					labyrinthe.getAventurier().setX(posX-1);
					JOptionPane.showMessageDialog(this, "Vous trouvez 3 billets de 10 euros par terre !"+"\n(Argent + 30)");
					labyrinthe.getCase(posX-1,posY).action(aventurier);
					labyrinthe.setCase(posX-1,posY, new Espace());
					}
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Mur")) {
					JOptionPane.showMessageDialog(this, "Attention ! Vous allez enfoncer sur le mur.");
					}
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Key")) {
					labyrinthe.getAventurier().setX(posX-1);
					JOptionPane.showMessageDialog(this, "Bravo ! Vous avez obtenu une clé.");
					labyrinthe.getCase(posX-1,posY).action(aventurier);
					labyrinthe.setCase(posX-1,posY, new Espace());
					}
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Porte")) {
					if (aventurier.getKey()>=1) {
						JOptionPane.showMessageDialog(this, "Bravo ! Vous avez ouvret une porte.");
						labyrinthe.getAventurier().setX(posX-1);
						labyrinthe.getCase(posX-1,posY).action(aventurier);
						labyrinthe.setCase(posX-1,posY, new Espace());
						}
					else
						JOptionPane.showMessageDialog(this, "Il faut trouver une clé pour ouvrir une porte.");
					}
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Restaurant")){
					if (JOptionPane.showConfirmDialog(this,"Entrez-vous dans le RestoLab ?"+"\n(coût de 15 euros)") == 0) {
						if (aventurier.getMoney()<15) {
							JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le repas.");
						}
						else {
							JOptionPane.showMessageDialog(this, "Bienvenue au RestoLab !");
						labyrinthe.getAventurier().setX(posX-1);
						int i = (int) (Math.random() * (3 - 1) + 1); //i=1 or i=2 (50% vs 50%)
						if (i == 1) {
							labyrinthe.setCase(posX-1,posY, new BadRestaurant());
							JOptionPane.showMessageDialog(this, "Zut! Vous êtes allergique aux fruits de mer!"+"\n(VP - 50)");
							labyrinthe.getCase(posX-1,posY).action(aventurier);
							labyrinthe.setCase(posX-1,posY, new Espace());
						}
						else {
							JOptionPane.showMessageDialog(this, "Vous avez bien reposé!"+"\n(VP + 100)");
							labyrinthe.getCase(posX-1,posY).action(aventurier);
							labyrinthe.setCase(posX-1,posY, new Espace());
							}
						}
					}
				}
				
				if (labyrinthe.getCase(posX-1,posY).getClassName().equals("Sortie")){
					labyrinthe.getAventurier().setX(posX-1);
					labyrinthe.getAventurier().setStep(step+1);
					JOptionPane.showMessageDialog(this, "Bravo! Vous avez trouvé la sortie !");
					}
				}

			/*----- Vers la droite -----*/
			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Espace")){
					labyrinthe.getAventurier().setY(posY+1);
					labyrinthe.getCase(posX,posY+1).action(aventurier);
					}
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Ocean")){
					labyrinthe.getAventurier().setY(posY+1);
					labyrinthe.getCase(posX,posY+1).action(aventurier);
					}
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Prairie")){
					labyrinthe.getAventurier().setY(posY+1);
					labyrinthe.getCase(posX,posY+1).action(aventurier);
					}
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Soigneur")){
					if (aventurier.getMoney()>=15) {
						if (JOptionPane.showConfirmDialog(this,"Voulez vous recevoir un traitement par le soigneur ? "+"\n(coût de 10 euros)") == 0) {
							labyrinthe.getAventurier().setY(posY+1);
							JOptionPane.showMessageDialog(this, "Le soigneur a soigné de tes blessures"+"\n(VP + 100)");
							labyrinthe.getCase(posX,posY+1).action(aventurier);
							labyrinthe.setCase(posX,posY+1, new Espace());
						}
					}
					else 
						JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le traitement.");
				}
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Marchand")){
					if (JOptionPane.showConfirmDialog(this,"Voulez-vous acheter un canoe ?"+"\n(coût de 50 euros)") == 0) {
						if (aventurier.getMoney()<50) {
							JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le canoe.");
						}
						else {
							JOptionPane.showMessageDialog(this, "Deal.");
							labyrinthe.getCase(posX,posY+1).action(aventurier);
							labyrinthe.setCase(posX,posY+1, new Espace());
						}
					}
				}
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Tresor")){
					labyrinthe.getAventurier().setY(posY+1);
					JOptionPane.showMessageDialog(this, "Vous trouvez 3 billets de 10 euros par terre !"+"\n(Argent + 30)");
					labyrinthe.getCase(posX,posY+1).action(aventurier);
					labyrinthe.setCase(posX,posY+1, new Espace());
					}
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Mur")) {
					JOptionPane.showMessageDialog(this, "Attention ! Vous allez enfoncer sur le mur.");
					}
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Key")) {
					labyrinthe.getAventurier().setY(posY+1);
					JOptionPane.showMessageDialog(this, "Bravo ! Vous avez obtenu une clé.");
					labyrinthe.getCase(posX,posY+1).action(aventurier);
					labyrinthe.setCase(posX,posY+1, new Espace());
					}
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Porte")) {
					if (aventurier.getKey()>=1) {
						JOptionPane.showMessageDialog(this, "Bravo ! Vous avez ouvret une porte.");
						labyrinthe.getAventurier().setY(posY+1);
						labyrinthe.getCase(posX,posY+1).action(aventurier);
						labyrinthe.setCase(posX,posY+1, new Espace());
						}
					else
						JOptionPane.showMessageDialog(this, "Il faut trouver une clé pour ouvrir une porte.");
					}
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Restaurant")){
					if (JOptionPane.showConfirmDialog(this,"Entrez-vous dans le RestoLab ?"+"\n(coût de 15 euros)") == 0) {
						if (aventurier.getMoney()<15) {
							JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le repas.");
						}
						else {
							JOptionPane.showMessageDialog(this, "Bienvenue au RestoLab !");
						labyrinthe.getAventurier().setY(posY+1);
						int i = (int) (Math.random() * (3 - 1) + 1); //i=1 or i=2 (50% vs 50%)
						if (i == 1) {
							labyrinthe.setCase(posX,posY+1, new BadRestaurant());
							JOptionPane.showMessageDialog(this, "Zut! Vous êtes allergique aux fruits de mer!"+"\n(VP - 50)");
							labyrinthe.getCase(posX,posY+1).action(aventurier);
							labyrinthe.setCase(posX,posY+1, new Espace());
						}
						else {
							JOptionPane.showMessageDialog(this, "Vous avez bien reposé!"+"\n(VP + 100)");
							labyrinthe.getCase(posX,posY+1).action(aventurier);
							labyrinthe.setCase(posX,posY+1, new Espace());
							}
						}
					}
				}
				
				if (labyrinthe.getCase(posX,posY+1).getClassName().equals("Sortie")){
					labyrinthe.getAventurier().setY(posY+1);
					labyrinthe.getAventurier().setStep(step+1);
					JOptionPane.showMessageDialog(this, "Bravo! Vous avez trouvé la sortie !");
					}
				}

			/*----- Vers la gauche -----*/
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Espace")){
					labyrinthe.getAventurier().setY(posY-1);
					labyrinthe.getCase(posX,posY-1).action(aventurier);
					}
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Ocean")){
					labyrinthe.getAventurier().setY(posY-1);
					labyrinthe.getCase(posX,posY-1).action(aventurier);
					}
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Prairie")){
					labyrinthe.getAventurier().setY(posY-1);
					labyrinthe.getCase(posX,posY-1).action(aventurier);
					}
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Soigneur")){
					if (aventurier.getMoney()>=15) {
						if (JOptionPane.showConfirmDialog(this,"Voulez vous recevoir un traitement par le soigneur ? "+"\n(coût de 10 euros)") == 0) {
							labyrinthe.getAventurier().setY(posY-1);
							JOptionPane.showMessageDialog(this, "Le soigneur a soigné de tes blessures"+"\n(VP + 100)");
							labyrinthe.getCase(posX,posY-1).action(aventurier);
							labyrinthe.setCase(posX,posY-1, new Espace());
						}
					}
					else 
						JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le traitement.");
				}
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Marchand")){
					if (JOptionPane.showConfirmDialog(this,"Voulez-vous acheter un canoe ?"+"\n(coût de 50 euros)") == 0) {
						if (aventurier.getMoney()<50) {
							JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le canoe.");
						}
						else {
							JOptionPane.showMessageDialog(this, "Deal.");
							labyrinthe.getCase(posX,posY-1).action(aventurier);
							labyrinthe.setCase(posX,posY-1, new Espace());
						}
					}
				}
				
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Tresor")){
					labyrinthe.getAventurier().setY(posY-1);
					JOptionPane.showMessageDialog(this, "Vous trouvez 3 billets de 10 euros par terre !"+"\n(Argent + 30)");
					labyrinthe.getCase(posX,posY-1).action(aventurier);
					labyrinthe.setCase(posX,posY-1, new Espace());
					}
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Mur")) {
					JOptionPane.showMessageDialog(this, "Attention ! Vous allez enfoncer sur le mur.");
					}
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Key")) {
					labyrinthe.getAventurier().setY(posY-1);
					JOptionPane.showMessageDialog(this, "Bravo ! Vous avez obtenu une clé.");
					labyrinthe.getCase(posX,posY-1).action(aventurier);
					labyrinthe.setCase(posX,posY-1, new Espace());
					}
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Porte")) {
					if (aventurier.getKey()>=1) {
						JOptionPane.showMessageDialog(this, "Bravo ! Vous avez ouvret une porte.");
						labyrinthe.getAventurier().setX(posY-1);
						labyrinthe.getCase(posX,posY-1).action(aventurier);
						labyrinthe.setCase(posX,posY-1, new Espace());
						}
					else
						JOptionPane.showMessageDialog(this, "Il faut trouver une clé pour ouvrir une porte.");
					}
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Restaurant")){
					if (JOptionPane.showConfirmDialog(this,"Entrez-vous dans le RestoLab ?"+"\n(coût de 15 euros)") == 0) {
						if (aventurier.getMoney()<15) {
							JOptionPane.showMessageDialog(this, "Désolé, vous n’avez pas assez d'argent pour payer le repas.");
						}
						else {
							JOptionPane.showMessageDialog(this, "Bienvenue au RestoLab !");
						labyrinthe.getAventurier().setY(posY-1);
						int i = (int) (Math.random() * (3 - 1) + 1); //i=1 or i=2 (50% vs 50%)
						if (i == 1) {
							labyrinthe.setCase(posX,posY-1, new BadRestaurant());
							JOptionPane.showMessageDialog(this, "Zut! Vous êtes allergique aux fruits de mer!"+"\n(VP - 50)");
							labyrinthe.getCase(posX,posY-1).action(aventurier);
							labyrinthe.setCase(posX,posY-1, new Espace());
						}
						else {
							JOptionPane.showMessageDialog(this, "Vous avez bien reposé!"+"\n(VP + 100)");
							labyrinthe.getCase(posX,posY-1).action(aventurier);
							labyrinthe.setCase(posX,posY-1, new Espace());
							}
						}
					}
				}
				
				if (labyrinthe.getCase(posX,posY-1).getClassName().equals("Sortie")){
					labyrinthe.getAventurier().setY(posY-1);
					labyrinthe.getAventurier().setStep(step+1);
					JOptionPane.showMessageDialog(this, "Bravo! Vous avez trouvé la sortie !");
					}
				}
				
			/*----- On refait le dessin -----*/
			repaint();
			
			if (labyrinthe.getCase(posX,posY).getClassName().equals("Sortie")) {
				if (JOptionPane.showConfirmDialog(this,"Voulez-vous quitter le jeu ?") == 1) {
					
					// si quitter pas 
				}
				else
					JOptionPane.showMessageDialog(this, "<html>Félicitations !"
							+"<br> Vous avez utilisé : " + aventurier.getStep() + " coups !"
							+"<br> Vous avez utilisé : " + m + "m" + s + "s"
							+"<br> Vous avez : " + aventurier.getMoney() + " EUR !"
							+"</html>");
					System.exit(0);
			}
			
			if (aventurier.getVP()<=0) {
				JOptionPane.showMessageDialog(this, "<html>Oops! Vous êtes mort !"
						+"<br> Vous avez fait : " + aventurier.getStep() + " coups !"
						+"<br> Vous avez utilisé : " + m + "m" + s + "s"
						+"<br> Vous avez : " + aventurier.getMoney() + " EUR !"
						+"</html>");
				System.exit(0);
			}
			
			}

		} /*----- Fin de la classe interne Dessin -----*/

} /*----- Fin de la classe Vue -----*/