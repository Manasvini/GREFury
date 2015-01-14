package com.example.grefury;


import android.net.ParseException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.JSON
 
public class Register extends Activity{
	
   
    
    Button submit;
    class AsyncTaskLogin extends AsyncTask<String,Void,String>{
    	String responsemsg;
		 private ProgressDialog pDialog;
		 StringBuffer buffer;
	     HttpResponse response;
	     HttpClient httpclient;
	     HttpGet httpget;
   	 protected void onPreExecute() {
         super.onPreExecute();
         pDialog = new ProgressDialog(Register.this);
         pDialog.setMessage("Creating Account..");
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
	   		nameValuePairs.add(new BasicNameValuePair("password",values[2]));
	   		nameValuePairs.add(new BasicNameValuePair("email",values[1]));
	   		HttpClient httpclient = new DefaultHttpClient();
	   		Log.i("reg","her");
	        HttpPost httppost = new HttpPost("http://10.0.2.2/GREFury/register.php");
	        Log.i("reg","her :P");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        Log.i("reg","her2");
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.i("reg","her3");
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
   	      JSONObject json_data=null;
   	     
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
		setContentView(R.layout.register_layout);
		 submit=(Button)findViewById(R.id.register_details_button);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				EditText nameBox=(EditText)findViewById(R.id.name_text_box);
				String name=nameBox.getText().toString();
				EditText emailBox=(EditText)findViewById(R.id.email_text_box);
				 String email=emailBox.getText().toString();
				EditText pwdBox=(EditText)findViewById(R.id.password_text_box);
				 String pwd=pwdBox.getText().toString();
				System.out.println(name+" "+email+" "+pwd);
				 new AsyncTaskLogin().execute(name,email,pwd);
				 
				 
			}
		});
		
	}
	
      
	}
