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
		
		Element plat = new Element("Frites");
		racine.addContent(plat);
		
		Attribute classe = new Attribute ("classe", "gras");
		plat.setAttribute(classe);
		
		Element accompagnement = new Element ("accompagnement");
		accompagnement.setText("saucisses");
		plat.addContent(accompagnement);
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

	