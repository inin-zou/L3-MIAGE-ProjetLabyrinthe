package modele;

public class Ocean extends Case {
	public void action (Aventurier p){
		p.setStep(p.getStep()+1);
		p.setVP(p.getVP()-7+p.getCanoe()*5);
		}
}
