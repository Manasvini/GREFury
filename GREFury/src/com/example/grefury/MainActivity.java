package com.example.grefury;

import android.os.Bundle;

//import android.widget.ImageView;
import android.widget.ProgressBar;
//import android.widget.Toast;
//import android.widget.ProgressBar;
import android.app.Activity;
//import android.view.*;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		//  ImageView image = (ImageView) findViewById(R.id.splash_image);
		//final ProgressBar progress=(ProgressBar)findViewById(R.id.splash_progress_bar);
	
		final ProgressBar progress=(ProgressBar)findViewById(R.id.splash_progress_bar);
		Thread timer = new Thread(){
				//int progressBarStatus=0;
			   
		       
		                   public void run()
		                    {
		                    	//int i=0;
		                    	 try{
		                             int logoTimer = 0;
		                             while (logoTimer<5000){
		                               this.sleep(100);
		                                 logoTimer=logoTimer+100;
		                                 progress.setProgress(logoTimer/50);
		                                // i++;
		                             }
		                           
		                             if( logoTimer==5000){
		                            	 //	Toast tot2 = Toast.makeText(getApplicationContext(), "hi",Toast.LENGTH_LONG);
		                		 			//tot2.show();
		                		 			//this.destroy()
		                		 			Intent switchIntent=new Intent(getApplicationContext(), TopMenu.class);
		                		   		      startActivity(switchIntent);
		                	    	 
		                   		      
		                   		      }
		                    	 }
		                             catch (InterruptedException e) {
		                                 // TODO Auto-generated catch block
		                                 e.printStackTrace();
		                             }
		                           
		                    }
		                };

		            
		      timer.start();
		    //  timer.
		    }
		   
		//progress.
	}
	


	

