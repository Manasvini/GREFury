package com.example.grefury;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
//import android.view.View.OnClickListener;
//import android.widget.Button;
import android.os.Handler;

//import android.widget.Toast;
//import android.content.Intent;
public class PlayScreen extends Activity{
	//final Handler myHandler=new Handler();
	 String word="";
	 TextView []t;
	 TextView []t1;
	 TextView tv;
	 TextView hintText;
	 RelativeLayout layout;
	 String sentence="";
	 ProgressBar progressBar;
	 int []randomArray=new int[10];
	 int progress=0,numWords=0,score=0;
	// ProgressDialog pDialog;
	 String[]wordArray=new String[10];
	 final Handler myHandler = new Handler();
	 Timer myTimer;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		generateRandomNumbers();
		setContentView(R.layout.play_screen);
		hintText=new TextView(this);
		 layout=(RelativeLayout)findViewById(R.id.play_layout);
		int len=20;
		t =new TextView[len-1];
		t1=new TextView[len-1];
		RelativeLayout.LayoutParams wordparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		wordparams.leftMargin=50;
		wordparams.topMargin=165;
		hintText.setLayoutParams(wordparams);
		layout.addView(hintText);
		for(int i=0;i<len-1;i++){
			

			t1[i]=new TextView(this);
			t[i]=new TextView(this);
			RelativeLayout.LayoutParams tvparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			tvparams.topMargin=165;
			tvparams.leftMargin=50+20*i;
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.topMargin=175;
			params.leftMargin=50+20*i;
			t[i].setLayoutParams(tvparams);
			t1[i].setLayoutParams(params);
			layout.addView(t1[i]);
			layout.addView(t[i]);
		}
		 progressBar=(ProgressBar)findViewById(R.id.timer_progress_bar);
		// numWords++;
		 showWord();
		addButtons();
		
