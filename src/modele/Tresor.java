package modele;

public class Tresor extends Case {
	
	public void action (Aventurier p){
		p.setStep(p.getStep()+1);
		p.setVP(p.getVP()-5);
		p.setMoney(p.getMoney()+30);
		}
}
