package fr.mauge;

public class affichageXML {
	public static void main(String[] args){
		DocXML.creerXML();
		DocXML.affiche();
		DocXML.enregistre("Fichier XML");
		DocXML.deXMLLaJDOM("Fichier JDOM");
	}
}