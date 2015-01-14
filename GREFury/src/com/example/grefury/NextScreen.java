package com.example.grefury;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NextScreen extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String message = getIntent().getStringExtra("msg");
		setContentView(R.layout.score_layout);
		//Toast tot2 = Toast.makeText(getApplicationContext(), message,
			//	Toast.LENGTH_LONG);
		//tot2.show();
		TextView tv=(TextView)findViewById(R.id.score_text);
		tv.setText(message);
		Button practiceButton=(Button)findViewById(R.id.play_again_button);
	practiceButton.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				Intent switchIntent=new Intent(getApplicationContext(), PlayScreen.class);
				
				startActivity(switchIntent);
				
			}
		});
	}

}
