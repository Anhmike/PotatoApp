package com.PotatoServer.Stores;

import org.joda.time.DateTime;

public class Picture {
	int id;
	int entityID;
	String type;
	String url;
	DateTime updateTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEntityID() {
		return entityID;
	}
	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public DateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(DateTime updateTime) {
		this.updateTime = updateTime;
	}

}
