package com.campuspo.bean;

import java.sql.Timestamp;

/**
 * TsFavour entity. @author MyEclipse Persistence Tools
 */

public class TsFavour implements java.io.Serializable {

	// Fields

	private TsFavourId id;
	private Boolean isdelete;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public TsFavour() {
	}

	/** minimal constructor */
	public TsFavour(TsFavourId id) {
		this.id = id;
	}

	/** full constructor */
	public TsFavour(TsFavourId id, Boolean isdelete) {
		this.id = id;
		this.isdelete = isdelete;
	}

	// Property accessors

	public TsFavourId getId() {
		return this.id;
	}

	public void setId(TsFavourId id) {
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