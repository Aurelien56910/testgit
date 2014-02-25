package fr.mauge;

import java.io.File;
import java.io.FileOutputStream;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class DocXML {

	private static Document document;
	private static Element racine;
	
	public static void creerXML() {
		
		racine = new Element("menu");
		
		document = new Document(racine);
		
		/*Création des aliments*/
		Element plat = new Element("Frites");
		racine.addContent(plat);
				
		Element platPat = new Element("Patates");
		racine.addContent(platPat);
		
		Element platFrtSal = new Element("Frites");
		racine.addContent(platFrtSal);
		
		/*Type de classe des aliments*/
		Attribute classeFrtGrs = new Attribute ("classe", "gras");
		plat.setAttribute(classeFrtGrs);
		
		Attribute classeFrtSal = new Attribute ("classe", "salé");
		platFrtSal.setAttribute(classeFrtSal);
		
		Attribute classePatGrs = new Attribute ("classe", "gras");
		platPat.setAttribute(classePatGrs);
		
		/*Accompagnement*/
		Element accompagnement = new Element ("accompagnement");
		accompagnement.setText("saucisses");
		plat.addContent(accompagnement);
		
		Element accompagnementPatates = new Element ("accompagnement");
		accompagnementPatates.setText("Carbonade");
		platPat.addContent(accompagnementPatates);
		
		Element accompagnementFrtSal = new Element ("accompagnement");
		accompagnementFrtSal.setText("Moules");
		platFrtSal.addContent(accompagnementFrtSal);
		
		/*Sauces*/
		Element Sauce = new Element ("Sauce");
		Sauce.setText("Bière");
		plat.addContent(Sauce);
		
		Element SaucePatates = new Element ("Sauce");
		SaucePatates.setText("Bière");
		platPat.addContent(SaucePatates);
		
		Element SauceFrtSal = new Element ("Sauce");
		SauceFrtSal.setText("Roquefort");
		platFrtSal.addContent(SauceFrtSal);
		
	}
		
	public static void affiche(){
		try{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			
			sortie.output(document, System.out);
		}
		catch (java.io.IOException e){
		}
	}
	
	public static void enregistre(String fichier)
	{
		try{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document,  new FileOutputStream(fichier));
		
		}
		catch (java.io.IOException e){
		}
	}
	
	public static void deXMLLaJDOM(String fichier){
		
		SAXBuilder sxb = new SAXBuilder();
		
		try{
			document = sxb.build(new File(fichier));
		
		}
		catch (Exception e){
			System.out.println("pb de parsing : " + e);
		}
	
	}
}

	