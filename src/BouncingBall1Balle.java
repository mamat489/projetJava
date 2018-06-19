/* © Code source librement inspiré par http://www3.ntu.edu.sg/home/ehchua/programming/ */
/* Documentation Java officielle : http://docs.oracle.com/javase/tutorial/2d/index.html */

/* Pour compiler avec tous les warnings, dans un sous-dossier bin:
		javac -Xlint:all -source 1.6 BouncingBall1Balle.java -d bin/
*/
/* Pour exécuter:
		java -cp bin/ BouncingBall1Balle 
*/

import java.awt.*;
import java.util.Formatter;
import javax.swing.*;

/**
 * Classe définissant une balle rebondissante dans un rectangle.
 * La classe étend JPanel, qui est un conteneur d'éléments graphiques.
 */
public class BouncingBall1Balle extends JPanel {
   
	private static final long serialVersionUID = 2015;
   
	/* taille du rectangle (final indique que c'est une constante) */
	public static final int BOX_WIDTH = 800;
	public static final int BOX_HEIGHT = 800;	
	public static final int UPDATE_RATE = 30; // taux de rafraîchissement par seconde
	
	/* définition d'éléments graphiques */
	MaBalle tab;
	
	/* constructeur de la classe */
	public BouncingBall1Balle() {
		// affectation de la taille du JPanel
		// (car BouncingBallSimple hérite des attributs et méthodes de JPanel)
		this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
		
		this.tab = new MaBalle(this, "Balle1");
		this.tab.start();
	}
	
	/* méthode qui dessine les éléments à l'écran, héritée de JPanel */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);    // méthode de dessin du composant étendu
		
		// dessine un rectangle noir à la taille de la fenêtre (le fond)
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
		
		// dessine la balle rouge, aux coordonnées spécifiées
		g.setColor(Color.RED);
		g.fillOval((int) (this.tab.ballX - this.tab.ballRadius),
					(int) (this.tab.ballY - this.tab.ballRadius),
			(int)(2 * this.tab.ballRadius), (int)(2 * this.tab.ballRadius));
  
		// affiche les informations sur la balle et le carré
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 18));
      
		StringBuilder sb1 = new StringBuilder();
		Formatter formatter = new Formatter(sb1);
		formatter.format("[ %s @(%03.0f,%03.0f) Vitesse=(%2.0f,%2.0f) ]",
			this.tab.nom, this.tab.ballX, this.tab.ballY,
			this.tab.ballSpeedX, this.tab.ballSpeedY);			

		g.drawString(sb1.toString(), 20, 30);
	}
  
   /* point d'entrée du programme */
	public static void main(String[] args) {
		// fenêtre principale du programme
		JFrame frame = new JFrame("Balle rebondissante");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new BouncingBall1Balle());
		frame.pack();
		frame.setVisible(true);
	}
}


class MaBalle extends Thread {		
	
	/* propriétés de la balle */
	public float ballRadius = 120; // rayon
	public float ballX = ballRadius + (int) (Math.random()*10000 % 800); // abscisse
	public float ballY = ballRadius + (int) (Math.random()*10000 % 800); // ordonnée
	public float ballSpeedX = 6;  // vitesse en abscisse
	public float ballSpeedY = 4;  // vitesse en ordonnée
	
	/* comptage des collisions */
	public int cpt_collisions = 0;
	
	/* nom de l'élément */
	public String nom = "";
	
	/* panneau dans lequel la balle est contenue */
	BouncingBall1Balle b;
	
	/* constructeur */
	public MaBalle(BouncingBall1Balle b, String nom) {
		this.b = b;
		this.nom = nom;
	}
	
	public void run() {
		/* boucle infinie de mise à jour des coordonnées de la balle */
		while (true) {
			// calcul de la nouvelle position
			ballX += ballSpeedX;
			ballY += ballSpeedY;
			
			// vérification si les coordonnées sont toujours dans la fenêtre
			verifieCoordonnees();
			
			// rafraîchissement de l'affichage
			this.b.repaint();
			
			// attente de quelques millisecondes avant de continuer le déplacement
			try 
			{
				Thread.sleep(1000 / BouncingBall1Balle.UPDATE_RATE);
			} catch (InterruptedException ex) 
			{ 
				System.out.println("Problème d'exécution. Arrêt du programme.");
				System.exit(0);
			}
		}
    }
    
    public void verifieCoordonnees()
    {
		if (this.ballX - this.ballRadius < 0)
		{
			this.ballSpeedX = -this.ballSpeedX;
			this.ballX = this.ballRadius;
		}
		else if (this.ballX + this.ballRadius > BouncingBall1Balle.BOX_WIDTH) 
		{
			this.ballSpeedX = -this.ballSpeedX;
			this.ballX = BouncingBall1Balle.BOX_WIDTH - this.ballRadius;
		}
		
		if (this.ballY - this.ballRadius < 0)
		{
			this.ballSpeedY = -this.ballSpeedY;
			this.ballY = this.ballRadius;
		} 
		else if (this.ballY + this.ballRadius > BouncingBall1Balle.BOX_HEIGHT)
		{
			this.ballSpeedY = -this.ballSpeedY;
			this.ballY = BouncingBall1Balle.BOX_HEIGHT - this.ballRadius;
		}
	}
}
