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
			
			//DocumentBuilder l�trehoz�sa

			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse("studentNWTTCA.xml");
			
			document.getDocumentElement().normalize();
			
			//az XPath k�sz�t�se
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//Meg kell adni az el�r�si �t kifejez�st �s a csom�pont list�t
			
			//1, v�lassza ki az �sszes student element, amely a class gyermekei!
			//String expression = "/class/student";
			
			//2,V�lassza ki azt a student elemet, amely rendelkezik "id" attrib�tummal �s �rt�ke "02"! 
			//String expression = "//student[@id='01']";
			
			//3,Kiv�lasztja az �sszes student elemet, f�ggetlen�l att�l, hogy hol vannak a dokumentumban!
			//String expression = "//student";
			
			//4,V�lassza ki a m�sodik student element, amely a class root element gyermeke!
			//String expression = "//student[2]";
			
			//5,V�lassza ki az utols� student elemet, amely a class root element gyermet!
			//String expression = "/class/student[last()]";
			
			//6,V�lassza ki az utls� el�tti student elemet, amely a class root element gyermeke!
			//String expression = "/class/student[last()-1]";
			
			//7,V�lassza ki az els� k�t student elemt, amelyek a root element gyermekei!
			//String expression = "/class/student[position()<3]";
			
			//8,V�lassza ki a class root element �sszes gyermek elem�t!
			//String expression = "/class/*";
			
			//9,V�lassza ki az �sszes student elemet, amely rendelkezik legal�bb egy b�rmilyen attrib�tommal!
			//String expression = "//student[@*]";
			
			//10,V�lassza ki a dokumentum �sszes elem�t!
			//String expression = "//*";
			
			//11,V�lassza ki a class root element �sszes student elem�t, amelyn�l a kor>20!
			//String expression = "/class/student[kor>20]";
			
			//12,V�lassza ki az �sszes student elem �sszes keresztnev or vezeteknev csom�pontot!
			String expression = "student[@keresztnev]";
			
			//K�sz�ts�nk egy list�t, majd a xPath kifejez�st le kell ford�tani �s ki kell �rt�kelni.
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			//A for ciklus seg�ts�g�vel a NodeList csom�pontjain v�gig kell iter�lni
			for(int i = 0;i<nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktu�lis elem: " + node.getNodeName());
				
				//Meg kell vizsg�lni a som�pontot, tesztelni kell a subelemet
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					//az id attrib�tumot ad vissza
					System.out.println("Hallgat� ID: " + element.getAttribute("id"));
				
					//keresztnevet ad vissza
					System.out.println("Keresztn�v: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					
					//vezet�lnevet ad vissza
					System.out.println("Vezet�kn�v: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					
					//becenevet ad vissza
					System.out.println("Becen�v: " + element.getElementsByTagName("becenev").item(0).getTextContent());

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
