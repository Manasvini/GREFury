package com.example.grefury;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
public class DatabaseHelper extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	private static String DB_PATH = "/data/data/com.example.GREFury/databases/";
	// Database Name
	private static final String DATABASE_NAME = "GREFuryDB";
	 private SQLiteDatabase myDataBase; 
	// words table name
	private static final String TABLE_WORDS = "wordlist";
	
	// Contacts Table Columns names
	private static final String KEY_WORD = "word";
	private static final String KEY_WORD_NO = "word_no";
	private static final String KEY_DEFINITION = "definition";
	private static final String KEY_SENTENCE = "sentence";
    private final Context myContext;
    
   
    public DatabaseHelper(Context context) {
 
    	super(context, DATABASE_NAME, null, 1);
        this.myContext = context;
    }	
    public void createDataBase() throws IOException{
    	 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DATABASE_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DATABASE_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = DB_PATH + DATABASE_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }
    @Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
	
	public void addWord(Word word,int i) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_WORD, word.getWord()); // Contact Name
	    values.put(KEY_WORD_NO, i);
	    values.put(KEY_DEFINITION, word.getDefinition()); // Contact Phone Number
	    values.put(KEY_SENTENCE, word.getSentence());
	    // Inserting Row
	    db.insert(TABLE_WORDS, null, values);
	    db.close(); // Closing database connection
	}
	Word returnWord(int i) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_WORDS, new String[] { KEY_WORD_NO,KEY_WORD,
                KEY_DEFINITION, KEY_SENTENCE }, KEY_WORD_NO + "=?",
                new String[] { ""+i }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Word dbword = new Word((cursor.getString(1)),
                cursor.getString(2), cursor.getString(3));
        // return contact
        return dbword;
    }
	public int updateWord(Word word) {
	    SQLiteDatabase db = this.getWritableDatabase();
	   
	    ContentValues values = new ContentValues();
	    values.put(KEY_WORD, word.getWord());
	    values.put(KEY_SENTENCE, word.getSentence());
	 
	    // updating row
	    return db.update(TABLE_WORDS, values, KEY_WORD + " = ?",
	            new String[] { word.getWord() });
	}
	
    // Deleting single contact
public void deleteWord(String word) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_WORDS, KEY_WORD + " = ?",
            new String[] { ""+word });
    db.close();
}
	
}