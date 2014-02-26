package com.campuspo.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TsPoster entity. @author MyEclipse Persistence Tools
 */

public class TsPoster implements java.io.Serializable {

	// Fields

	private Integer posterId;
	private TsUsers tsUsers;
	private String posterTitle;
	private String posterDescription;
	private Short wanted;
	private Integer wantedNum;
	private Integer participantNum;
	private Timestamp posterReleasedTime;
	private Boolean isdelete;
	private Set tsParticipants = new HashSet(0);
	private Set tsFavours = new HashSet(0);

	// Constructors

	/** default constructor */
	public TsPoster() {
	}

	/** minimal constructor */
	public TsPoster(TsUsers tsUsers, String posterTitle,
			String posterDescription, Integer wantedNum,
			Integer participantNum, Timestamp posterReleasedTime) {
		this.tsUsers = tsUsers;
		this.posterTitle = posterTitle;
		this.posterDescription = posterDescription;
		this.wantedNum = wantedNum;
		this.participantNum = participantNum;
		this.posterReleasedTime = posterReleasedTime;
	}

	/** full constructor */
	public TsPoster(TsUsers tsUsers, String posterTitle,
			String posterDescription, Short wanted, Integer wantedNum,
			Integer participantNum, Timestamp posterReleasedTime,
			Boolean isdelete, Set tsParticipants, Set tsFavours) {
		this.tsUsers = tsUsers;
		this.posterTitle = posterTitle;
		this.posterDescription = posterDescription;
		this.wanted = wanted;
		this.wantedNum = wantedNum;
		this.participantNum = participantNum;
		this.posterReleasedTime = posterReleasedTime;
		this.isdelete = isdelete;
		this.tsParticipants = tsParticipants;
		this.tsFavours = tsFavours;
	}

	// Property accessors

	public Integer getPosterId() {
		return this.posterId;
	}

	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}

	public TsUsers getTsUsers() {
		return this.tsUsers;
	}

	public void setTsUsers(TsUsers tsUsers) {
		this.tsUsers = tsUsers;
	}

	public String getPosterTitle() {
		return this.posterTitle;
	}

	public void setPosterTitle(String posterTitle) {
		this.posterTitle = posterTitle;
	}

	public String getPosterDescription() {
		return this.posterDescription;
	}

	public void setPosterDescription(String posterDescription) {
		this.posterDescription = posterDescription;
	}

	public Short getWanted() {
		return this.wanted;
	}

	public void setWanted(Short wanted) {
		this.wanted = wanted;
	}

	public Integer getWantedNum() {
		return this.wantedNum;
	}

	public void setWantedNum(Integer wantedNum) {
		this.wantedNum = wantedNum;
	}

	public Integer getParticipantNum() {
		return this.participantNum;
	}

	public void setParticipantNum(Integer participantNum) {
		this.participantNum = participantNum;
	}

	public Timestamp getPosterReleasedTime() {
		return this.posterReleasedTime;
	}

	public void setPosterReleasedTime(Timestamp posterReleasedTime) {
		this.posterReleasedTime = posterReleasedTime;
	}

	public Boolean getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}

	public Set getTsParticipants() {
		return this.tsParticipants;
	}

	public void setTsParticipants(Set tsParticipants) {
		this.tsParticipants = tsParticipants;
	}

	public Set getTsFavours() {
		return this.tsFavours;
	}

	public void setTsFavours(Set tsFavours) {
		this.tsFavours = tsFavours;
	}

}