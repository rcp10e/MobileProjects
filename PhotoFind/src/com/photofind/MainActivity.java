package com.photofind;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;

public class MainActivity extends Activity {

    TextView test;
    TextView location;
    Facebook facebook = new Facebook("337321883018339");
    ImageView userpicture;
    String name;
    String id;
    String locationText;
    String[] friends;
	
    @Override 
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    		setContentView(R.layout.activity_main);
    		
    		test = (TextView) findViewById(R.id.facebooktest);
    		userpicture = (ImageView) findViewById(R.id.facebookpicture);
    		location = (TextView) findViewById(R.id.location);
    		
    		facebook.authorize(this, new String[]{"user_location"}, new DialogListener() {
    
    			@Override public void onComplete(Bundle values) {
    				getUserInfo();
    	    		test.setText(name);
    				userpicture.setImageBitmap(getUserPic(id));
    				location.setText(locationText);

    			}
    
    			@Override public void onFacebookError(FacebookError error) {}
   
    			@Override public void onError(DialogError e) {}
    
    			@Override public void onCancel() {}
    		});
    		 
    		
    }
    
    
    @Override 
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	facebook.authorizeCallback(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
    
    //get user picture
    public Bitmap getUserPic(String userID) {
        String imageURL;
        Bitmap bitmap = null;
        
        imageURL = "http://graph.facebook.com/" + id + "/picture?type=large";
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageURL).getContent());
        } catch (Exception e) {
            Log.d("TAG", "Loading Picture FAILED");
            e.printStackTrace();
        }
        return bitmap;
    }
    
    //get user info
    public void getUserInfo(){
    	
    	//get User info in JSON format 
    	JSONObject userObject = null;
    	JSONObject locationObject = null;
    	JSONObject friendsObject = null;
    	JSONArray friendsArray = null;
		
    	try {
			userObject = new JSONObject(facebook.request("me"));
			locationObject = userObject.getJSONObject("location");
			friendsObject = new JSONObject(facebook.request("me/friends"));
			friendsArray = friendsObject.getJSONArray("data");
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
		
		//Parse info out of JSON objects
		try {
			id = userObject.getString("id");
			name =userObject.getString("name");
			locationText = locationObject.getString("name");
			friends = new String[friendsArray.length()];
			
			for(int i = 0; i < friendsArray.length(); i++){
				JSONObject obj = friendsArray.getJSONObject(i);
				friends[i] = obj.getString("name");
			}
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
    }
    
    
    public void createGame(View v){
    	
    	Intent intent = new Intent(this, CreateGameActivity.class);
    	intent.putExtra("friends", friends);
    	startActivity(intent);
    	
    }
  
    public void friendActivity(View v)
    {
    	
    	Intent intent = new Intent(this, FriendsListActivity.class);
    	intent.putExtra("friends", friends);
    	startActivity(intent);
    	
    }
}





