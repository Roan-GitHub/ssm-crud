package com.mr.car;

import java.util.Date;

public class Car {
	  private Integer id;
	  private double capcity;
	  private String start;
	  private String info;
	  private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getCapcity() {
		return capcity;
	}
	public void setCapcity(double capcity) {
		this.capcity = capcity;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
