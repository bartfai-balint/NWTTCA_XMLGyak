package hu.domparse.NWTTCA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomReadNWTTCA {

	 private static int baseLineRule = 0;
	 private final static String XMLFILE = "XMLNWTTCA.xml";
	 private static StringBuilder result;
	
	public static void main(String[] args) throws IOException {
        readOutAllElements();
        xmlToText(result);
    }

    //Eredm�ny ki�r�sa
    private static void resultToConsole() {
        System.out.println(result.toString());
    }

    //Minden elem beolvas�sa
    private static void readOutAllElements(){
        Document doc;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        result = new StringBuilder();
        try {
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            doc = dBuilder.parse(XMLFILE);

            Node er = doc.getFirstChild();
            NodeList erList = er.getChildNodes();
            getAllElements(erList);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        resultToConsole();
    }

    //Elemek �sszef�z�s
    private static void getAllElements(NodeList nl) {
        Node node;
        for (int i = 0; i < nl.getLength(); i++) {
            node = nl.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {

                result.append(indent(3)+node.getNodeName());

                if(node.hasAttributes()) {
                    result.append(" "+ getAttributes(node.getAttributes())+"\n");//
                } else {
                    result.append("\n");
                }
                if(node.getChildNodes().getLength() != 1) {

                    NodeList childList = node.getChildNodes();
                    baseLineRule++;
                    getAllElements(childList);
                } else {
                    baseLineRule++;
                    result.append(indent(3)+node.getTextContent()+"\n");
                    baseLineRule--;
                }
            }
        }
        baseLineRule--;
    }

    //Elemek elv�laszt�sa
    private static String indent(int multiplyBy) {
        StringBuilder whiteSpaces = new StringBuilder();
        for (int i = 0; i < (baseLineRule *multiplyBy); i++) {
            whiteSpaces.append(" ");
        }
        return whiteSpaces.toString();
    }

    //Elemek tulajdons�g�nak a neve
    private static String getAttributes(NamedNodeMap node) {
        StringBuilder sb = new StringBuilder(" Attributes: [");
        for (int i = 0; i < node.getLength(); i++) {
            if(i != node.getLength()-1) {
                sb.append(node.item(i).getNodeName()+" : "+node.item(i).getNodeValue()+"; ");
            } else {
                sb.append(node.item(i).getNodeName()+" : "+node.item(i).getNodeValue());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //XML �tkonvert�l�sa Txt f�jlra
    private static void xmlToText(StringBuilder result)throws IOException {
    	FileWriter file = new FileWriter("XMLNWTTCA.txt");
    	file.write(result.toString());
    	file.close();
    }

}
