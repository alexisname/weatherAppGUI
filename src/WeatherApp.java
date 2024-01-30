import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {
    //fetch weather data for given location
    public static JSONObject getWeatherData(String locationName){
        //get coordinates using geolocation API
        JSONArray locationData = getLocationData(locationName);
        return null;
    }

    public static JSONArray getLocationData(String locationName){
        //replace any whitespace in location name to +, to adhere to API's request format, e.g., New York will be New+York
        locationName = locationName.replaceAll(" ","+");

        //build API url with location parameter
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                locationName + "&count=10&language=en&format=json";
        try{
            //make API call and get response
            HttpURLConnection conn = fetchAPIResponse(urlString);

            //check response status
            if(conn.getResponseCode()!=200){
                System.out.println("Error: Could not connect to API");
                return null;
            }
            else {
                //store the API response
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());
                while(scanner.hasNext()){
                    resultJson.append(scanner.nextLine());
                }
                //close scanner
                scanner.close();

                //close url connection
                conn.disconnect();

                //parse the JSON string into JSON obj
                JSONParser parser = new JSONParser();
                JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                //get the list of location data from the API based on the location name
                JSONArray locationData = (JSONArray) resultJsonObj.get("results");
                return locationData;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection fetchAPIResponse(String urlString){
        try{
            //try to create connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //set request method to get
            conn.setRequestMethod("GET");

            //connect to our API
            conn.connect();
            return conn;
        }catch (IOException e){
            e.printStackTrace();
        }

        //no connection
        return null;
    }
}
