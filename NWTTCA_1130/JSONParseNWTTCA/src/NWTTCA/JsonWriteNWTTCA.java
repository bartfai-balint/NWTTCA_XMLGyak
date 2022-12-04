package NWTTCA;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonWriteNWTTCA {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		JSONObject employeeDetails = new JSONObject();
		
        employeeDetails.put("nev", "Bártfai Bálint");
        employeeDetails.put("neptunkod", "NWTTCA");
        employeeDetails.put("szak", "PTI");
        
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeDetails);
        
        System.out.println(employeeList);
        
        FileWriter file = new FileWriter("JSONnwttca_out.json");
        
        file.write(employeeList.toJSONString()); 
        file.flush();
	}

}