		//for(int j=0;j<3;j++){
			//progress=0;
			changeWord();
	//	}
		Button hint=(Button)findViewById(R.id.hint_button);
		
		
		hint.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				
				hintText.setText(replaceWithBlank());
			}
		});
		
		Button next=(Button)findViewById(R.id.next_button);
		next.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(numWords<10){
					calcScore();
					progress=0;
					clearWord();
					showWord();
					//numWords++;
				}
				else{
				
					 AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlayScreen.this);

			     		
			     		alertDialog.setMessage("Your score is "+score+"\n");

			     				//alertDialog.setMessage("Game completed!");

			     		alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			     			public void onClick(DialogInterface dialog,int which) {
			     					
			     					
			     				Intent switchIntent=new Intent(getApplicationContext(), ShowListOfWords.class);
			     				switchIntent.putExtra("words",wordArray);
			    				startActivity(switchIntent);
			     				/*for(int i=0;i<wordArray.length;i++){
				     				Toast tot2 = Toast.makeText(getApplicationContext(),""+wordArray[i],Toast.LENGTH_LONG);
				     		 		tot2.show();
				     			}*/
			     			
			     			}
			     		});

			     		
			     		alertDialog.show();
			        	 
				}
				
			}
		});
		
	}
	private int checkNumber(int number,int index){
		int i;
		for(i=0;i<index;i++){
			if(randomArray[i]==number)return 1;
		}
		return 0;
	}
	public void generateRandomNumbers(){
		int i=0,found,wordno;
		while(i<10){
		//	Toast tot = Toast.makeText(getApplicationContext(),"bleh"+i,Toast.LENGTH_LONG);
		 //		tot.show();
			 wordno=(int)(Math.random()*3653)+1;
			found=checkNumber(wordno,i);
			if(found==0){
				//Toast tot2 = Toast.makeText(getApplicationContext(),""+i+"-> "+wordno,Toast.LENGTH_LONG);
 		 		//tot2.show();
				randomArray[i++]=wordno;
				
			}
		}
	}
	public void changeWord(){
		 progress=0;
		 myTimer = new Timer();
	      myTimer.schedule(new TimerTask() {
	         @Override
	         public void run() {UpdateGUI();}
	      }, 0, 200);

	   }
	private void UpdateGUI() {
	   
	      myHandler.post(myRunnable);
	   }
	final Runnable myRunnable = new Runnable() {
	      public void run() {
	       // progress++;
	    	  if(progress<100)
	    		  progressBar.setProgress(progress++);
	    	  else  if(progress==100 && numWords<9){
	        	// progressBar.setProgress(progress++);
	        	 calcScore();
	        	 clearWord();
 	        	 showWord();
 	        	//numWords++;

	        	 progress=0;
	        	
	         }
	         else if( progress==100 && numWords==9){
	        	//finish();
	        	 myTimer.cancel();
	        	 //progressBar.setOnCom
	        	 AlertDialog.Builder alertDialog = new AlertDialog.Builder(PlayScreen.this);

	     		
	     		alertDialog.setMessage("Your score is "+score+"\n");

	     				//alertDialog.setMessage("Game completed!");

	     		alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
	     			public void onClick(DialogInterface dialog,int which) {
	     					
	     					
	     				Intent switchIntent=new Intent(getApplicationContext(), ShowListOfWords.class);
	     				switchIntent.putExtra("words",wordArray );
	    				startActivity(switchIntent);
	     			/*for(int i=0;i<wordArray.length;i++){
	     				Toast tot2 = Toast.makeText(getApplicationContext(),""+wordArray[i],Toast.LENGTH_LONG);
	     		 		tot2.show();
	     			}*/
	     			
	     			}
	     		});

	     		
	     		alertDialog.show();
	        	 
					
	         }
	        
	      }
	   };
	   public void calcScore(){
		   int len=word.length();
		   int flag=0;
		 
		   for(int i=0;i<len-1;i++){
			   if(((String)t[i].getText()).equals("")){
					   flag=1;
					
					   break;
			   }
			  
		   }
		   if(flag==0)score++; 
	   }
	
	public void showWord(){
		 tv=new TextView(this);
		//Toast tot2 = Toast.makeText(getApplicationContext(),""+numWords,Toast.LENGTH_LONG);
 		//tot2.show();
		//int wordno=(int)(Math.random()*49)+1;
		DatabaseHelper dbh=new DatabaseHelper(this);
		Word w=dbh.returnWord(randomArray[numWords]);
		wordArray[numWords]=w.getWord()+"\n"+w.getDefinition()+"\n"+w.getSentence()+"\n";
		word=w.getWord();
		//Toast tot = Toast.makeText(getApplicationContext(),""+wordArray[numWords],Toast.LENGTH_LONG);
 		//tot.show();
		sentence=w.getSentence();
		int l=w.getWord().length();
		RelativeLayout.LayoutParams wordparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		tv.setText(w.getDefinition());
		wordparams.topMargin=110;
		wordparams.leftMargin=30;
		tv.setLayoutParams(wordparams);
		layout.addView(tv);
		
		for(int i=0;i<l-1;i++){
			t[i].setText("");
			t1[i].setText("_ ");
		}
		numWords++;
	}
	public void clearWord(){
		for(int i=0;i<word.length()-1;i++){
			t1[i].setText("");
			t[i].setText("");
			
		}
		final Button a=(Button)findViewById(R.id.a_button);
		a.setBackgroundColor(Color.LTGRAY);
		final Button b=(Button)findViewById(R.id.b_button);
		b.setBackgroundColor(Color.LTGRAY);
		
		final Button c=(Button)findViewById(R.id.c_button);
		c.setBackgroundColor(Color.LTGRAY);
		
		final Button d=(Button)findViewById(R.id.d_button);
		d.setBackgroundColor(Color.LTGRAY);
		
		final Button e=(Button)findViewById(R.id.e_button);
		e.setBackgroundColor(Color.LTGRAY);
		
		final Button f=(Button)findViewById(R.id.f_button);
		f.setBackgroundColor(Color.LTGRAY);
		
		final  Button g=(Button)findViewById(R.id.g_button);
		g.setBackgroundColor(Color.LTGRAY);
		
		final Button h=(Button)findViewById(R.id.h_button);
		
		h.setBackgroundColor(Color.LTGRAY);
		final Button i=(Button)findViewById(R.id.i_button);
		
		i.setBackgroundColor(Color.LTGRAY);
		final Button j=(Button)findViewById(R.id.j_button);
		
		j.setBackgroundColor(Color.LTGRAY);
		final Button k=(Button)findViewById(R.id.k_button);
		k.setBackgroundColor(Color.LTGRAY);
	
		final Button l=(Button)findViewById(R.id.l_button);
		
		l.setBackgroundColor(Color.LTGRAY);
		final Button m=(Button)findViewById(R.id.m_button);
		
		m.setBackgroundColor(Color.LTGRAY);
		final Button n=(Button)findViewById(R.id.n_button);
		
		n.setBackgroundColor(Color.LTGRAY);
		final Button o=(Button)findViewById(R.id.o_button);
		
		o.setBackgroundColor(Color.LTGRAY);
		final Button p=(Button)findViewById(R.id.p_button);
		
		p.setBackgroundColor(Color.LTGRAY);
		final Button q=(Button)findViewById(R.id.q_button);
		
		q.setBackgroundColor(Color.LTGRAY);
		final Button r=(Button)findViewById(R.id.r_button);
		
		r.setBackgroundColor(Color.LTGRAY);
		final Button s=(Button)findViewById(R.id.s_button);
		
		s.setBackgroundColor(Color.LTGRAY);
		final Button t=(Button)findViewById(R.id.t_button);
		
		t.setBackgroundColor(Color.LTGRAY);
		final Button u=(Button)findViewById(R.id.u_button);
		
		u.setBackgroundColor(Color.LTGRAY);
		final Button v=(Button)findViewById(R.id.v_button);
		
		v.setBackgroundColor(Color.LTGRAY);
		final Button w=(Button)findViewById(R.id.w_button);
		
		w.setBackgroundColor(Color.LTGRAY);
		final Button x=(Button)findViewById(R.id.x_button);
		
		x.setBackgroundColor(Color.LTGRAY);
		final Button y=(Button)findViewById(R.id.y_button);
		
		y.setBackgroundColor(Color.LTGRAY);
		final Button z=(Button)findViewById(R.id.z_button);
		
		z.setBackgroundColor(Color.LTGRAY);
		tv.setText("");
		hintText.setText("");
	}
	public void addButtons(){
		int color=0xFFFF00;
		final Button a=(Button)findViewById(R.id.a_button);
		//a.setBackgroundColor(Color.LTGRAY);
		addListener(a);
		final Button b=(Button)findViewById(R.id.b_button);
		//b.setBackgroundColor(Color.LTGRAY);
		addListener(b);
		final Button c=(Button)findViewById(R.id.c_button);
		//c.setBackgroundColor(Color.LTGRAY);
		addListener(c);
		final Button d=(Button)findViewById(R.id.d_button);
		//d.setBackgroundColor(Color.LTGRAY);
		addListener(d);
		final Button e=(Button)findViewById(R.id.e_button);
		//e.setBackgroundColor(Color.LTGRAY);
		addListener(e);
		final Button f=(Button)findViewById(R.id.f_button);
		//f.setBackgroundColor(Color.LTGRAY);
		addListener(f);
		final  Button g=(Button)findViewById(R.id.g_button);
		//g.setBackgroundColor(Color.LTGRAY);
		addListener(g);
		final Button h=(Button)findViewById(R.id.h_button);
		addListener(h);
		//h.setBackgroundColor(Color.LTGRAY);
		final Button i=(Button)findViewById(R.id.i_button);
		addListener(i);
		//i.setBackgroundColor(Color.LTGRAY);
		final Button j=(Button)findViewById(R.id.j_button);
		addListener(j);
		//j.setBackgroundColor(Color.LTGRAY);
		final Button k=(Button)findViewById(R.id.k_button);
		//k.setBackgroundColor(Color.LTGRAY);
		addListener(k);
		final Button l=(Button)findViewById(R.id.l_button);
		addListener(l);
		//l.setBackgroundColor(Color.LTGRAY);
		final Button m=(Button)findViewById(R.id.m_button);
		addListener(m);
		//m.setBackgroundColor(Color.LTGRAY);
		final Button n=(Button)findViewById(R.id.n_button);
		addListener(n);
		//n.setBackgroundColor(Color.LTGRAY);
		final Button o=(Button)findViewById(R.id.o_button);
		addListener(o);
		//o.setBackgroundColor(Color.LTGRAY);
		final Button p=(Button)findViewById(R.id.p_button);
		addListener(p);
		//p.setBackgroundColor(Color.LTGRAY);
		final Button q=(Button)findViewById(R.id.q_button);
		addListener(q);
		//q.setBackgroundColor(Color.LTGRAY);
		final Button r=(Button)findViewById(R.id.r_button);
		addListener(r);
		//r.setBackgroundColor(Color.LTGRAY);
		final Button s=(Button)findViewById(R.id.s_button);
		addListener(s);
		//s.setBackgroundColor(Color.LTGRAY);
		final Button t=(Button)findViewById(R.id.t_button);
		addListener(t);
		//t.setBackgroundColor(Color.LTGRAY);
		final Button u=(Button)findViewById(R.id.u_button);
		addListener(u);
		//u.setBackgroundColor(Color.LTGRAY);
		final Button v=(Button)findViewById(R.id.v_button);
		addListener(v);
		//v.setBackgroundColor(Color.LTGRAY);
		final Button w=(Button)findViewById(R.id.w_button);
		addListener(w);
		//w.setBackgroundColor(Color.LTGRAY);
		final Button x=(Button)findViewById(R.id.x_button);
		addListener(x);
		//x.setBackgroundColor(Color.LTGRAY);
		final Button y=(Button)findViewById(R.id.y_button);
		addListener(y);
		//y.setBackgroundColor(Color.LTGRAY);
		final Button z=(Button)findViewById(R.id.z_button);
		addListener(z);
		//z.setBackgroundColor(Color.LTGRAY);
		
	}
	public void addListener(final Button b){
		//tv.
		b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				char letter ;
				if(b.getId()==R.id.a_button)
					letter='a';
				else if(b.getId()==R.id.b_button)
					letter='b';
				else if(b.getId()==R.id.c_button)
					letter='c';
				
				else if(b.getId()==R.id.d_button)
					letter='d';
				
				else if(b.getId()==R.id.e_button)
					letter='e';
				
				else if(b.getId()==R.id.f_button)
					letter='f';
				
				else if(b.getId()==R.id.g_button)
					letter='g';
				
				else if(b.getId()==R.id.h_button)
					letter='h';
				
				else if(b.getId()==R.id.i_button)
					letter='i';
				
				else if(b.getId()==R.id.j_button)
					letter='j';
				
				else if(b.getId()==R.id.k_button)
					letter='k';
				
				else if(b.getId()==R.id.l_button)
					letter='l';
				
				else if(b.getId()==R.id.m_button)
					letter='m';
				
				else if(b.getId()==R.id.n_button)
					letter='n';
				
				else if(b.getId()==R.id.o_button)
					letter='o';
				
				else if(b.getId()==R.id.p_button)
					letter='p';
				
				else if(b.getId()==R.id.q_button)
					letter='q';
				
				else if(b.getId()==R.id.r_button)
					letter='r';
				
				else if(b.getId()==R.id.s_button)
					letter='s';
				
				else if(b.getId()==R.id.t_button)
					letter='t';
				
				else if(b.getId()==R.id.u_button)
					letter='u';
				
				else if(b.getId()==R.id.v_button)
					letter='v';
				
				else if(b.getId()==R.id.w_button)
					letter='w';
				
				else if(b.getId()==R.id.x_button)
					letter='x';
				
				else if(b.getId()==R.id.y_button)
					letter='y';
				
				else 
					letter='z';
				
				
				//Toast tot3 = Toast.makeText(getApplicationContext(),""+letter,Toast.LENGTH_LONG);
		 		//tot3.show();
				int found=findLetter(letter);
				if(found==0)b.setBackgroundColor(Color.RED);
				else if(found==1)b.setBackgroundColor(Color.GREEN);
			}
		});
	}
	public int findLetter(char letter){
		int flag=0;
		for(int i=0;i<word.length()-1;i++)
		{
			if(word.charAt(i)==letter){
				t[i].setText(""+letter);
				flag=1;
			}
		}
		return flag;
		
	}
	public String replaceWithBlank(){
		StringTokenizer st = new StringTokenizer(sentence);
		 
		String newSentence="";
		String tmp="";
		while (st.hasMoreElements()) {
			
			tmp=""+st.nextElement();
			if(word.contains((CharSequence)tmp)==true){
				tmp="_______ ";
			}
			newSentence=newSentence+tmp+" ";
		}
		return newSentence;
	}

}
