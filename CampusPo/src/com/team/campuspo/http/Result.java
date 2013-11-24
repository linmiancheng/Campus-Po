package com.team.campuspo.http;

import java.io.Serializable;

public class Result {
	
	private int mCode;
	private String mMessage;
	
	private Serializable mEntity;
	
	public Result(int code, String message, Serializable entity) {
		mCode = code;
		mMessage = message;
		mEntity = entity;
	}
	
	public int getCode() {
		return mCode;
	}
	
	public String getMessage() {
		return mMessage;
	}
	
	public Serializable getEntity() {
		return mEntity;
	}
}
