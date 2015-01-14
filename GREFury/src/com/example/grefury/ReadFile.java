package com.example.grefury;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;


public class ReadFile extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dummy_layout);
		readFile();
		
	}
	public void readFile(){
		// String line=null;
         String res = "";
         int i=3653;
         InputStream is = getResources().openRawResource(R.raw.sublist);
         
        // FileInputStream fis;
         
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         String readLine = null;
 
         try {
        	//readLine= br.readLine();
            while ((readLine = br.readLine()) != null && i<3655) {
            	
            	// res+=readLine;
            	// 
            	 if(res.equals("")==true ){
            		   res=readLine+"#";
            		   }
    		     else{
    			   if(!readLine.equals("\n"))
    		       res=res+readLine;
    			 }
            	//Toast tot = Toast.makeText(getApplicationContext(),""+ res.charAt(res.length()-1),Toast.LENGTH_LONG);
           	 	//tot.show();
            	 if(res.charAt(res.length()-1)=='$'){
            		 
            		 String token= res.replaceAll("'", "\'");
            		//Toast tot2 = Toast.makeText(getApplicationContext(), res,Toast.LENGTH_LONG);
              	 	//tot2.show();
            		 myParseString(token,i);
            		 res="";
            		 i++;
            	 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
       Toast tot = Toast.makeText(getApplicationContext(),"done"+i,Toast.LENGTH_LONG);
    	 	tot.show();
        // return res;
	}
	public void myParseString(String token,int i){
		StringTokenizer st=new StringTokenizer(token,"#$-");
		String word=st.nextToken();
		String def=st.nextToken();
		String sentence=st.nextToken();
		//Toast tot1 = Toast.makeText(getApplicationContext(),""+i+"."+ word,Toast.LENGTH_LONG);
 		//tot1.show();
 		//Toast tot2 = Toast.makeText(getApplicationContext(), def,Toast.LENGTH_LONG);
 		//tot2.show();
		//Toast tot3 = Toast.makeText(getApplicationContext(),""+i+"."+ word+"-"+def+"\n"+sentence,Toast.LENGTH_LONG);
 		//tot3.show();
 		DatabaseHelper dbh=new DatabaseHelper(this);
 		//dbh.deleteWord(word);
 		//dbh.addWord(new Word(word,def,sentence),i);
 		//dbh.deleteWord()
 		Word newWord=dbh.returnWord(i);
 		//if(i==1400){
 		Toast tot3 = Toast.makeText(getApplicationContext(),""+i+"."+ newWord.getWord()+"-"+newWord.getDefinition()+"\n"+newWord.getSentence(),Toast.LENGTH_LONG);
 		tot3.show();
 		//}
	}

}
