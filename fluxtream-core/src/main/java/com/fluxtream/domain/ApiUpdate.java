package com.fluxtream.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

@Entity(name="ApiUpdates")
@NamedQueries ( {
	@NamedQuery(name = "apiUpdates.count.all.since",
		query = "SELECT COUNT(updt) FROM ApiUpdates updt WHERE updt.api=? AND updt.ts>?"),
	@NamedQuery(name = "apiUpdates.count.byGuest.since",
		query = "SELECT COUNT(updt) FROM ApiUpdates updt WHERE updt.guestId=? AND updt.api=? AND updt.ts>?"),
	@NamedQuery(name = "apiUpdates.count.all",
		query = "SELECT COUNT(updt) FROM ApiUpdates updt WHERE updt.api=?"),
	@NamedQuery(name = "apiUpdates.count.byGuest",
		query = "SELECT COUNT(updt) FROM ApiUpdates updt WHERE updt.guestId=? AND updt.api=?"),
	@NamedQuery(name = "apiUpdates.delete.all",
		query = "DELETE FROM ApiUpdates updt WHERE updt.guestId=?"),
	@NamedQuery(name = "apiUpdates.delete.byApiAndObjectTypes",
		query = "DELETE FROM ApiUpdates updt WHERE updt.guestId=? AND updt.api=? AND updt.objectTypes=?"),
	@NamedQuery(name = "apiUpdates.delete.byApi",
		query = "DELETE FROM ApiUpdates updt WHERE updt.guestId=? AND updt.api=?"),
	@NamedQuery( name="apiUpdates.last",
		query="SELECT updt FROM ApiUpdates updt WHERE updt.guestId=? and updt.api=? ORDER BY updt.ts DESC LIMIT 10"),
	@NamedQuery( name="apiUpdates.last.successful.byApi",
		query="SELECT updt FROM ApiUpdates updt WHERE updt.guestId=? and updt.api=? and updt.success=true ORDER BY updt.ts DESC"),
	@NamedQuery( name="apiUpdates.last.successful.byApiAndObjectTypes",
		query="SELECT updt FROM ApiUpdates updt WHERE updt.guestId=? and updt.api=? and updt.objectTypes=? and updt.success=true ORDER BY updt.ts DESC"),
    @NamedQuery( name="apiUpdates.lastSync",
        query="SELECT updt FROM ApiUpdates updt WHERE updt.guestId=? and updt.api=? and updt.objectTypes=? and updt.success=true and lastSync<>0 ORDER BY updt.lastSync DESC LIMIT 10")})
public class ApiUpdate extends AbstractEntity {

	@Index(name="guestId")
	public long guestId;
	
	@Index(name="ts")
	public long ts;
	
	@Index(name="elapsed")
	public long elapsed;

	@Index(name="api")
	public int api;
	@Index(name="objectTypes")
	public int objectTypes;
	
	@Index(name="success")
	@Type(type="yes_no")
	public boolean success;

	public String query;

    public long lastSync;
}
