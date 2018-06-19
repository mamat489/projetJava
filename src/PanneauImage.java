// À compiler par javac PanneauImage.java
// À exécuter par java PanneauImage <nomImage> , où <nomImage> est le nom de l'image à afficher

import java.awt.Dimension;	// Pour définir une dimension d'élément graphique
import java.awt.Graphics;	// Pour manipuler un contexte de dessin
import java.awt.image.BufferedImage;	// Pour contenir l'image lue d'un fichier
import java.io.File;	// Pour manipuler un fichier
import javax.imageio.ImageIO;	// Pour pouvoir lire une image
import javax.swing.*;	// Pour manipuler tous les composants Swing

class PanneauImage extends JPanel
{
	private BufferedImage image;	// Pour stocker une image

	public PanneauImage(String nomImage) throws Exception
	{
		image = ImageIO.read(new File(nomImage));
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));	// Fixe la taille du panneau à la taille de l'image
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);	// Exécute les actions graphiques héritées
		g.drawImage(image, 0, 0, null);	// Dessine l'image
	}

	public static void main(String[] args)
	{
		if (true)
		{
			JFrame fenetre = new JFrame();	// Fabrique la fenêtre
			fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Quand on ferme la fenêtre, l'application s'arrête
			try
			{
				PanneauImage panneauImage = new PanneauImage("/home/mamat/Documents/Code_java/TP3/ProjetJava/src/bomber.jpg");	// Fabrique l'élément image
				fenetre.getContentPane().add(panneauImage);	// Ajoute l'élément image au contenu de la fenêtre
				fenetre.pack();	// Fixe la taille de la fenêtre relativement à son contenu
				fenetre.setLocationRelativeTo(null);	// Place la fenêtre au milieu de l'écran
				fenetre.setVisible(true);	// Affiche la fenêtre
			}
			catch (Exception e)
			{
				System.out.println("Impossible d'ouvrir l'image");
			}
		}
	}
}
