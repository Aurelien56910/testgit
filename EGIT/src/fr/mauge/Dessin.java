package fr.mauge;


/**
 * Dessin.java
 * modifié:  10/09/2013
 * 
 * @author E Rigaud
 * @version 1.0
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Definit le contenu de la fenêtre de l'application d'animation des BallonRondSauteur. Une zone
 * de dessin est un JPanel qui gère un liste d'objets BallonRondSauteur. Lorsqu'il se réaffiche
 * l'objet Dessin redessine les différents objets BallonRondSauteur contenus dans cette liste.
 *
 */
public class Dessin extends JPanel {
    /**
     * stocke la liste des BallonRondSauteur ayant été ajoutés à cette zone de
     * dessin.
     */
    private List<BallonRondSauteur> listeDesBallons = new ArrayList<BallonRondSauteur>();
    
    /**
     * retourne la largeur de la zone de dessin.
     * @return la largeur.
     */
    public int getLargeur() {
        return getWidth();
    }
    
    /**
     * retourne la hauteur de la zone de dessin.
     * @return la hauteur.
     */
    public int getHauteur() {
        return getHeight();
    }
    
    /**
     * ajoute un BallonRondSauteur à la zone de dessin.
     * @param unBallon le  BallonRondSauteur à ajouter au Dessin
     * @see BallonRondSauteur
     */
    public void ajouterObjet(BallonRondSauteur unBallon) {
        
        if (! listeDesBallons.contains(unBallon)) {
            // l'objet n'est pas déjà dans la liste
            // on le rajoute a la liste des objets du dessin
            listeDesBallons.add(unBallon);
			// fixe le dessin associé au ballon unBallon : c'est CE dessin
            unBallon.setDessin(this);
            // le dessin se réaffiche
            repaint();
        }
    }
    
    /**
     * temporisation de l'animation.
     * @param duree delai de temporisation en ms.
     */
    public void pause(int duree) {
        try {
            Thread.sleep(duree);
        } catch (Exception e) {}
    }
    
    /**
     * affiche la zone de dessin et son contenu
     * @param g le contexte graphique
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dessinerLesBallons(g);
    }
    
    /**
     * Parcourt la liste des ballons pour afficher chacun d'eux.
     * @param g le contexte graphique
     */
    private void dessinerLesBallons(Graphics g) {
        
        
        for ( BallonRondSauteur unBallon: listeDesBallons) {
        	unBallon.dessiner(g);
        }
    }
    
} // Dessin
