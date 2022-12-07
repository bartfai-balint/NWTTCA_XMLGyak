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

		//XML f�jl megh�v�sa
		File xmlFile = new File("XMLNWTTCA.xml");
		
		//Document builder defini�l�sa
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//F�jl bet�lt�se a DocumentBuilderbe
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//A 302-es id-val rendelkez� horg�sz felszerel�s�t feederre v�ltoztatjuk
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
		
		//�jabb horg�szt adunk hozz�
		Element Horgasz = (Element)doc.getElementsByTagName("Horgaszok").item(0);
		Horgasz.appendChild(createHorgasz(doc,"304", "feeder", "ideiglenes horg�sz enged�ly", "Balla Bence", "4", "5000" ));
		
		//�jabb szektort adunk hozz�
		Element Szektor = (Element)doc.getElementsByTagName("Szektorok").item(0);
		Szektor.appendChild(createSzektor(doc,"197", "12", "7", "�szak", "D�l", "147"));
		
		//M�dos�tjuk a 196-os id-val rendelkez� szektort 365-re
		modifyId(doc, "Szektor", "SzektorId", "196", " 365");
		
		//M�dos�tjuk a 134-es id-val rendelkez� szervez� id-j�t 366ra
		modifyId(doc, "Szervezo", "SzervezoId", "134", "366");
		
		//Lementj�k a m�dos�tott dokumentumot
		SaveAsDoc(doc, "teszt.xml");
	}
	
	//Lementj�k a m�dos�tott xml dokumentumot
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
	
	//Horg�szt hozzunk l�tre
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
	
	
	//Szektort hozzunk l�tre
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
	
	//Elementet hozzunk l�tre
	private static Node createElement(Document doc, String name, String value) {
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
	//M�dos�tjuk az id-t
	
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
