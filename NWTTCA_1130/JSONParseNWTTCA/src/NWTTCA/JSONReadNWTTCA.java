package NWTTCA;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReadNWTTCA {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		JSONParser jsonParser = new JSONParser();

		FileReader toParse = new FileReader("JSONNWTTCA.json");
		
		 Object obj = jsonParser.parse(toParse);
		 
         System.out.println(obj);
	}

}
