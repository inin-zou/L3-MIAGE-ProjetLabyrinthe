package modele;

public class Marchand extends Case {
	public void action (Aventurier p)
	{
	p.setMoney(p.getMoney()-50);
	p.setCanoe(p.getCanoe()+1);
	}
}
