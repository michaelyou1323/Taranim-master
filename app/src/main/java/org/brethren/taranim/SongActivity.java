package org.brethren.taranim;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.brethren.taranim.adapter.SongListAdapter;
import org.brethren.taranim.model.Song;
import org.brethren.taranim.settings.SettingsHelper;

//import org.brethren.taranim.R;


public class SongActivity extends ListActivity{

    private TaranimApplication app;
    private SongListAdapter adapter;
    private TextView listTitle;
    private SettingsHelper settingsHelper;
    private Typeface tf;
    private InputMethodManager inputMethodManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_activity);
        settingsHelper = new SettingsHelper(SongActivity.this);
        tf = Typeface.createFromAsset(this.getAssets(),"fonts/"+settingsHelper.getFontName()+".ttf");

        listTitle = (TextView)findViewById(R.id.list_title);
        listTitle.setTypeface(tf);
        listTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
        app = (TaranimApplication) getApplication();

        EditText songNoEditText = (EditText) findViewById(R.id.songNo_editText);
        songNoEditText.setFocusableInTouchMode(true);
        songNoEditText.requestFocus();
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(songNoEditText, InputMethodManager.SHOW_IMPLICIT);


        songNoEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s != null && !s.toString().equals(""))
                {
                    int songNo = Integer.parseInt(s.toString());

                    adapter = new SongListAdapter(app.getSongListById(songNo), SongActivity.this);

                }
                else
                {
                    adapter = new SongListAdapter(app.getCurrentSongs(), SongActivity.this);

                }

                SongActivity.this.setListAdapter(adapter);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        tf = Typeface.createFromAsset(this.getAssets(),"fonts/"+settingsHelper.getFontName()+".ttf");
        listTitle.setTypeface(tf);
        listTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
        app = (TaranimApplication) getApplication();
        adapter = new SongListAdapter(app.getCurrentSongs(), this);
        setListAdapter(adapter);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        Song song = adapter.getItem(position);
        Intent intent = new Intent(SongActivity.this, SongVersesActivity.class);
        intent.putExtra("song", song);
        startActivity(intent);
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

}
