package fr.mauge;

/**
 *
 * AppliBallon
 * 
 *
 * modifié:  10/09/2013
 * 
 * @author E Rigaud
 * @version 1.0
 */

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JFrame;

/**
 * Animation d'un ballon dans une fenêtre graphique. Un ballon est dessiné à
 * l'intérieur d'une fenêtre et se déplace. Chaque fois que l'un des bords est
 * atteint, le ballon change de direction.
 * 
 */

public class CopyOfAppliBallon extends JFrame implements MouseMotionListener{
	//donne l'etat de l'application
	private boolean cestfini;
	
	/**
	 * Construit une fenêtre avec une barre de titre
	 * @param nom Titre de la fenêtre
	 */
	public CopyOfAppliBallon (String nom){
		super(nom);
		cestfini = false;
	}
	
	public static void main(String[] args) {

		// la fenêtre graphique
		CopyOfAppliBallon laFenetre = new CopyOfAppliBallon	("Ballon animé");
		laFenetre.addMouseMotionListener(laFenetre);

		laFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//place la fenêtre aux coord. du point de l'écran
		laFenetre.setLocation(new Point(150, 150));
		//définit la taille de la fenêtre
		laFenetre.setSize(512, 512);

		// créé la zone de dessin et la place dans la fenêtre
		Dessin d = new Dessin();
		laFenetre.add(d);

		// affiche la fenêtre
		laFenetre.setVisible(true);

		// creation d'un objet BallonRondSauteur
		int nb = 4;
		BallonRondSauteur[] leBallon= new BallonRondSauteur[nb];

		// on rajoute cet objet à la zône de dessin
		for(int i = 0; i<nb ; i++){
			leBallon[i] = new BallonRondSauteur( new Random().nextInt(400)  , new Random().nextInt(400));
			d.ajouterObjet(leBallon[i]);
		}

		// la boucle d'animation
		// c'est une boucle infinie, le programme se ferme lorsque la souris
		//  passe sur la fenêtre ou lors d'un clic dans la case de fermeture.
		while ( ! laFenetre.cestfini) {
			for (int i = 0; i < nb; i++){
				leBallon[i].deplacerSansRebond();
			}

			// la zone de dessin se réaffiche
			d.repaint();

			// pause en ms pour voir le deplacement
			d.pause(200);
		}
		//System.exit(0);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		cestfini = true;
	}

} // AppliBallon
