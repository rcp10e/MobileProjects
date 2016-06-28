package com.photofind;



import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobdb.android.UpdateRowData;

public class FriendsListActivity extends ListActivity {

	String[] friends;
	ArrayAdapter<String>adapter;
	ListView lv;
	String APP_KEY = "MIRGwN-5T3-ol1kL7sp6i36EAt77YZxZCIA-IND101202WoBpad1SsNhMuym888";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    
	    friends = getIntent().getStringArrayExtra("friends");
	    
	    adapter = new ArrayAdapter<String>(FriendsListActivity.this, android.R.layout.simple_list_item_1, friends);
	    setListAdapter(adapter);
	    
	    
	    lv = getListView();
	    
	    
	    lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				
				String friendName = lv.getItemAtPosition(arg2).toString();
				
				
				
				
				
				
				
			}
	    	
	    	
		});
	    
	    
	}

}
