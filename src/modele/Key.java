package modele;

/**
 * Classe qui représente une cle.
 */
public class Key extends Case{
	public void action (Aventurier p) {
		int nbKey = p.getKey();
		p.setKey(nbKey + 1);
		p.setStep(p.getStep()+1);
		p.setVP(p.getVP()-5);
	}
}

