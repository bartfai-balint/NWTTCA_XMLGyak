package NWTTCA;

import org.json.simple.JSONObject;

public class ObjectNWTTCA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JSONObject student = new JSONObject();
		
		student.put("nev", "Bártfai Bálint");
		student.put("neptunkod", "NWTTCA");
		student.put("szak", "PTI");
		
		System.out.println(student.toString());
		
	}

}
