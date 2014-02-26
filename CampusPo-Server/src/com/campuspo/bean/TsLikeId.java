package com.campuspo.bean;

/**
 * TsLikeId entity. @author MyEclipse Persistence Tools
 */

public class TsLikeId implements java.io.Serializable {

	// Fields

	private TsUsers tsUsers;
	private TsSkill tsSkill;

	// Constructors

	/** default constructor */
	public TsLikeId() {
	}

	/** full constructor */
	public TsLikeId(TsUsers tsUsers, TsSkill tsSkill) {
		this.tsUsers = tsUsers;
		this.tsSkill = tsSkill;
	}

	// Property accessors

	public TsUsers getTsUsers() {
		return this.tsUsers;
	}

	public void setTsUsers(TsUsers tsUsers) {
		this.tsUsers = tsUsers;
	}

	public TsSkill getTsSkill() {
		return this.tsSkill;
	}

	public void setTsSkill(TsSkill tsSkill) {
		this.tsSkill = tsSkill;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TsLikeId))
			return false;
		TsLikeId castOther = (TsLikeId) other;

		return ((this.getTsUsers() == castOther.getTsUsers()) || (this
				.getTsUsers() != null && castOther.getTsUsers() != null && this
				.getTsUsers().equals(castOther.getTsUsers())))
				&& ((this.getTsSkill() == castOther.getTsSkill()) || (this
						.getTsSkill() != null && castOther.getTsSkill() != null && this
						.getTsSkill().equals(castOther.getTsSkill())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTsUsers() == null ? 0 : this.getTsUsers().hashCode());
		result = 37 * result
				+ (getTsSkill() == null ? 0 : this.getTsSkill().hashCode());
		return result;
	}

}