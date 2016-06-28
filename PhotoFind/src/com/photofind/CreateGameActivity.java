package com.photofind;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CreateGameActivity extends Activity {

	
	String[] friends;
	Button invite;
	Button item;
	String[] itemsList;
	ListView lv;
	ArrayAdapter<String>adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.create_game);
	
	    itemsList = new String[1];
	    itemsList[0] = "No Items Yet";
	    friends = getIntent().getStringArrayExtra("friends");
	    item = (Button) findViewById(R.id.additem);
	    
	    lv = (ListView) findViewById(R.id.itemlist);
	    
	    adapter = new ArrayAdapter<String>(CreateGameActivity.this, R.layout.list_item,  itemsList);
	    lv.setAdapter(adapter);
	    
	    
	}

	
	
	
}
