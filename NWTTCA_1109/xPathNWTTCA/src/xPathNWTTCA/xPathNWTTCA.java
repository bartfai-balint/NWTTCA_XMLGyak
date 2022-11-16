package xPathNWTTCA;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class xPathNWTTCA {

	public static void main(String[] args){
		// TODO Auto-generated method stub

		
		try {
			//File xmlFile = new File("studentNWTTCA.xml");
			
			//DocumentBuilder létrehozása

			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse("studentNWTTCA.xml");
			
			document.getDocumentElement().normalize();
			
			//az XPath készítése
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//Meg kell adni az elérési út kifejezést és a csomópont listát
			
			//1, válassza ki az összes student element, amely a class gyermekei!
			//String expression = "/class/student";
			
			//2,Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "02"! 
			//String expression = "//student[@id='01']";
			
			//3,Kiválasztja az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban!
			//String expression = "//student";
			
			//4,Válassza ki a második student element, amely a class root element gyermeke!
			//String expression = "//student[2]";
			
			//5,Válassza ki az utolsó student elemet, amely a class root element gyermet!
			//String expression = "/class/student[last()]";
			
			//6,Válassza ki az utlsó elõtti student elemet, amely a class root element gyermeke!
			//String expression = "/class/student[last()-1]";
			
			//7,Válassza ki az elsõ két student elemt, amelyek a root element gyermekei!
			//String expression = "/class/student[position()<3]";
			
			//8,Válassza ki a class root element összes gyermek elemét!
			//String expression = "/class/*";
			
			//9,Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútommal!
			//String expression = "//student[@*]";
			
			//10,Válassza ki a dokumentum összes elemét!
			//String expression = "//*";
			
			//11,Válassza ki a class root element összes student elemét, amelynél a kor>20!
			//String expression = "/class/student[kor>20]";
			
			//12,Válassza ki az összes student elem összes keresztnev or vezeteknev csomópontot!
			String expression = "student[@keresztnev]";
			
			//Készítsünk egy listát, majd a xPath kifejezést le kell fordítani és ki kell értékelni.
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			//A for ciklus segítségével a NodeList csomópontjain végig kell iterálni
			for(int i = 0;i<nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktuális elem: " + node.getNodeName());
				
				//Meg kell vizsgálni a somópontot, tesztelni kell a subelemet
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					//az id attribútumot ad vissza
					System.out.println("Hallgató ID: " + element.getAttribute("id"));
				
					//keresztnevet ad vissza
					System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					
					//vezetélnevet ad vissza
					System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					
					//becenevet ad vissza
					System.out.println("Becenév: " + element.getElementsByTagName("becenev").item(0).getTextContent());

					//kort ad vissza
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
				}
			}
		}catch (ParserConfigurationException e) {
			e.printStackTrace();
		}catch (SAXException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

}
