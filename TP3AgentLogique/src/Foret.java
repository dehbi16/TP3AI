import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Foret extends JFrame{

	public int taille;

	private static final long serialVersionUID = 1L;
	private JButton[][] mesCases;
	private ImageIcon AGENT = new ImageIcon(getClass().getResource("images/Agent.png"));
	private ImageIcon MONSTRE = new ImageIcon(getClass().getResource("images/Monstre.png"));
	private ImageIcon CREVASSE = new ImageIcon(getClass().getResource("images/Crevasse.png"));
	private ImageIcon ODEUR = new ImageIcon(getClass().getResource("images/Odeur.png"));
	private ImageIcon VENT = new ImageIcon(getClass().getResource("images/Vent.png"));
	private ImageIcon PORTAIL = new ImageIcon(getClass().getResource("images/Portail.png"));
	private ImageIcon AGENTVENT = new ImageIcon(getClass().getResource("images/AgentVent.png"));
	private ImageIcon AGENTODEUR = new ImageIcon(getClass().getResource("images/AgentOdeur.png"));
	private ImageIcon AGENTPORTAIL = new ImageIcon(getClass().getResource("images/AgentPortail.png"));
	private ImageIcon CASENOIRE = new ImageIcon(getClass().getResource("images/CaseNoire.png"));
	

	public Foret(int taille){
		this.taille = taille;
		mesCases = new JButton[taille][taille];
		setTitle("Foret");
		setSize(700,500);
		setLayout(new GridLayout(taille,taille));
		Container c = getContentPane();
		System.out.println(taille);
		for(int i =0;i<taille;i++) {
			for(int j = 0;j<taille;j++) {				
				mesCases[i][j] = new JButton();
				System.out.println(taille);
				c.add(mesCases[i][j]);
			}
		}
		
		this.repaint();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void placementItem(int i, int j, String item) {
		mesCases[i][j].setIcon(retourneImage(item));
		try {
			mesCases[i][j].setIcon(retourneImage(item));
		} catch(Exception e) {}		
		this.repaint();
	}
	
	public void enlevementItem(int i, int j) {
		mesCases[i][j].setIcon(null);
		try {
			mesCases[i][j].setIcon(null);
		} catch(Exception e) {}		
		this.repaint();
	}
	public ImageIcon retourneImage(String item) {
		ImageIcon img = new ImageIcon();
		if(item.matches("1")) {
			img = AGENT;
		}
		if(item.matches("2")) {
			img = MONSTRE;
		}
		if(item.matches("3")) {
			img = CREVASSE;
		}
		if(item.matches("4")) {
			img = VENT;
		}
		if(item.matches("5")) {
			img = ODEUR;
		}
		if(item.matches("6")) {
			img = PORTAIL;
		}
		if(item.matches("7")) {
			img = AGENTVENT;
		}
		if(item.matches("8")) {
			img = AGENTODEUR;
		}
		if(item.matches("9")) {
			img = AGENTPORTAIL;
		}
		if(item.matches("10")) {
			img = CASENOIRE;
		}
		return img;
	}

	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
}
