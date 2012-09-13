/*
 * 	CouchTatertot is a android app for managing couchpotato
 * 	Copyright (C) 2012  David Stocking dmstocking@gmail.com
 * 
 * 	http://code.google.com/p/couch-tatertot/
 * 	
 * 	libCouchPotato is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 * 	
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 * 	
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.couchtatertot.fragment;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.couchtatertot.R;
import org.couchtatertot.task.MovieAddTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class OnShareViaFragment extends SherlockFragment {

	// FORMAT FOR TEXT FROM ANDROID IMDB APP
	// <Show Name>\n<Uri>
	private static Pattern imdbAndroid = Pattern.compile("https?\\://(www\\.)?(m\\.)?imdb\\.com/rg/an_share/title/title/(tt\\d{7})/");
	private static Pattern imdbShowtimeWebsite = Pattern.compile("https?\\://(www\\.)?(m\\.)?imdb\\.com/showtimes/title/(tt\\d{7})/");
	private static Pattern imdbTitleWebsite = Pattern.compile("https?\\://(www\\.)?(m\\.)?imdb\\.com/title/(tt\\d{7})/");
	
	private static List<Pattern> imdbPatterns = Arrays.asList( imdbAndroid, imdbShowtimeWebsite, imdbTitleWebsite );
	
	private String extras;
	private String imdb;
	
	private LinearLayout working;
	private TextView error;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Intent intent = this.getActivity().getIntent();
		extras = intent.getStringExtra(Intent.EXTRA_TEXT);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.on_share_via_fragment, container, false);
		return root;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		working = (LinearLayout) view.findViewById(R.id.workingLinearLayout);
		error = (TextView) view.findViewById(R.id.errorTextView);
		
        for ( Pattern p : imdbPatterns ) {
        	Matcher m = p.matcher(extras.toString());
	        if ( m.find() ) {
	        	// we need to do stuff
	        	imdb = m.group(3);
	        	MovieAddTask task = new MovieAddTask(imdb){
					@Override
					protected void onPostExecute(Void result) {
						super.onPostExecute(result);
						
						if ( OnShareViaFragment.this.getActivity() == null )
							return; // no point to do anything our activity is gone :(
						
						if ( error == null ) {
							Toast success = Toast.makeText( getActivity(), "Successfully added movie!!!", Toast.LENGTH_LONG );
							success.show();
							OnShareViaFragment.this.getActivity().finish();
						} else {
							working.setVisibility(View.GONE);
							OnShareViaFragment.this.error.setText("Error adding movie.\nERROR: " + error.getMessage());
							OnShareViaFragment.this.error.setVisibility(View.VISIBLE);
						}
					}
				};
				task.execute();
	        }
        }
        if ( imdb == null ) {
        	working.setVisibility(View.GONE);
        	error.setVisibility(View.VISIBLE);
        }
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
}