package modele;

public class BadRestaurant extends Case {
	public void action (Aventurier p) {
		int vp = p.getVP();
		p.setVP(vp - 50);
		p.setStep(p.getStep()+1);
		p.setMoney(p.getMoney()-15);
	}
}
