package com.campuspo.bean;

/**
 * TsLike entity. @author MyEclipse Persistence Tools
 */

public class TsLike implements java.io.Serializable {

	// Fields

	private TsLikeId id;
	private Boolean isdelete;

	// Constructors

	/** default constructor */
	public TsLike() {
	}

	/** minimal constructor */
	public TsLike(TsLikeId id) {
		this.id = id;
	}

	/** full constructor */
	public TsLike(TsLikeId id, Boolean isdelete) {
		this.id = id;
		this.isdelete = isdelete;
	}

	// Property accessors

	public TsLikeId getId() {
		return this.id;
	}

	public void setId(TsLikeId id) {
		this.id = id;
	}

	public Boolean getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}

}