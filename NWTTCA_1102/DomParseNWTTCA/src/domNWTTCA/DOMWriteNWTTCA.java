package domNWTTCA;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DOMWriteNWTTCA {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		// TODO Auto-generated method stub
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		
		Element root = doc.createElementNS("DOMNWTTCA", "users");
		doc.appendChild(root);
		
		root.appendChild(createUser(doc, "1", "Bártfai", "Bálint", "programtervezõ"));
		root.appendChild(createUser(doc, "2", "Horanek", "Dávid", "programtervezõ"));
		root.appendChild(createUser(doc, "3", "Ferencsik", "Márk", "programtervezõ"));
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/xslt} indent-amount", "2");
	
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File("users2.xml");
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
		
	}
	
	private static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {

		Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.setAttribute("firstname", firstName);
		user.setAttribute("lastname", lastName);
		user.setAttribute("profession", profession);
		
		return user;
		
	}
	
	

}
