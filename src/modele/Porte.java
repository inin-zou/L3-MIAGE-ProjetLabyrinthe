package modele;

public class Porte extends Case{
	public void action (Aventurier p) {
		int nbKey = p.getKey();
		p.setKey(nbKey - 1);
		p.setStep(p.getStep()+1);
		p.setVP(p.getVP()-5);
	}
}
