package com.campuspo.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * TsSkill entity. @author MyEclipse Persistence Tools
 */

public class TsSkill implements java.io.Serializable {

	// Fields

	private Integer skillId;
	private TsUsers tsUsers;
	private String skillDescription;
	private Boolean isdelete;
	private Set tsLikes = new HashSet(0);

	// Constructors

	/** default constructor */
	public TsSkill() {
	}

	/** minimal constructor */
	public TsSkill(TsUsers tsUsers, String skillDescription) {
		this.tsUsers = tsUsers;
		this.skillDescription = skillDescription;
	}

	/** full constructor */
	public TsSkill(TsUsers tsUsers, String skillDescription, Boolean isdelete,
			Set tsLikes) {
		this.tsUsers = tsUsers;
		this.skillDescription = skillDescription;
		this.isdelete = isdelete;
		this.tsLikes = tsLikes;
	}

	// Property accessors

	public Integer getSkillId() {
		return this.skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public TsUsers getTsUsers() {
		return this.tsUsers;
	}

	public void setTsUsers(TsUsers tsUsers) {
		this.tsUsers = tsUsers;
	}

	public String getSkillDescription() {
		return this.skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	public Boolean getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}

	public Set getTsLikes() {
		return this.tsLikes;
	}

	public void setTsLikes(Set tsLikes) {
		this.tsLikes = tsLikes;
	}

}