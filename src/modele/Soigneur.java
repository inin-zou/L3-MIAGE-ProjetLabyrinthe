package modele;

public class Soigneur extends Case{
	public void action (Aventurier p) {
		int vp = p.getVP();
		p.setVP(vp+100);
		p.setMoney(p.getMoney()-10);
	}
}
