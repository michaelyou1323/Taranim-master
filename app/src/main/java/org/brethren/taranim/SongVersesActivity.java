package org.brethren.taranim;

import org.brethren.taranim.adapter.SongVersesListAdapter;
import org.brethren.taranim.model.Song;
import org.brethren.taranim.model.SongVerses;
import org.brethren.taranim.settings.SettingsHelper;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.brethren.taranim.R;


public class SongVersesActivity extends ListActivity {

    private TextView songNumberText;
	private TextView songNazm;
	private TaranimApplication app;
	private SongVersesListAdapter adapter;
    private Typeface tf;
	private Song song;
	private SettingsHelper settingsHelper;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.song_verses_activity);
		settingsHelper = new SettingsHelper(SongVersesActivity.this);
        songNumberText = (TextView) findViewById(R.id.song_number);
		songNazm = (TextView) findViewById(R.id.song_nazm);
        songNumberText.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
        songNazm.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());

		Intent intent = getIntent();
		song = (Song)intent.getSerializableExtra("song");
        songNumberText.setText(""+song.getNumber());
		songNazm.setText((song.getNazm() == null || song.getNazm().equals("")) ? "" : "("+song.getNazm()+")");
		
		app = (TaranimApplication) getApplication();
		adapter = new SongVersesListAdapter(app.getSongVerses(song), this);
		setListAdapter(adapter);

        getActionBar().setHomeButtonEnabled(true);

        tf = Typeface.createFromAsset(this.getAssets(),"fonts/"+settingsHelper.getFontName()+".ttf");
        songNazm.setTypeface(tf);
        songNumberText.setTypeface(tf);


	}
	@Override
	protected void onResume() {
		super.onResume();
        tf = Typeface.createFromAsset(this.getAssets(),"fonts/"+settingsHelper.getFontName()+".ttf");
        songNumberText.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
        songNazm.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
        songNazm.setTypeface(tf);
        songNumberText.setTypeface(tf);
		app = (TaranimApplication) getApplication();
		adapter = new SongVersesListAdapter(app.getSongVerses(song), this);
		setListAdapter(adapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuHelper.openActivityFromMenuItem(this, item);
	}

    public void goNext(View view)
    {
        int songNumber = song.getNumber();
        if(songNumber < 375)
            songNumber++;
        else
            songNumber=1;

        song = app.getSongById(songNumber);
        songNumberText.setText(""+song.getNumber());
        songNazm.setText((song.getNazm() == null || song.getNazm().equals("")) ? "" : "("+song.getNazm()+")");
        app = (TaranimApplication) getApplication();
        adapter = new SongVersesListAdapter(app.getSongVerses(song), this);
        setListAdapter(adapter);

    }

    public void goPrev(View view)
    {
        int songNumber = song.getNumber();
        if(songNumber > 1)
            songNumber--;
        else
            songNumber=375;

        song = app.getSongById(songNumber);
        songNumberText.setText(""+song.getNumber());
        songNazm.setText((song.getNazm() == null || song.getNazm().equals("")) ? "" : "("+song.getNazm()+")");
        app = (TaranimApplication) getApplication();
        adapter = new SongVersesListAdapter(app.getSongVerses(song), this);
        setListAdapter(adapter);
    }



    public void share(View view)
    {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this site!");
            startActivity(Intent.createChooser(intent, "Share"));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        SongVerses songVerse = adapter.getItem(position);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        StringBuilder  shareStr = new StringBuilder(songVerse.getVerseText().replace('/','\n'));
        shareStr.append("\n\n");
        shareStr.append(getResources().getString(R.string.from_app));
        shareStr.append("\n");
        shareStr.append(getResources().getString(R.string.goole_play_link));
        shareStr.append(this.getPackageName());
        intent.putExtra(Intent.EXTRA_TEXT, shareStr.toString());
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this site!");
        startActivity(Intent.createChooser(intent, "Share"));
    }


}
