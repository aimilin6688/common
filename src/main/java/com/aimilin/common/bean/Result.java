package com.aimilin.common.bean;

import java.util.HashMap;
import java.util.Map;

public class Result {
	private static final String STATE_OK = "ok";
	private static final String STATE_FAIL = "fail";
	
	private String state;
	private Object data;
	private Map<String, Object> infos = new HashMap<>();
	public Result() {
		super();
	}
	public Result(String state, Object data) {
		super();
		this.state = state;
		this.data = data;
	}
	
	public Result put(String key,Object value) {
		this.infos.put(key, value);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		Object obj= this.infos.get(key);
		return (T) obj;
	}
	
	public boolean isSuccess() {
		return STATE_OK.equals(this.state);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getData(Class<T> clazz) {
		if(this.getData() ==null) {
			return null;
		}
		if(clazz.equals(this.getData().getClass())) {
			return (T) this.getData();
		}
		return (T)this.getData();
	}

	public static Result OK(Object data) {
		return new Result(Result.STATE_OK, data);
	}
	public static Result OK() {
		return new Result(Result.STATE_OK, "");
	}
	
	public static Result FAIL(Object data) {
		return new Result(Result.STATE_FAIL, data);
	}
	public static Result FAIL() {
		return new Result(Result.STATE_FAIL, "");
	}
	
	@Override
	public String toString() {
		return "Result [state=" + state + ", data=" + data + ", infos=" + infos + "]";
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Map<String, Object> getInfos() {
		return infos;
	}
	public void setInfos(Map<String, Object> infos) {
		this.infos = infos;
	}
}
