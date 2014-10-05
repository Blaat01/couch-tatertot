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

import java.util.ArrayList;
import java.util.List;

public class _NewAPI_MovieListJson {
	public List<_NewAPI_MovieJson> movies;
	public int total;
	public boolean success;
	public boolean empty;
	
	public MovieListJson toOld() {
		MovieListJson old = new MovieListJson();
		old.movies = new ArrayList<MovieJson>();
		for (_NewAPI_MovieJson movie : this.movies) {
			movie.toOld();
			old.movies.add(movie.toOld());
		}
		old.total = this.total;
		old.success = this.success;
		old.empty = this.empty;
		return old;
	}
}
