package modele;

public class Prairie extends Case{
	public void action (Aventurier p){
		p.setStep(p.getStep()+1);
		p.setVP(p.getVP()-3);
		}
}
