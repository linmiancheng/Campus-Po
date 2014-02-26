package com.campuspo.bean;

/**
 * TsFocusId entity. @author MyEclipse Persistence Tools
 */

public class TsFocusId implements java.io.Serializable {

	// Fields

	private TsUsers tsUsers;
	private TsUsers tsUsers_1;

	// Constructors

	/** default constructor */
	public TsFocusId() {
	}

	/** full constructor */
	public TsFocusId(TsUsers tsUsers, TsUsers tsUsers_1) {
		this.tsUsers = tsUsers;
		this.tsUsers_1 = tsUsers_1;
	}

	// Property accessors

	public TsUsers getTsUsers() {
		return this.tsUsers;
	}

	public void setTsUsers(TsUsers tsUsers) {
		this.tsUsers = tsUsers;
	}

	public TsUsers getTsUsers_1() {
		return this.tsUsers_1;
	}

	public void setTsUsers_1(TsUsers tsUsers_1) {
		this.tsUsers_1 = tsUsers_1;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TsFocusId))
			return false;
		TsFocusId castOther = (TsFocusId) other;

		return ((this.getTsUsers() == castOther.getTsUsers()) || (this
				.getTsUsers() != null && castOther.getTsUsers() != null && this
				.getTsUsers().equals(castOther.getTsUsers())))
				&& ((this.getTsUsers_1() == castOther.getTsUsers_1()) || (this
						.getTsUsers_1() != null
						&& castOther.getTsUsers_1() != null && this
						.getTsUsers_1().equals(castOther.getTsUsers_1())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTsUsers() == null ? 0 : this.getTsUsers().hashCode());
		result = 37 * result
				+ (getTsUsers_1() == null ? 0 : this.getTsUsers_1().hashCode());
		return result;
	}

}