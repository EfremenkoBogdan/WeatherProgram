package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane backColor;

    @FXML
    private Button button;

    @FXML
    private TextField cityField;

    @FXML
    private Text infoFeels;

    @FXML
    private Text infoMax;

    @FXML
    private Text infoMiddle;

    @FXML
    private Text infoMin;

    @FXML
    private Text infoPress;

    @FXML
    private Text infoTemp;
    
    @FXML
    private Text infoWind;

    @FXML
    private Text weatherUpprer;

    @FXML
    void initialize() {
    	button.setOnAction(event -> {
    		String getUserCity = cityField.getText().trim(); 
    		//thanks for really nice API :)
    		String output = getUrlContent ("https://api.openweathermap.org/data/2.5/weather?q="+ getUserCity +"&appid=e999c91df11359a66397e416851654a7&units=metric");
    		if(!output.isEmpty()) {
    			JSONObject obj = new JSONObject(output);
    			infoTemp.setText("Temperature: " + obj.getJSONObject("main").getInt("temp"));
    			infoFeels.setText("Fills like: " + obj.getJSONObject("main").getInt("feels_like"));
    			infoMax.setText("MAX: " + obj.getJSONObject("main").getInt("temp_max"));
    			infoMin.setText("MIN: " + obj.getJSONObject("main").getInt("temp_min"));
    			infoPress.setText("Pressure: " + obj.getJSONObject("main").getInt("pressure"));
    			infoWind.setText("Wind speed: " + obj.getJSONObject("wind").getInt("speed"));
    		}
    	});
    }
    
    private static String getUrlContent (String urlAddress) {
    	StringBuffer content = new StringBuffer();
    	
    	try {
    		URL url = new URL(urlAddress);
    		URLConnection urlConn = url.openConnection();
    		
    		BufferedReader bufferReader = new BufferedReader (new InputStreamReader(urlConn.getInputStream()));
    		
    		String line;
    		
    		while((line = bufferReader.readLine()) != null) {
    			content.append(line +"\n");
    		}
    		bufferReader.close();
    	} catch (Exception e) {
    		System.out.println ("City doesn`t exist!");
    	}
    	return content.toString();
    }

}
