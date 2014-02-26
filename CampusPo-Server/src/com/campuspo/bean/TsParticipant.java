package com.campuspo.bean;

import java.sql.Timestamp;

/**
 * TsParticipant entity. @author MyEclipse Persistence Tools
 */

public class TsParticipant implements java.io.Serializable {

	// Fields

	private TsParticipantId id;
	private Timestamp joinTime;
	private Boolean isdelete;

	// Constructors

	/** default constructor */
	public TsParticipant() {
	}

	/** minimal constructor */
	public TsParticipant(TsParticipantId id, Timestamp joinTime) {
		this.id = id;
		this.joinTime = joinTime;
	}

	/** full constructor */
	public TsParticipant(TsParticipantId id, Timestamp joinTime,
			Boolean isdelete) {
		this.id = id;
		this.joinTime = joinTime;
		this.isdelete = isdelete;
	}

	// Property accessors

	public TsParticipantId getId() {
		return this.id;
	}

	public void setId(TsParticipantId id) {
		this.id = id;
	}

	public Timestamp getJoinTime() {
		return this.joinTime;
	}

	public void setJoinTime(Timestamp joinTime) {
		this.joinTime = joinTime;
	}

	public Boolean getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}

}