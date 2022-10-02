package modele;



public class Restaurant extends Case {
	
	public void action (Aventurier p) {
		int vp = p.getVP();
		p.setVP(vp + 100);
		p.setStep(p.getStep()+1);
		p.setMoney(p.getMoney()-15);
	}
}
