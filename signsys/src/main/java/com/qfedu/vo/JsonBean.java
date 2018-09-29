package com.qfedu.vo;

public class JsonBean {

	private int code;//1 代表成功 2 失败
	private Object msg;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	
	
	
}
