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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class _NewAPI_MovieJson {
	public ProfileJson profile;
	@SerializedName("library_id")
	public int libraryId;
	public List<ReleaseJson> releases;
	@SerializedName("status_id")
	public int statusId;
	@SerializedName("profile_id")
	public String profileId;
	public _NewAPI_InfoJson info;
	public String status;
	@SerializedName("last_edit")
	public int lastEdit;
	public int id;
	public _NewAPI_Files files;
	
	public MovieJson toOld() {
		MovieJson old = new MovieJson();
		old.profile = this.profile;
		old.libraryId = this.libraryId;
		old.releases = this.releases;
		old.statusId = this.statusId;
		old.library = this.info.toOld();
		old.lastEdit = this.lastEdit;
		old.id = this.id;

		old.profileId = new BigInteger(this.profileId, 16).intValue();

		old.status = new StatusJson();
		old.status.id = 0;
		old.status.identifier = this.status;
		old.status.label = this.status;
		
		old.files = new ArrayList<String>();
		for (String files : this.files.imagePoster) {
			old.files.add(files);
		}
		return old;
	}
}
