package com.campuspo.bean;

/**
 * TsFavourId entity. @author MyEclipse Persistence Tools
 */

public class TsFavourId implements java.io.Serializable {

	// Fields

	private TsPoster tsPoster;
	private TsUsers tsUsers;

	// Constructors

	/** default constructor */
	public TsFavourId() {
	}

	/** full constructor */
	public TsFavourId(TsPoster tsPoster, TsUsers tsUsers) {
		this.tsPoster = tsPoster;
		this.tsUsers = tsUsers;
	}

	// Property accessors

	public TsPoster getTsPoster() {
		return this.tsPoster;
	}

	public void setTsPoster(TsPoster tsPoster) {
		this.tsPoster = tsPoster;
	}

	public TsUsers getTsUsers() {
		return this.tsUsers;
	}

	public void setTsUsers(TsUsers tsUsers) {
		this.tsUsers = tsUsers;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TsFavourId))
			return false;
		TsFavourId castOther = (TsFavourId) other;

		return ((this.getTsPoster() == castOther.getTsPoster()) || (this
				.getTsPoster() != null && castOther.getTsPoster() != null && this
				.getTsPoster().equals(castOther.getTsPoster())))
				&& ((this.getTsUsers() == castOther.getTsUsers()) || (this
						.getTsUsers() != null && castOther.getTsUsers() != null && this
						.getTsUsers().equals(castOther.getTsUsers())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTsPoster() == null ? 0 : this.getTsPoster().hashCode());
		result = 37 * result
				+ (getTsUsers() == null ? 0 : this.getTsUsers().hashCode());
		return result;
	}

}