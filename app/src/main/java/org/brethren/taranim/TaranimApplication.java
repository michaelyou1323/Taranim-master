package org.brethren.taranim;

import static org.brethren.taranim.model.TaranimSQLiteOpenHelper.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.brethren.taranim.model.Song;
import org.brethren.taranim.model.SongVerses;
import org.brethren.taranim.model.TaranimSQLiteOpenHelper;

import android.annotation.SuppressLint;
import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaranimApplication extends Application{
	
	public SQLiteDatabase database;
	private ArrayList<Song> currentSongs;

	private static final char a = 'ا' ;
	private static final char ah = 'أ' ;
	private static final char ahd = 'إ' ;
	private static final char ahu = 'آ' ;
	private static final char  z = 'ى' ;
	private static final char  zn = 'ي' ;
	private static final char  h = 'ه' ;
	private static final char  tm = 'ة' ;

	public ArrayList<Song> getCurrentSongs() {
		return currentSongs;
	}

	public void setCurrentSongs(ArrayList<Song> currentSongs) {
		this.currentSongs = currentSongs;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		TaranimSQLiteOpenHelper helper = new TaranimSQLiteOpenHelper(this);
		database = helper.getWritableDatabase();
		try {
			copyDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (null == currentSongs) {
			loadSongs();
		}
	}

	@SuppressLint("SdCardPath")
	private void copyDataBase() throws IOException {

	    // Open your local db as the input stream
	    InputStream myInput = this.getAssets().open(DB_NAME);
	    // Path to the just created empty db
	    String outFileName =  "/data/data/"
	            +getApplicationContext().getPackageName()
	            + "/databases/" + DB_NAME;
		File f = new File(outFileName);
	    // Open the empty db as the output stream
	    OutputStream myOutput = new FileOutputStream(outFileName);
	    // transfer bytes from the inputfile to the outputfile
	    byte[] buffer = new byte[1024];
	    int length;
	    while ((length = myInput.read(buffer)) > 0) {
	        myOutput.write(buffer, 0, length);

	    }
	    // Close the streams
	    myOutput.flush();
	    myOutput.close();
	    myInput.close();
	}

	private void loadSongs() {
		currentSongs = new ArrayList<Song>();
		Cursor songsCursor = database.query(SONG_TABLE, new String[] { ID,
				TITLE, NAZM, NUMBER }, null, null, null, null,
				String.format("%s", ID));

		Song song;
		if (songsCursor.moveToFirst()) {
			do {
				long id = songsCursor.getLong(0);
				String title = songsCursor.getString(1);
				String nazm = songsCursor.getString(2);
				int number = songsCursor.getInt(3);

				song = new Song();
				song.setId(id);
				song.setTitle(title);
			    song.setNazm(nazm);
			    song.setNumber(number);
			    
				currentSongs.add(song);
			} while (songsCursor.moveToNext());
		}
		songsCursor.close();
	}
	
	public ArrayList<SongVerses> getSongVerses(Song song){
		ArrayList<SongVerses> verses = new ArrayList<SongVerses>();
		Cursor versesCursor = database.query(SONG_VERSES_TABLE, new String[] { ID,
				VERSE_TEXT, NUMBER }, SONG_ID + " = " + song.getId(), null, null, null,
				String.format("%s", ID));
		SongVerses verse;
		if (versesCursor.moveToFirst()) {
			do {
				long id = versesCursor.getLong(0);
				String verseText = versesCursor.getString(1);
				int verseNumber = versesCursor.getInt(2);

				verse = new SongVerses();
				verse.setId(id);
				verse.setSongId(song.getNumber());
				verse.setVerseText(verseText);
				verse.setNumber(verseNumber);
			    
				verses.add(verse);
			} while (versesCursor.moveToNext());
		}
		versesCursor.close();
		return verses;
	}
	
	public ArrayList<SongVerses> searchVersesBy(String searchText)
	{

		ArrayList<SongVerses> verses = new ArrayList<SongVerses>();

		searchText = processSearchText(searchText);
		String query = "select id, song_id, verse_text, number from song_verses where " +
 		"replace(replace(replace(replace(replace(replace( "+
 		"verse_search_text, '"+ ah +"', '"+ a +"') "+
				",'"+ ahd+"', '"+ a +"') "+
				",'"+ ahu+"', '"+ a +"') "+
				",'"+ zn+"', '"+ z +"') "+
				",'"+ tm+"', '"+ h +"') "+
				",'/', ' ') "+
				" LIKE '%" +searchText+ "%' ";
		Cursor versesCursor = database.rawQuery(query, null);

	//	Cursor versesCursor = database.query(SONG_VERSES_TABLE, new String[] { ID, SONG_ID, VERSE_TEXT, NUMBER }, VERSE_SEARCH_TEXT + " like '%" + searchText + "%'", null, null, null, String.format("%s", ID));
		SongVerses verse;
		if (versesCursor.moveToFirst())
		{
			do {
				long id = versesCursor.getLong(0);
				long songId = versesCursor.getLong(1);
				String verseText = versesCursor.getString(2);
				int verseNumber = versesCursor.getInt(3);

				verse = new SongVerses();
				verse.setId(id);
				verse.setSongId(songId);
				verse.setVerseText(verseText);
				verse.setNumber(verseNumber);
			    
				verses.add(verse);
			} while (versesCursor.moveToNext());
		}
		versesCursor.close();
		return verses;
	}

	private String processSearchText(String searchText)
	{
		searchText=searchText.replace('أ','ا');
		searchText=searchText.replace('إ','ا');
		searchText=searchText.replace('آ','ا');
		searchText=searchText.replace('ي','ى');
		searchText=searchText.replace('ة','ه');

		return searchText;
	}
	
	public Song getSongById(long songId)
	{
		Cursor songsCursor = database.query(SONG_TABLE, new String[] { ID,
						TITLE, NAZM, NUMBER }, ID +"="+ songId, null, null, null,
				String.format("%s", ID));

		Song song = new Song();
		if (songsCursor.moveToFirst())
		{

			long id = songsCursor.getLong(0);
			String title = songsCursor.getString(1);
			String nazm = songsCursor.getString(2);
			int number = songsCursor.getInt(3);

			songsCursor.close();
			song.setId(id);
			song.setTitle(title);
			song.setNazm(nazm);
			song.setNumber(number);

		}
		return song;
	}

	
	public ArrayList<Song> getSongListById(long songId) 
	{
		Cursor songsCursor = database.query(SONG_TABLE, new String[] { ID,
				TITLE, NAZM, NUMBER }, ID +"="+ songId, null, null, null,
				String.format("%s", ID));

		ArrayList<Song> songList = new ArrayList<Song>();
		if (songsCursor.moveToFirst()) 
		{
			
				long id = songsCursor.getLong(0);
				String title = songsCursor.getString(1);
				String nazm = songsCursor.getString(2);
				int number = songsCursor.getInt(3);
				
				songsCursor.close();
				
				Song song = new Song();
				song.setId(id);
				song.setTitle(title);
			    song.setNazm(nazm);
			    song.setNumber(number);
			   
			    
			    songList.add(song);
		}
		
		return songList;
	}

}
		
