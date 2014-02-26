package fr.mauge;

/**
 * BallonRondSauteur.java
 * modifi�:  10/09/2013
 * 
 * @author E Rigaud
 * @version 1.0
 */

import java.awt.Graphics;


/**
 * Cette classe permet de mod�liser un ballon de forme ronde  (ou ovale).
 * <p>
 * Le rep�re graphique est d�fini avec son origine en haut � gauche de la z�ne
 * de dessin, l'axe des x : horizontal vers la droite
 * et l'axe des y : vertical vers le bas.
 * <p>
 * Un ballon est d�fini par :
 * <ul>
 * <li> les coordonn�es xhg, yhg du coin sup�rieur gauche du rectangle
 * englobant,</li>
 * <li> une largeur et une hauteur,</li>
 * <li> un d�placement �l�mentaire horizontal (dx) et un d�placement �l�mentaire
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
	 * La z�ne de dessin dans laquelle sera dessin� le BallonRondSauteur.
	 */
	private Dessin d;

	/**
	 * abscisse coin sup�rieur gauche du rectangle englobant le ballon.
	 */
	private int xhg = 0;

	/**
	 * ordonn�e coin sup�rieur gauche du rectangle englobant le ballon.
	 */
	private int yhg = 0;

	/**
	 * largeur du ballon. Par d�faut 50 pixels.
	 */
	private int largeur = 50;

	/**
	 * hauteur du ballon. Par d�faut 50 pixels.
	 */
	private int hauteur = 50;

	/**
	 * d�placement �l�mentaire horizontal du ballon. Par d�faut 5 pixels.
	 */
	private int dx = 5;

	/**
	 * deplacement �l�mentaire vertical du ballon. Par d�faut 5 pixels.
	 */
	private int dy = 5;

	// ---------------------------------------------------------
	// Les constructeurs de la classe BallonRondSauteur
	// ---------------------------------------------------------

	/**
	 * Constructeur avec valeurs par d�faut. Cr�e un ballon de taille 50x50 dont
	 * le coin sup�rieur gauche du rectangle englobant est situ� au point (0,0)
	 * de la z�ne de dessin. Ce ballon est �galement dot� d'un d�placement
	 * �l�mentaire horizontal et vertical de +5 pixels.
	 */
	public BallonRondSauteur() {
	}

	/**
	 * Constructeur avec positionnement du ballon. Cr�e un ballon de taille
	 * 50x50 mais dont la position du coin sup�rieur gauche du rectangle
	 * englobant est fix�e � la cr�ation. Ce ballon est dot� d'un d�placement
	 * �l�mentaire horizontal et vertical de +5 pixels.
	 * 
	 * @param xg
	 *            abscisse du coin sup�rieur gauche du rectangle englobant.
	 * @param yg
	 *            ordonn�e du coin sup�rieur gauche du rectangle englobant.
	 */
	public BallonRondSauteur(int xg, int yg) {
		xhg = xg;
		yhg = yg;

	}

	/**
	 * Constructeur avec positionnement du ballon et d�finition de sa taille.
	 * Cr�e un ballon dont les diemensions et la position du coin sup�rieur
	 * gauche du rectangle englobant sont fix�es � la cr�ation. Ce ballon est
	 * dot� d'un d�placement �l�mentaire horizontal et vertical de +5 pixels.
	 * 
	 * @param xg
	 *            abscisse du coin sup�rieur gauche du rectangle englobant.
	 * @param yg
	 *            abscisse du coin sup�rieur gauche du rectangle englobant.
	 * @param larg
	 *            largeur du ballon. La largeur doit �tre sup�rieure �
	 *            LARGEUR_MIN
	 * @param haut
	 *            hauteur du ballon. La hauteur doit �tre sup�rieure �
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
	 * Donne la valeur du d�placement �l�mentaire horizontal.
	 * 
	 * @return valeur de dx, d�placement �l�mentaire horizontal.
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * Fixe d�placement �l�mentaire horizontal.
	 * 
	 * @param v
	 *            Valeur � affecter � dx, d�placement �l�mentaire horizontal.
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
	 * Inverse sens du d�placement horizontal.
	 */
	private void inverserDx() {
		dx = -dx;
	}

	/**
	 * Inverse sens du d�placement vertical.
	 */
	private void inverserDy() {
		dy = -dy;
	}

	/**
	 * Inverse sens des d�placements horizontal et vertical.
	 */
	private void inverserDxEtDy() {
		this.inverserDx();
		this.inverserDy();
	}

	/**
	 * Fait effectuer au ballon un d�placement �lementaire. La position du coin
	 * sup�rieur gauche du ballon est modifi�e en lui ajoutant le d�placement
	 * �l�mentaire d�fini par dx et dy.
	 */
	private void bouger() {
		xhg += dx;
		yhg += dy;
	}
	
	/**
	 * Fait effectuer au ballon un d�placement �lementaire. La position du coin
	 * sup�rieur gauche du ballon est modifi�e en lui ajoutant le d�placement
	 * �l�mentaire d�fini par dx et dy. Si le ballon d�passe l'un des bords
	 * de la zone de dessin il inverse sa direction de d�placement.
	 */
	public void deplacerSansRebond() {
		if (bordDroitAtteint() || bordGaucheAtteint())
			inverserDx();
		if (bordHautAtteint()	|| bordBasAtteint())
			inverserDy();
		bouger();
	}


	/**
	 * Fait effectuer au ballon un d�placement �lementaire. La position du coin
	 * sup�rieur gauche du ballon est modifi�e en lui ajoutant le d�placement
	 * �l�mentaire d�fini par dx et dy. Si le ballon d�passe l'un des bords
	 * de la zone de dessin il inverse sa direction de d�placement.
	 */
	public void deplacerAvecRebond() {
		
	}

	/**
	 * Evalue si le ballon atteint le bord gauche de la z�ne de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le ballon
	 *         intersecte le cot� gauche de la z�ne de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordGaucheAtteint() {
		return (xhg < 0);
	}

	/**
	 * Evalue si le ballon atteint le bord droit de la z�ne de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le ballon
	 *         intersecte le cot� droit de la z�ne de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordDroitAtteint() {
		return ((xhg + largeur) > d.getLargeur());
	}

	/**
	 * Evalue si le ballon atteint le bord haut de la z�ne de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le ballon
	 *         intersecte le cot� haut de la z�ne de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordHautAtteint() {
		return (yhg < 0);
	}

	/**
	 * Evalue si le ballon atteint le bord bas de la z�ne de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le ballon
	 *         intersecte le cot� bas de la z�ne de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordBasAtteint() {
		return ((yhg + hauteur) >= d.getHauteur());
	}

	/**
	 * Evalue si le ballon atteint l'un des bords de la z�ne de dessin.
	 * 
	 * @return <code>true</code> si le rectangle englobant le ballon
	 *         intersecte l'un des cot�s de la z�ne de dessin, <code>
	 *         false</code>
	 *         sinon.
	 */
	public boolean bordAtteint() {
		return bordDroitAtteint() || bordGaucheAtteint() || bordHautAtteint()
				|| bordBasAtteint();
	}

	/**
	 * fixe la z�ne de dessin dans laquelle le ballon est affich�.
	 * 
	 * @param d
	 *            r�f�rence de la z�ne de dessin associ�e au ballon
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
	 *            le contexte graphique de la z�ne de dessin en charge de
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
	
