package NWTTCA;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

public class ListNWTTCA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> list = new ArrayList<String>();
		
		list.add("Bártfai Bálint");
		list.add("NWTTCA");
		list.add("PTI");
		
		String jsonStr = JSONArray.toJSONString(list);
		System.out.println(jsonStr);
		
	}

}
