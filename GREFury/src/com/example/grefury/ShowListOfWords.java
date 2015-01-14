package com.example.grefury;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ShowListOfWords extends Activity{

	 String []data=new String[10];
	public void onCreate(Bundle savedInstanceState)
	{
		//data[0]="helllo";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		data=getIntent().getStringArrayExtra("words");
		
		int  c1=getResources().getColor(R.color.myblue);
		int c2=getResources().getColor(R.color.myorange);
	ListView list = (ListView) findViewById(R.id.simpleList);
		MyAdapter adapter =
			new MyAdapter(this, data,c1,c2);
		list.setAdapter(adapter);
	}
	
}

class MyAdapter extends ArrayAdapter{
	
	  private final Context context;
	  private final String[] values;
	  int c1,c2;
	  public MyAdapter(Context context, String[] values,int color1,int color2) {
	    super(context, android.R.layout.simple_list_item_1, values);
	    this.context = context;
	    this.values = values;
	    c1=color1;
	    c2=color2;
	  }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {  
	View view = super.getView(position, convertView, parent);  
	if (position % 2 == 1) {
		view.setBackgroundColor(c1) ; 
	} else {
	    view.setBackgroundColor(c2);  
	}

	return view;  
	}
	
	
}
