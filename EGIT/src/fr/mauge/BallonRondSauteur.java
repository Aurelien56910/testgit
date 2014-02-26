package fr.mauge;

/**
 * BallonRondSauteur.java
 * modifié:  10/09/2013
 * 
 * @author E Rigaud
 * @version 1.0
 */

import java.awt.Graphics;


/**
 * Cette classe permet de modéliser un ballon de forme ronde  (ou ovale).
 * <p>
 * Le repère graphique est défini avec son origine en haut à gauche de la zône
 * de dessin, l'axe des x : horizontal vers la droite
 * et l'axe des y : vertical vers le bas.
 * <p>
 * Un ballon est défini par :
 * <ul>
 * <li> les coordonnées xhg, yhg du coin supérieur gauche du rectangle
 * englobant,</li>
 * <li> une largeur et une hauteur,</li>
 * <li> un déplacement élémentaire horizontal (dx) et un déplacement élémentaire
 * vertical (dy).</li>
 * </ul>
 *
 * <center><img src="../../../ballonrondsauteurdroit.png" height=100 width=100 align=middle></center>
 *  
 */

public class BallonRondSauteur {

	// ---------------------------------------------------------
	// Les constantes de la classe BallonRondSauteur
	// ---------------------------------------------------------

	/**
	 * Largeur minimale pour un BallonRondSauteur.
	 */
	public static final int LARGEUR_MIN = 15;

	/**
	 * Hauteur minimale pour un BallonRondSauteur.
	 */
	public static final int HAUTEUR_MIN = 15;

	// -------------------------------------------------------------
	// Les attributs (variables d'instance) de la classe BallonRondSauteur
	// -------------------------------------------------------------

	/**
	 * La zône de dessin dans laquelle sera dessiné le BallonRondSauteur.
	 */
	private Dessin d;

	/**
	 * abscisse coin supérieur gauche du rectangle englobant le ballon.
	 */
	private int xhg = 0;

	/**
	 * ordonnée coin supérieur gauche du rectangle englobant le ballon.
	 */
	private int yhg = 0;

	/**
	 * largeur du ballon. Par défaut 50 pixels.
	 */
	private int largeur = 50;

	/**
	 * hauteur du ballon. Par défaut 50 pixels.
	 */
	private int hauteur = 50;

	/**
	 * déplacement élémentaire horizontal du ballon. Par défaut 5 pixels.
	 */
	private int dx = 5;

	/**
	 * deplacement élémentaire vertical du ballon. Par défaut 5 pixels.
	 */
	private int dy = 5;

	// ---------------------------------------------------------
	// Les constructeurs de la classe BallonRondSauteur
	// ---------------------------------------------------------

	/**
	 * Constructeur avec valeurs par défaut. Crée un ballon de taille 50x50 dont
	 * le coin supérieur gauche du rectangle englobant est situé au point (0,0)
	 * de la zône de dessin. Ce ballon est également doté d'un déplacement
	 * élémentaire horizontal et vertical de +5 pixels.
	 */
	public BallonRondSauteur() {
	}

	/**
	 * Constructeur avec positionnement du ballon. Crée un ballon de taille
	 * 50x50 mais dont la position du coin supérieur gauche du rectangle
	 * englobant est fixée à la création. Ce ballon est doté d'un déplacement
	 * élémentaire horizontal et vertical de +5 pixels.
	 * 
	 * @param xg
	 *            abscisse du coin supérieur gauche du rectangle englobant.
	 * @param yg
	 *            ordonnée du coin supérieur gauche du rectangle englobant.
	 */
	public BallonRondSauteur(int xg, int yg) {
		xhg = xg;
		yhg = yg;

	}

	/**
	 * Constructeur avec positionnement du ballon et définition de sa taille.
	 * Crée un ballon dont les diemensions et la position du coin supérieur
	 * gauche du rectangle englobant sont fixées à la création. Ce ballon est
	 * doté d'un déplacement élémentaire horizontal et vertical de +5 pixels.
	 * 
	 * @param xg
	 *            abscisse du coin supérieur gauche du rectangle englobant.
	 * @param yg
	 *            abscisse du coin supérieur gauche du rectangle englobant.
	 * @param larg
	 *            largeur du ballon. La largeur doit être supérieure à
	 *            LARGEUR_MIN
	 * @param haut
	 *            hauteur du ballon. La hauteur doit être supérieure à
	 *            HAUTEUR_MIN
	 * 
	 * @see BallonRondSauteur#LARGEUR_MIN
	 * @see BallonRondSauteur#HAUTEUR_MIN
	 */
	public BallonRondSauteur(int xg, int yg, int larg, int haut) {
		xhg = xg;
		yhg = yg;

		largeur = Math.max(larg, LARGEUR_MIN);
		hauteur = Math.max(haut, HAUTEUR_MIN);
	}

