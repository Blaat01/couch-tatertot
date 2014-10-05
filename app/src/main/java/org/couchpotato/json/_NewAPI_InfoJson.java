/*
 * 	libCouchPotato is a java library for communication with couchpotato
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
package org.couchpotato.json;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class _NewAPI_InfoJson {
	@SerializedName("via_tmdb")
	public boolean viaTmdb;
	@SerializedName("via_imdb")
	public boolean viaImdb;
	// TODO actually make this although I never think ill use it
//	public RatingJson rating;
	public List<String> genres;
	public String plot;
	public String tagline;
	// TODO actually make this too
//	@SerializedName("release_date")
//	public ReleaseDateJson releaseDate;
	@SerializedName("original_title")
	public String originalTitle;
	public List<String> writers;
	public List<String> directors;
	public List<String> actors;
	public List<String> titles;
	public String imdb;
	public int year;
	public ImagesJson images;
	public int runtime;
	public int id;
	
	@SerializedName("status_id")
	public int statusId;
	public String identifier;
	public List<FilesJson> files;
	
	public LibraryJson toOld() {
		// LibraryJson stuff
		LibraryJson old = new LibraryJson();
		old.plot = this.plot;
		old.statusId = this.statusId;
		old.tagline = this.tagline;
		old.titles = new ArrayList<TitleJson>();
		for (int i = 0; i < this.titles.size(); i++) {
			TitleJson title_json = new TitleJson();
			title_json.id = i;
			title_json.isDefault = (i == 0);
			title_json.title = this.titles.get(i);
			title_json.simpleTitle = title_json.title.replaceAll("[^\\x00-\\x7F]", "");
			old.titles.add(title_json);
		}
		old.year = this.year;
		old.identifier = this.identifier;
		old.files = this.files;
		
		// InfoJson stuff
		old.info = new InfoJson();
		old.info.actors = this.actors;
		old.info.directors = this.directors;
		old.info.genres = this.genres;
		old.info.id = this.id;
		old.info.images = this.images;
		old.info.imdb = this.imdb;
		old.info.originalTitle = this.originalTitle;
		old.info.plot = this.plot;
		old.info.runtime = this.runtime;
		old.info.tagline = this.tagline;
		old.info.titles = this.titles;
		old.info.viaImdb = this.viaImdb;
		old.info.viaTmdb = this.viaTmdb;
		old.info.writers = this.writers;
		old.info.year = this.year;
		
		return old;
	}
}
