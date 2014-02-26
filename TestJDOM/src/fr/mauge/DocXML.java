package fr.mauge;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.jdom2.*;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.transform.JDOMResult;
import org.jdom2.transform.JDOMSource;


public class DocXML {

	private static Document document;
	private static Element racine;
	
	private static Element plat ;
				
	private static Element platPat ;
	
	private static Element platFrtSal;
	
	public static void main(String[] args){
		DocXML.creerXML();
		/*DocXML.supprElement("Sauce");*/
		DocXML.affiche();
		/*DocXML.affichageMenu();
		DocXML.affichageFiltre(plat);*/
		DocXML.enregistre("Fichier XML");
		DocXML.deXMLLaJDOM("Fichier XML");
		DocXML.transformXML(document, "resulta.xsl");
		
	}

	
	public static void creerXML() {
		
		racine = new Element("menu");
		
		document = new Document(racine);
		
		/*Création des aliments*/
		/*Element*/ plat = new Element("Frites");
		racine.addContent(plat);
				
		/*Element*/ platPat = new Element("Patates");
		racine.addContent(platPat);
		
		/*Element*/ platFrtSal = new Element("Frites");
		racine.addContent(platFrtSal);
		
		/*Type de classe des aliments*/
		Attribute classeFrtGrs = new Attribute ("classe", "salé");
		plat.setAttribute(classeFrtGrs);
		
		Attribute classeFrtSal = new Attribute ("classe", "gras");
		platPat.setAttribute(classeFrtSal);
		
		Attribute classePatGrs = new Attribute ("classe", "salé");
		platFrtSal.setAttribute(classePatGrs);
		
		/*Accompagnement*/
		Element accompagnement = new Element ("accompagnement");
		accompagnement.setText("saucisses");
		plat.addContent(accompagnement);
		
		Element accompagnementFrtSal = new Element ("accompagnement");
		accompagnementFrtSal.setText("Carbonade");
		platFrtSal.addContent(accompagnementFrtSal);
		
		Element accompagnementPatates = new Element ("accompagnement");
		accompagnementPatates.setText("Moules");
		platPat.addContent(accompagnementPatates);
		
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
	
	public static void affichageMenu(){
		racine = document.getRootElement();
		
		List<Element> listePlats = (List<Element>) racine.getChildren("Frites");
		
		Iterator<Element> i= listePlats.iterator();
		
		while(i.hasNext()) {
			Element courant = i.next();
			/*System.out.println(courant.getChild("accompagnement").getText());*/
		}
		
	}	
	public static void affichageFiltre(Element element){
			
		List<Element> resultat = element.getContent(Filters.element("Sauce"));
		
		for (Element elem : resultat){
			if(elem.getText().equals("Bière")) {
				System.out.println(elem.getText()+" "+ element.getAttributeValue("classe"));
			}
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
	public static void supprElement(String elementASupprimer){
	racine = document.getRootElement();
	
	List<Element> listePlats = (List<Element>) racine.getChildren("Frites");
	
	Iterator<Element> i= listePlats.iterator();
	
	while(i.hasNext()) {
		Element courant = i.next();

		if(courant.getChild(elementASupprimer)!= null){ 
			
			courant.removeChild(elementASupprimer);
			
			courant.setName("plat_modifié");
		}
	}
	}

	public static void transformXML(Document documentJDOMEntree, String fichierXSL){
		JDOMResult documentJDOMSortie = new JDOMResult();
		
		Document resultat = null;
		try{
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(fichierXSL));
			transformer.transform(new JDOMSource(documentJDOMEntree), documentJDOMSortie);
			document = documentJDOMSortie.getDocument();
			enregistre("resultat.xml");
		}
		catch(Exception e){
		}
		}
}
	