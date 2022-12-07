package hu.domparse.NWTTCA;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryNWTTCA {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		
		//XML f�jl megh�v�sa
		File xmlFile = new File("XMLNWTTCA.xml");
		
		//Document builder defini�l�sa
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//F�jl bet�lt�se a documentum builderbe
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//Horg�szok kilist�z�sa
		
		System.out.println("");
		System.out.println("Horgaszok kilistazasa: ");
		System.out.println("");
		
		NodeList horgaszList = doc.getElementsByTagName("Horgasz");
		for(int i=0;i<horgaszList.getLength();i++) {
			Node nNode = horgaszList.item(i);
			printHorgasz(nNode);
		}
		
		//Feeder botot haszn�l� horg�szok kilist�z�sa
		System.out.println("");
		System.out.println("Feeder botos horgaszok: ");
		System.out.println("");
		
		NodeList feederList = doc.getElementsByTagName("Horgasz");
		for(int i=0;i<feederList.getLength();i++) {
			Node nNode = feederList.item(i);
			printHorgasz(nNode, "feeder");
		}
		
		// 5000ftos nevezesidijasok kilist�z�sa
		
		System.out.println("");
		System.out.println("5000ftos nevezesi dijas horgaszok kilistazasa");
		System.out.println("");
		
		NodeList otezerList = doc.getElementsByTagName("Horgasz");
		for(int i=0;i<otezerList.getLength();i++) {
			Node nNode = otezerList.item(i);
			printOtezer(nNode, "5000");
		}
		
		//Szervezok kilist�z�sa m�sik megold�ssal
		
		NodeList szervezoList =doc.getElementsByTagName("Szervezo");
		
		for(int i=0;i<szervezoList.getLength();i++) {
			Node nNode = szervezoList.item(i);
			System.out.println("");
			printSzervezo(nNode);
		}
		
		//Szektorok kilist�z�sa
		
		System.out.println("");
		System.out.println("Szektorok kilist�z�sa");
		
		NodeList eszakList = doc.getElementsByTagName("Szektorok");
		for(int i=0;i<otezerList.getLength();i++) {
			Node nNode = otezerList.item(i);
			System.out.println("");
			printSzektor(nNode);
		}
		
	}
	
	//Horg�szok kilist�z�sa
	private static void printHorgasz(Node nNode) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String HorgaszId = elem.getAttribute("HorgaszId");
			
			Node nNode1 = elem.getElementsByTagName("Felszereles").item(0);
			String Felszereles = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("Engedely").item(0);
			String Engedely = nNode2.getTextContent();
			
			Node nNode3 = elem.getElementsByTagName("Nev").item(0);
			String Nev = nNode3.getTextContent();
			
			Node nNode4 = elem.getElementsByTagName("Sorszam").item(0);
			String Sorszam = nNode4.getTextContent();
			
			Node nNode5 = elem.getElementsByTagName("Nevezesidij").item(0);
			String Nevezesidij = nNode5.getTextContent();
			
			
			System.out.printf("HorgaszID: %s%n", HorgaszId);
			System.out.printf("Felszereles: %s%n", Felszereles);
			System.out.printf("Engedely %s%n", Engedely);
			System.out.printf("Nev %s%n", Nev);
			System.out.printf("Sorszam %s%n", Sorszam);
			System.out.printf("Nevezesidij %s%n", Nevezesidij);
			System.out.println("");
		}
	}

	//Feeder botot haszn�l� horg�szok kilist�z�sa
	private static void printHorgasz(Node nNode, String Felszereles) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String HorgaszId = elem.getAttribute("HorgaszId");
			
			Node nNode1 = elem.getElementsByTagName("Felszereles").item(0);
			String Felszereles2 = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("Engedely").item(0);
			String Engedely = nNode2.getTextContent();
			
			Node nNode3 = elem.getElementsByTagName("Nev").item(0);
			String Nev = nNode3.getTextContent();
			
			Node nNode4 = elem.getElementsByTagName("Sorszam").item(0);
			String Sorszam = nNode4.getTextContent();
			
			Node nNode5 = elem.getElementsByTagName("Nevezesidij").item(0);
			String Nevezesidij = nNode5.getTextContent();
			
			if(Felszereles2.equals(Felszereles)) {
				System.out.printf("HorgaszID: %s%n", HorgaszId);
				System.out.printf("Felszereles: %s%n", Felszereles);
				System.out.printf("Engedely %s%n", Engedely);
				System.out.printf("Nev %s%n", Nev);
				System.out.printf("Sorszam %s%n", Sorszam);
				System.out.printf("Nevezesidij %s%n", Nevezesidij);
				System.out.println("");
			}
		}
	}
	
	// 5000ftos nevezesidijasok kilist�z�sa
	private static void printOtezer(Node nNode, String Nevezesidij) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String HorgaszId = elem.getAttribute("HorgaszId");
			
			Node nNode1 = elem.getElementsByTagName("Felszereles").item(0);
			String Felszereles = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("Engedely").item(0);
			String Engedely = nNode2.getTextContent();
			
			Node nNode3 = elem.getElementsByTagName("Nev").item(0);
			String Nev = nNode3.getTextContent();
			
			Node nNode4 = elem.getElementsByTagName("Sorszam").item(0);
			String Sorszam = nNode4.getTextContent();
			
			Node nNode5 = elem.getElementsByTagName("Nevezesidij").item(0);
			String Nevezesidij2 = nNode5.getTextContent();
			
			if(Nevezesidij2.equals(Nevezesidij)) {
				System.out.printf("HorgaszID: %s%n", HorgaszId);
				System.out.printf("Felszereles: %s%n", Felszereles);
				System.out.printf("Engedely %s%n", Engedely);
				System.out.printf("Nev %s%n", Nev);
				System.out.printf("Sorszam %s%n", Sorszam);
				System.out.printf("Nevezesidij %s%n", Nevezesidij);
				System.out.println("");
			}
		}
	}
	
	//Szervezok kilist�z�sa m�sik megold�ssal
	private static void printSzervezo(Node nNode) {
		if(nNode.getNodeType()==Node.ELEMENT_NODE) {
			Element element =(Element) nNode;
			NodeList nList = element.getChildNodes();
			for (int j = 0; j < nList.getLength(); j++) {
                Node node2 = nList.item(j);
                if (node2.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node2;
                    System.out.println(node2.getNodeName()+" : "+node2.getTextContent());
                }
			}
		}	
	}
	
	//Szektorok kilist�z�sa
	private static void printSzektor(Node nNode) {
		if(nNode.getNodeType()==Node.ELEMENT_NODE) {
			Element element =(Element) nNode;
			NodeList nList = element.getChildNodes();
			for (int j = 0; j < nList.getLength(); j++) {
                Node node2 = nList.item(j);
                if (node2.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node2;
                    System.out.println(node2.getNodeName()+" : "+node2.getTextContent());
                }
			}
		}	
	}
}
