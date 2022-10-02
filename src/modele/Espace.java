package modele;


/**
 * Classe qui représente une zone d'espace libre.
 */
public class Espace extends Case
{
	/**
	 * Méthode qui définit une action / un changement sur l'aventurier.
	 */
	@Override
	public void action (Aventurier p){
		p.setStep(p.getStep()+1);
		p.setVP(p.getVP()-5);
		}

} /*----- Fin de la classe Espace -----*/
