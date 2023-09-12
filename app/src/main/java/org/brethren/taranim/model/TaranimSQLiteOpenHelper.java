package org.brethren.taranim.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaranimSQLiteOpenHelper extends SQLiteOpenHelper{
	
	public static final String DB_NAME = "taranim_db.sqlite";
	public static final int VERSION = 1;
	
	public static final String SONG_TABLE = "song";
	public static final String SONG_VERSES_TABLE = "song_verses";

	
	
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String AUTHOR = "author";
	public static final String COMPOSER = "composer";
	public static final String TUNE = "tune";
	public static final String NAZM = "nazm";
	public static final String METER = "meter";
	public static final String SIGNATURE = "signature";
	public static final String CHORDS = "chords";
	
	public static final String NUMBER = "number";
	public static final String VERSE_TEXT = "verse_text";
	public static final String VERSE_SEARCH_TEXT = "verse_search_text";
	public static final String SONG_ID = "song_id";
	
	
	public TaranimSQLiteOpenHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		createTables(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	private void createTables(SQLiteDatabase db) {
		
		db.execSQL(
				"create table " + SONG_TABLE + " ("+
				 ID + " integer primary key autoincrement not null, "+
				 TITLE + " text, " +
				 AUTHOR + " text, "+
				 COMPOSER + " text, "+
				 TUNE + " text, "+ 
				 METER + " text, " +
				 SIGNATURE + " text, "+
				 CHORDS + " text, "+
				 NUMBER + " integer, "+
				 NAZM + " text "+
				 ");"
				 );
		
		db.execSQL(
				"create table " + SONG_VERSES_TABLE + " ("+
				 ID + " integer primary key autoincrement not null, "+
				 SONG_ID + " integer, " +
				 VERSE_TEXT + " text, " +
				 VERSE_SEARCH_TEXT + " text, "+
				 NUMBER + " integer " +
				 ");"
				 );
	}
	
}
