package com.example.grefury;




import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
 
public class Login extends Activity{
	
   
    String responsemsg;
    Button submit;
    class AsyncTaskLogin extends AsyncTask<String,Void,String>{
		 
		 private ProgressDialog pDialog;
		 StringBuffer buffer;
	     HttpResponse response;
	     HttpClient httpclient;
	     HttpGet httpget;
   	 protected void onPreExecute() {
         super.onPreExecute();
         pDialog = new ProgressDialog(Login.this);
         pDialog.setMessage("Validating login..");
         pDialog.setIndeterminate(false);
         pDialog.setCancelable(true);
         pDialog.show();
     }
   	protected String doInBackground(String... values){
   		
   		String result="";
   		JSONObject jObj=null;
   		InputStream is=null;
   		int code;
   		String message;
   		try{
   			
	   		
	   		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	   		nameValuePairs.add(new BasicNameValuePair("username",values[0]));
	   		nameValuePairs.add(new BasicNameValuePair("password",values[1]));
	   		//nameValuePairs.add(new BasicNameValuePair("email",values[2]));
	   		HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://10.0.2.2/GREFury/login.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
           }
           catch(Exception e){
       
        	   pDialog.setMessage(e.toString());
   			   e.printStackTrace();
           	}
   		//finish();
   		try{
   			//InputStream is;
   	      BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
   	       StringBuilder sb = new StringBuilder();
   	       sb.append(reader.readLine() + "\n");

   	       String line="0";
   	       while ((line = reader.readLine()) != null) {
   	                      sb.append(line + "\n");
   	        }
   	        is.close();
   	        result=sb.toString();
   	        }catch(Exception e){
   	             pDialog.setMessage(e.toString());
   	        }
   		
   		try{
   		 jObj = new JSONObject(result);
   	    //  JSONObject json_data=null;
   	     
   	      }
   	      catch(Exception e1){
   	    	  pDialog.setMessage(e1.toString());
   	      }
   	 Intent switchIntent=new Intent(getApplicationContext(), NextScreen.class);
     try {
		switchIntent.putExtra("msg",jObj.getString("message"));
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		startActivity(switchIntent);
		
		return responsemsg;
	   		
   	}
   	 	 
   	   
   	protected void onPostExecute(String msg) {
   		
        pDialog.dismiss();
       
    }
   	   
   }

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//String message = getIntent().getStringExtra("msg");
		setContentView(R.layout.login_layout);
		 submit=(Button)findViewById(R.id.login_details_button);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				EditText nameBox=(EditText)findViewById(R.id.login_user_text_box);
				String name=nameBox.getText().toString();
				
				EditText pwdBox=(EditText)findViewById(R.id.login_password_text_box);
				 String pwd=pwdBox.getText().toString();
				System.out.println(name+" "+" "+pwd);
				 new AsyncTaskLogin().execute(name,pwd);
				 
				 
			}
		});
		
	}
	public void onRegisterComplete(String status){
		
		Toast tot2 = Toast.makeText(getApplicationContext(), status,Toast.LENGTH_LONG);
		tot2.show();
		
		
	}
      
	}