	/**
	 * Donne la valeur du déplacement élémentaire horizontal.
	 * 
	 * @return valeur de dx, déplacement élémentaire horizontal.
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * Fixe déplacement élémentaire horizontal.
	 * 
	 * @param v
	 *            Valeur à affecter à dx, déplacement élémentaire horizontal.
	 */
	public void setDx(int v) {
		this.dx = v;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int v) {
		this.dy = v;
	}

	/**
	 * Inverse sens du déplacement horizontal.
	 */
	private void inverserDx() {
		dx = -dx;
	}

	/**
	 * Inverse sens du déplacement vertical.
	 */
	private void inverserDy() {
		dy = -dy;
	}

	/**
	 * Inverse sens des déplacements horizontal et vertical.
	 */
	private void inverserDxEtDy() {
		this.inverserDx();
		this.inverserDy();
	}

	/**
	 * Fait effectuer au ballon un déplacement élementaire. La position du coin
	 * supérieur gauche du ballon est modifiée en lui ajoutant le déplacement
	 * élémentaire défini par dx et dy.
	 */
	private void bouger() {
		xhg += dx;
		yhg += dy;
	}
	
	/**
	 * Fait effectuer au ballon un déplacement élementaire. La position du coin
	 * supérieur gauche du ballon est modifiée en lui ajoutant le déplacement
	 * élémentaire défini par dx et dy. Si le ballon dépasse l'un des bords
	 * de la zone de dessin il inverse sa direction de déplacement.
	 */
	public void deplacerSansRebond() {
		if (bordDroitAtteint() || bordGaucheAtteint())
			inverserDx();
		if (bordHautAtteint()	|| bordBasAtteint())
			inverserDy();
		bouger();
	}


	/**
	 * Fait effectuer au ballon un déplacement élementaire. La position du coin
	 * supérieur gauche du ballon est modifiée en lui ajoutant le déplacement
	 * élémentaire défini par dx et dy. Si le ballon dépasse l'un des bords
	 * de la zone de dessin il inverse sa direction de déplacement.
	 */
	public void deplacerAvecRebond() {
		
	}

	/**
	 * Evalue si le ballon atteint le bord gauche de la zône de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le ballon
	 *         intersecte le coté gauche de la zône de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordGaucheAtteint() {
		return (xhg < 0);
	}

	/**
	 * Evalue si le ballon atteint le bord droit de la zône de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le ballon
	 *         intersecte le coté droit de la zône de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordDroitAtteint() {
		return ((xhg + largeur) > d.getLargeur());
	}

	/**
	 * Evalue si le ballon atteint le bord haut de la zône de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le ballon
	 *         intersecte le coté haut de la zône de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordHautAtteint() {
		return (yhg < 0);
	}

	/**
	 * Evalue si le ballon atteint le bord bas de la zône de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le ballon
	 *         intersecte le coté bas de la zône de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordBasAtteint() {
		return ((yhg + hauteur) >= d.getHauteur());
	}

	/**
	 * Evalue si le ballon atteint l'un des bords de la zône de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le ballon
	 *         intersecte l'un des cotés de la zône de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordAtteint() {
		return bordDroitAtteint() || bordGaucheAtteint() || bordHautAtteint()
				|| bordBasAtteint();
	}

	/**
	 * fixe la zône de dessin dans laquelle le ballon est affiché.
	 * 
	 * @param d
	 *            référence de la zône de dessin associée au ballon
	 * 
	 * @see Dessin
	 */
	public void setDessin(Dessin d) {
		this.d = d;
	}

	/**
	 * affiche le ballon.
	 * 
	 * @param g
	 *            le contexte graphique de la zône de dessin en charge de
	 *            l'affichage.
	 * 
	 * @see java.awt.Graphics
	 * @see Dessin
	 */
	public void dessiner(Graphics g) {
		// dessiner le contour du ballon
		g.drawOval(xhg, yhg, largeur, hauteur);

		
		// dessiner les yeux
		int largeurOeil = largeur / 5;
		int hauteurOeil = hauteur / 5;
		g.drawOval(xhg + largeurOeil, yhg + hauteurOeil, largeurOeil,
				hauteurOeil);
		g.drawOval(xhg + 3 * largeurOeil, yhg + hauteurOeil, largeurOeil,
				hauteurOeil);
		// dessiner la bouche
		//bouche en arc de cercle
			
		if (bordAtteint()){
			g.drawArc(xhg + largeur / 4, yhg + hauteur / 2  ,25 , 15, 180, 180);
			}
		else {
			g.drawArc(xhg + largeur / 4, yhg + hauteur / 2  ,25 , 20, 0, 180);
			}
		}
	}
 // BallonRondSauteur
	
