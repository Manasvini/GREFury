package com.example.grefury;

public class Word {
	private String word;
	private String definition;
	private String sentence;
	public Word(String w,String d,String s){
			word=w;
			definition=d;
			sentence=s;
	}
	public String getWord(){
			return word;
			
	}
	public String getDefinition(){
			return definition;
			
	}
	public String getSentence(){
		return sentence;
	}

}
