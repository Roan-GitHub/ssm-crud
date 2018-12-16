package com.atguigu.crud.bean;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/*
	 * 通用的返回Json的类
	 * */

public class Msg {
	
	//状态码, 如果服务器给浏览器返回100代表成功,返回200,代表服务器处理失败
	private int code;
	//提示信息
	private String msg;
	//用户要返回给浏览器的数据
	private Map<String,Object> extend = new HashMap<String,Object>();
	
	//处理成功的方法,状态码为100,提示信息为处理成功
	public static Msg success()
	{
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("处理成功！");
		return result;
	}
	 
	//处理失败的方法,状态码为200,提示信息为处理失败
	public static Msg fail()
	{
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("处理失败！");
		return result;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getExtend() {
		return extend;
	}
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

	//添加方法,Msg对象中添加查询或者处理的其他信息
	public Msg add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}
	
	
}
