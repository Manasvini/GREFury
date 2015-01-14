package com.example.grefury;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;


public class ScoreScreen extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String message = getIntent().getStringExtra("msg");
		setContentView(R.layout.score_layout);
		
		TextView textView=(TextView)findViewById(R.id.score_text);
		textView.setText(message);
		Button play=(Button)findViewById(R.id.play_again_button);
		play.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent switchIntent=new Intent(getApplicationContext(), PlayScreen.class);
				startActivity(switchIntent);
				
			}
		});
	}

}
