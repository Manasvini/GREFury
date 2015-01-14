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


public class TopMenu extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//String message = getIntent().getStringExtra("msg");
		setContentView(R.layout.topmenu);
		//Toast tot2 = Toast.makeText(getApplicationContext(), "hi",Toast.LENGTH_LONG);
		//tot2.show();
		Button playButton=(Button)findViewById(R.id.play_button);
		playButton.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				Intent switchIntent=new Intent(getApplicationContext(), MainMenu.class);
				
				startActivity(switchIntent);
				
			}
		});
		
		Button practiceButton=(Button)findViewById(R.id.practice_button);
		practiceButton.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				Intent switchIntent=new Intent(getApplicationContext(), PlayScreen.class);
				
				startActivity(switchIntent);
				
			}
		});
		Button quitButton=(Button)findViewById(R.id.quit_button);
		quitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				System.exit(0);
				
			}
		});
		
		
	}
	

}


