package hu.domparse.NWTTCA;


import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyNWTTCA {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		// TODO Auto-generated method stub

		//XML fájl meghívása
		File xmlFile = new File("XMLNWTTCA.xml");
		
		//Document builder definiálása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//Fájl betöltése a DocumentBuilderbe
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//A 302-es id-val rendelkezõ horgász felszerelését feederre változtatjuk
		NodeList nodes = doc.getElementsByTagName("Horgasz");
		for(int i=0;i<nodes.getLength();i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				if(node.getAttributes().getNamedItem("HorgaszId").getTextContent().equals("302")) {
					NodeList childNodes = node.getChildNodes();
					for(int j=0;j<childNodes.getLength();j++) {
						Node childNode = childNodes.item(j);
						if(childNode.getNodeName().equals("Felszereles")) {
							childNode.setTextContent("feeder");
						}
						
					}
				}
			}
		}
		
		//Újabb horgászt adunk hozzá
		Element Horgasz = (Element)doc.getElementsByTagName("Horgaszok").item(0);
		Horgasz.appendChild(createHorgasz(doc,"304", "feeder", "ideiglenes horgász engedély", "Balla Bence", "4", "5000" ));
		
		//Újabb szektort adunk hozzá
		Element Szektor = (Element)doc.getElementsByTagName("Szektorok").item(0);
		Szektor.appendChild(createSzektor(doc,"197", "12", "7", "Észak", "Dél", "147"));
		
		//Módosítjuk a 196-os id-val rendelkezõ szektort 365-re
		modifyId(doc, "Szektor", "SzektorId", "196", " 365");
		
		//Módosítjuk a 134-es id-val rendelkezõ szervezõ id-ját 366ra
		modifyId(doc, "Szervezo", "SzervezoId", "134", "366");
		
		//Lementjük a módosított dokumentumot
		SaveAsDoc(doc, "teszt.xml");
	}
	
	//Lementjük a módosított xml dokumentumot
	public static void SaveAsDoc(Document doc, String filename) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/.xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File(filename);
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
	}
	
	//Horgászt hozzunk létre
	private static Node createHorgasz(Document doc, String HorgaszId, String Felszereles, String Engedely, String Nev, String Sorszam, String Nevezesidij) {
		
		Element user = doc.createElement("Horgasz");
		
		user.setAttribute("HorgaszId", HorgaszId);
		user.appendChild(createElement(doc, "Felszereles", Felszereles));
		user.appendChild(createElement(doc, "Engedely", Engedely));
		user.appendChild(createElement(doc, "Nev", Nev));
		user.appendChild(createElement(doc, "Sorszam", Sorszam));
		user.appendChild(createElement(doc, "Nevezesidij", Nevezesidij));
		
		return user;
	}
	
	
	//Szektort hozzunk létre
	private static Node createSzektor(Document doc, String SzektorId, String Kapacitas, String Sorszam, String Elhelyezkedes, String Szelirany, String VersenyId_FK) {
		
		Element user = doc.createElement("Szektor");
		
		user.setAttribute("SzektorId", SzektorId);
		user.appendChild(createElement(doc, "Kapacitas", Kapacitas));
		user.appendChild(createElement(doc, "Sorszam", Sorszam));
		user.appendChild(createElement(doc, "Elhelyezkedes", Elhelyezkedes));
		user.appendChild(createElement(doc, "Szelirany", Szelirany));
		user.appendChild(createElement(doc, "VersenyId_FK", VersenyId_FK));

		
		return user;
	}
	
	//Elementet hozzunk létre
	private static Node createElement(Document doc, String name, String value) {
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
	//Módosítjuk az id-t
	
	public static void modifyId(Document doc, String tagName, String attrName, String oldData, String newData) {

		NodeList nodes = doc.getElementsByTagName(tagName);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getAttributes().getNamedItem(attrName).getTextContent().equals(oldData)) {
					node.getAttributes().getNamedItem(attrName).setTextContent(newData);
				}
			}
		}
	}

}
