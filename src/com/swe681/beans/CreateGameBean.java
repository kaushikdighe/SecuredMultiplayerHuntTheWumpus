package com.swe681.beans;

public class CreateGameBean 
{
	private String gamename;
	private Long gametimer;
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGamename() {
		return gamename;
	}
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	public Long getGametimer() {
		return gametimer;
	}
	public void setGametimer(Long gametimer) {
		this.gametimer = gametimer;
	}
	
}
