package com.lacunaexpanse.LacunaAndroidClient;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PlanetBuildingsView extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_buildings_view);
        
        final Bundle EXTRAS = getIntent().getExtras();
        
        String sessionId = null;
        String selectedServer = null;
        String planetId = null;
        
        if (EXTRAS != null) {
        	sessionId = EXTRAS.getString("sessionId");
        	selectedServer = EXTRAS.getString("selectedServer");
        	planetId = EXTRAS.getString("planetId");
        }
        
        final String SESSION_ID = sessionId;
        final String SELECTED_SERVER = selectedServer;
        final String PLANET_ID = planetId;
        
        String[] paramsBuilder = {SESSION_ID,PLANET_ID};
        String params = Library.parseParams(paramsBuilder);
        String serverUrl = Library.assembleGetUrl(SELECTED_SERVER, "body", "get_buildings", params);
        
        final String serverResponse = Library.sendServerRequest(serverUrl);
        
        try {
        	JSONObject jObject = new JSONObject(serverResponse);
        }
        catch (JSONException e) {
        	Toast.makeText(PlanetBuildingsView.this, "Error interpereting server response: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        
        Button goBackButton = (Button) findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent intent = new Intent(PlanetBuildingsView.this,PlanetResourceView.class);
				intent.putExtra("sessionId",SESSION_ID);
				intent.putExtra("selectedServer",SELECTED_SERVER);
				intent.putExtra("planetId",PLANET_ID);
				
				PlanetBuildingsView.this.startActivity(intent);
			}
		});
    }
}