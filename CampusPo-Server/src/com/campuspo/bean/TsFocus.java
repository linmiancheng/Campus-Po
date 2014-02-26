package com.campuspo.bean;

import java.sql.Timestamp;

/**
 * TsFocus entity. @author MyEclipse Persistence Tools
 */

public class TsFocus implements java.io.Serializable {

	// Fields

	private TsFocusId id;
	private Boolean isdelete;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public TsFocus() {
	}

	/** minimal constructor */
	public TsFocus(TsFocusId id) {
		this.id = id;
	}

	/** full constructor */
	public TsFocus(TsFocusId id, Boolean isdelete) {
		this.id = id;
		this.isdelete = isdelete;
	}

	// Property accessors

	public TsFocusId getId() {
		return this.id;
	}

	public void setId(TsFocusId id) {
		this.id = id;
	}

	public Boolean getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}