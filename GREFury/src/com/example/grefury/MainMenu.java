package com.example.grefury;

//import com.example.firstapp.SecondHelllo;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;


public class MainMenu extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//String message = getIntent().getStringExtra("msg");
		setContentView(R.layout.activity_menu);
		Toast tot2 = Toast.makeText(getApplicationContext(), "hi",Toast.LENGTH_LONG);
		tot2.show();
		Button registerButton=(Button)findViewById(R.id.register_button);
		registerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				Intent switchIntent=new Intent(getApplicationContext(), Register.class);
				
				startActivity(switchIntent);
				
			}
		});
		Button loginButton=(Button)findViewById(R.id.login_button);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				Intent switchIntent=new Intent(getApplicationContext(), Login.class);
				
				startActivity(switchIntent);
				
			}
		});
		
		
		
	}
	

}
