package com.barrett.gof.建造者模式.improve;

//产品->Product
public class House {
	//打桩
	private String baise;
	//砌墙
	private String wall;
	//封顶
	private String roofed;

	public String getBaise() {
		return baise;
	}
	public void setBaise(String baise) {
		this.baise = baise;
	}
	public String getWall() {
		return wall;
	}
	public void setWall(String wall) {
		this.wall = wall;
	}
	public String getRoofed() {
		return roofed;
	}
	public void setRoofed(String roofed) {
		this.roofed = roofed;
	}
	
}
