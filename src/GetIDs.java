import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class GetIDs {

	public static void main(String[] args) throws IOException, JSONException {
		boolean returnedError = false;
		ArrayList<Champion> champs = new ArrayList<Champion>();
		int ctr = 1;
		String apiKey = "b40cf360-ac52-479d-b9eb-06dc99bdea83";
		JSONObject json = readJsonFromAPI("https://na.api.pvp.net/api/lol/static-data/na/v1.2/champion?api_key=" + apiKey);
		
		JSONObject jsonChildObject = (JSONObject)json.get("data");
		Iterator it = jsonChildObject.keys();
		String key = null;
		
		while (it.hasNext()) {
			key = (String)it.next();
			int id = Integer.parseInt(((JSONObject)jsonChildObject.get(key)).get("id").toString());
			String defString = "ID: " + ((JSONObject)jsonChildObject.get(key)).get("id") + " - " + ((JSONObject)jsonChildObject.get(key)).get("name") + ", " +  ((JSONObject)jsonChildObject.get(key)).get("title");
			champs.add(new Champion(id, defString));
		}
		
		Collections.sort(champs);
		
		for (Champion c : champs) {
			System.out.println(c.toString());
		}
	}
	
	public static JSONObject readJsonFromAPI(String call) throws IOException, JSONException {
		InputStream is = new URL(call).openStream();
		
		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(read);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	
	private static String readAll(Reader read) throws IOException {
		StringBuilder sb = new StringBuilder();
		int i;
		while ((i = read.read()) != -1) {
			sb.append((char) i);
		}
		return sb.toString();
	}
}